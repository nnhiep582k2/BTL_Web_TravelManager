package com.nnhiep.travelmanager.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nnhiep.travelmanager.R;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{

public SlideAdapter(List<SlideItem> slideitem, ViewPager2 viewpager2) {
        this.slideitem = slideitem;
        this.viewpager2 = viewpager2;
    }

    private List<SlideItem>  slideitem;
    private ViewPager2 viewpager2;
    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new SlideViewHolder(
               LayoutInflater.from(parent.getContext()).inflate(
                       R.layout.slider_search_item,
                       parent,
                       false

               )
       );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
       holder.setImage(slideitem.get(position));
       if(position== slideitem.size() -2)
       {
           viewpager2.post(runnable);
       }
    }

    @Override
    public int getItemCount() {
        return slideitem.size();
    }




    class SlideViewHolder extends RecyclerView.ViewHolder
    {
        private RoundedImageView imageview;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageslide);

        }
        void setImage(SlideItem slideitem)
        {
            imageview.setImageResource(slideitem.getImage());
        }


    }
    private Runnable runnable=new Runnable()
    {
        @Override
        public void run() {
            slideitem.addAll(slideitem);
            notifyDataSetChanged();
        }
    };


}
