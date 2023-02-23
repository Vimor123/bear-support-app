package vimor.bearsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class RandomBearsActivity extends AppCompatActivity {

    private Random rand = new Random();

    public void setNewBear() {
        int[] bearImageIds = {
                R.drawable.badonklin,
                R.drawable.biggiant,
                R.drawable.biggiant2,
                R.drawable.cowboy,
                R.drawable.cuddly,
                R.drawable.daddy,
                R.drawable.dreamy,
                R.drawable.drink,
                R.drawable.handsome,
                R.drawable.neonboof1,
                R.drawable.neonboof2,
                R.drawable.older,
                R.drawable.oscar,
                R.drawable.oscar2,
                R.drawable.oscarbear,
                R.drawable.sexy,
                R.drawable.thiccnd,
                R.drawable.tired
        };

        int randomIndex = rand.nextInt(bearImageIds.length);

        ImageView bearImage = (ImageView) findViewById(R.id.bearImageView);
        bearImage.setImageResource(bearImageIds[randomIndex]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_bears);
        setNewBear();
    }

    public void newBear(View view) {
        setNewBear();
    }
}