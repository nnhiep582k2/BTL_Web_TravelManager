package com.nnhiep.travelmanager.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.Note;

import java.util.List;

public class ManageNoteActivity extends AppCompatActivity {
    private Button btnAdd, btnBack;
    private ListView lvListNote;
    private List<Note> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_note);
        btnAdd = findViewById(R.id.btnAddNote);
        btnBack = findViewById(R.id.btnBack);
        lvListNote = findViewById(R.id.lvlistNote);

    }
}
