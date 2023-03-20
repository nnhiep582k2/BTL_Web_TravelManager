package com.nnhiep.travelmanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.NoteTable;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends BaseAdapter {
    //Nguon du lieu cho adapter
    private List<NoteTable> listNode;
    //Ngu canh ung dung
    private Activity context;
    //Doi tuong de phan tich layout
    private LayoutInflater inflater;

    public NoteAdapter() {
    }

    public NoteAdapter(List<NoteTable> listNode, Activity context) {
        this.listNode = listNode;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public NoteAdapter(ArrayList<NoteTable> listNode, Activity context, LayoutInflater inflater) {
        this.listNode = listNode;
        this.context = context;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return listNode.size();
    }

    @Override
    public Object getItem(int position) {
        return listNode.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listNode.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.note_item, null);
        }
        EditText etTitle = v.findViewById(R.id.etTitleAdd);
        TextInputEditText etDescription = v.findViewById(R.id.etDescriptionAdd);
        etTitle.setText(listNode.get(position).getTitle());
        etDescription.setText(listNode.get(position).getDescription());
        return v;
    }
}
