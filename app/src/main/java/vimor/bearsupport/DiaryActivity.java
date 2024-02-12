package vimor.bearsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
    }

    public void launchAddEntry(View view) {
        Intent intent = new Intent(this, AddEntryActivity.class);
        startActivity(intent);
    }
}