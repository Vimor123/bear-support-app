package vimor.bearsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class EntryViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_view);

        TextView titleText = findViewById(R.id.entryViewTitleText);
        TextView dateText = findViewById(R.id.entryViewDateText);
        TextView entryText = findViewById(R.id.entryViewEntryText);

        Intent intent = getIntent();

        titleText.setText(intent.getStringExtra("title"));
        dateText.setText(intent.getStringExtra("date"));
        entryText.setText(intent.getStringExtra("entry"));

        entryText.setMovementMethod(new ScrollingMovementMethod());
    }
}