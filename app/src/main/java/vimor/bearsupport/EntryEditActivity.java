package vimor.bearsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDate;

public class EntryEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_edit);

        EditText entryTitleText = findViewById(R.id.editEditTextEntryTitle);
        EditText dateText = findViewById(R.id.editEditTextDate);
        EditText entryText = findViewById(R.id.editEditTextEntry);

        Button submitBtn = findViewById(R.id.edit_entry_button);

        Intent intent = getIntent();

        entryTitleText.setText(intent.getStringExtra("title"));
        dateText.setText(intent.getStringExtra("date"));
        entryText.setText(intent.getStringExtra("entry"));

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = entryTitleText.getText().toString();
                String entry = entryText.getText().toString();
                String dateString = dateText.getText().toString();

                boolean titleSet = true;
                boolean entrySet = true;
                boolean dateCorrect = true;

                try {
                    String[] segments = dateString.split("/");
                    if (segments.length != 3) {
                        throw new Exception("Invalid format");
                    }
                    System.out.println(Integer.parseInt(segments[0]));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalDate date = LocalDate.of(Integer.parseInt(segments[2]), Integer.parseInt(segments[1]), Integer.parseInt(segments[0]));
                    } else {
                        throw new Exception("SDK problem");
                    }
                } catch (Exception e) {
                    dateCorrect = false;
                }

                if (title.equals("")) {
                    titleSet = false;
                }
                if (entry.equals("")) {
                    entrySet = false;
                }

                if (titleSet && entrySet && dateCorrect) {
                    String[] segments = dateString.split("/");
                    LocalDate date = LocalDate.of(Integer.parseInt(segments[2]), Integer.parseInt(segments[1]), Integer.parseInt(segments[0]));

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(EntryEditActivity.this);

                    EntryModel entryModel = new EntryModel(intent.getIntExtra("id", -1), title, date, entry);

                    dataBaseHelper.editEntry(entryModel);

                    finish();
                } else {
                    if (!titleSet) {
                        entryTitleText.setHint("You need to set the title");
                        entryTitleText.setHintTextColor(Color.parseColor("#F73F3F"));
                    }
                    if (!entrySet) {
                        entryText.setHint("You need to write something");
                        entryText.setHintTextColor(Color.parseColor("#F73F3F"));
                    }
                    if (!dateCorrect) {
                        dateText.setHintTextColor(Color.parseColor("#F73F3F"));
                        dateText.setText("");
                    }
                }
            }
        });
    }
}