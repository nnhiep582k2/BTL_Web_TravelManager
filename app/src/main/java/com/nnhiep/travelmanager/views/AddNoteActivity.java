package com.nnhiep.travelmanager.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.nnhiep.travelmanager.R;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle;
    TextInputEditText etDescription;
    Button btnAdd, btnCancel;
    Integer id = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        etTitle = findViewById(R.id.etTitleAdd);
        etDescription = findViewById(R.id.etDescriptionAdd);
        btnAdd = findViewById(R.id.btnAddNote);
        btnCancel = findViewById(R.id.btnCancleNote);
        //get intent from which content call it
        Intent intent = getIntent();
        //get bundle
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getInt("Id");
            String title = bundle.getString("Title");
            String description = bundle.getString("Description");
            etTitle.setText(title);
            etDescription.setText(description);
            btnAdd.setText("Save");
        }
        btnCancel.setOnClickListener(v -> finish());
        btnAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            Intent intent2 = new Intent();
            Bundle b = new Bundle();
            if (id != -1)
                b.putInt("Id", id);
            b.putString("Title", title);
            b.putString("Description", description);
            intent2.putExtras(b);
            setResult(150, intent2);
            finish();
        });
    }
}
