package com.nnhiep.travelmanager.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.NoteAdapter;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.models.Note;

import java.util.List;

public class ManageNoteActivity extends AppCompatActivity {
    private ImageView btnAdd, btnBack;
    private ListView lvListNote;
    private List<Note> listNote;
    private Database db;
    private NoteAdapter adapter;
    int itemSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_note);
        btnAdd = findViewById(R.id.ivAdd);
        btnBack = findViewById(R.id.ivBack);
        lvListNote = findViewById(R.id.lvlistNote);

        db = new Database(this);
//        db.deleteAllNote();
//        db.insertANote("Tour Hà Nội","Được chị Thanh tip 1 củ");
//        db.insertANote("Tour Quảng Ninh","Nhớ đón khách");
//        db.insertANote("Tour Bắc Lãm","Khách đăng kí đi chơi thêm");
        listNote = db.getDataNote();
        adapter = new NoteAdapter(listNote, this);
        lvListNote.setAdapter(adapter);
        lvListNote.setOnItemLongClickListener((parent, view, position, id) -> {
             itemSelected = position;
             return false;
        });
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(ManageNoteActivity.this,AddNote.class);
            startActivityForResult(intent,100);
        });
        btnBack.setOnClickListener(v -> finish());
        registerForContextMenu(lvListNote);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b;
        if (data != null) {
            b = data.getExtras();
            String title = b.getString("Title") != null ? b.getString("Title") : "";
            String description = b.getString("Description");
            Integer id = b.getInt("Id");
            Note note = new Note(title, description);
            if (requestCode == 100 && resultCode == 150) {
                //truong hop them
                listNote.add(note);
                db.insertANote(title, description);
            } else if (requestCode == 101 && resultCode == 150) {
                //truong hop sua
                listNote.set(itemSelected, note);
                db.updateANote(title,description,id.toString());
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lvlistNote) {
            getMenuInflater().inflate(R.menu.note_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuEdit:
                Intent intent = new Intent(ManageNoteActivity.this, AddNote.class);
                Note note = listNote.get(itemSelected);
                Bundle b = new Bundle();
                b.putInt("Id", note.getId());
                b.putString("Title", note.getTitle());
                b.putString("Description", note.getDescription());
                intent.putExtras(b);
                startActivityForResult(intent, 101);
                break;
            case R.id.mnuDelete:
                db.deleteANote(String.valueOf(listNote.get(itemSelected).getId()));
                listNote.remove(itemSelected);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}
