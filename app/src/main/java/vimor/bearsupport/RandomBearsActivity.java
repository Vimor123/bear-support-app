package vimor.bearsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RandomBearsActivity extends AppCompatActivity {

    public void setNewBear() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_bears);
    }

    public void newBear(View view) {
        setNewBear();
    }
}