package vimor.bearsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DiaryActivity.this);
        List<EntryModel> entries = dataBaseHelper.getAllEntries();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EntryAdapter entryAdapter = new EntryAdapter(getApplicationContext(), entries);
        recyclerView.setAdapter(entryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DiaryActivity.this);
        List<EntryModel> entries = dataBaseHelper.getAllEntries();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EntryAdapter entryAdapter = new EntryAdapter(getApplicationContext(), entries);
        recyclerView.setAdapter(entryAdapter);
    }

    public void launchAddEntry(View view) {
        Intent intent = new Intent(this, AddEntryActivity.class);
        startActivity(intent);
    }
}