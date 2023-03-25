package com.nnhiep.travelmanager.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.views.FQA_Set;
import com.nnhiep.travelmanager.views.Instruct_Set;
import com.nnhiep.travelmanager.views.ListView_TeamProduct;
import com.nnhiep.travelmanager.views.Rules_Set;

/**
 * Trang thiết lập - Bắc
 */
public class SettingsFragment extends Fragment {
    public SettingsFragment() {}
     private ImageView imgTeam,imgVersion,imgcontact,imgRules,imginstruct,imageFQA;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        imgTeam = view.findViewById(R.id.imageView21);
        imgVersion =  view.findViewById(R.id.imageView23);
        imgcontact =  view.findViewById(R.id.imageView18);
        imgRules = view.findViewById(R.id.imageView20);
        imginstruct = view.findViewById(R.id.imageView19);
        imageFQA = view.findViewById(R.id.imageView22);

        imageFQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FQA_Set.class);
                startActivity(intent);
            }
        });

        imginstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Instruct_Set.class);
                startActivity(intent);
            }
        });
        imgRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Rules_Set.class);
                startActivity(intent);
            }
        });

        imgcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 openDialogcontact();
            }
        });
        imgVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        imgTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ListView_TeamProduct.class);
                startActivity(intent);
            }
        });


       return view;


    }
    public void openDialogcontact(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Contact us")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setMessage("group1_android@gmail.com")
        ;
        builder.show();

    }
    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setMessage("Version 1.0")
        ;
        builder.show();

    }
}