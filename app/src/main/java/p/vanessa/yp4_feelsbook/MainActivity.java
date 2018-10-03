package p.vanessa.yp4_feelsbook;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    // This function is for declaring and initializing the data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loveButton = findViewById(R.id.lButton);
        Button joyButton = findViewById(R.id.jButton);
        Button surpriseButton = findViewById(R.id.spsButton);
        Button angerButton = findViewById(R.id.aButton);
        Button sadnessButton = findViewById(R.id.sadButton);
        Button fearButton = findViewById(R.id.fButton);
        Button historyButton = findViewById(R.id.hisButton);

        loveButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        angerButton.setOnClickListener(this);
        sadnessButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        }

        // Check which button was clicked
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.lButton:
                    addEmotionCount();
                    break;
                case R.id.jButton:
                    addEmotionCount();
                    break;
                case R.id.spsButton:
                    addEmotionCount();
                    break;
                case R.id.aButton:
                    addEmotionCount();
                    break;
                case R.id.sadButton:
                    addEmotionCount();
                    break;
                case R.id.fButton:
                    addEmotionCount();
                    break;
                case R.id.hisButton:
                    openHistoryActivity();
            }
        }

  public void addEmotionCount(){
        
  }


//        // If the emotion button is clicked, we are showing a toast for the user to enter comment
//        private void showToast() {
////            Toast.makeText(this, "Recorded", Toast.LENGTH_SHORT).show();
//            LayoutInflater inflater = getLayoutInflater();
//            View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toasty));
//            Toast toast = new Toast(getApplicationContext());
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            toast.setView(layout);
//            toast.show();
//        }

        //If checking the history button is clicked, we are redirecting the user to a new activity page
        public void openHistoryActivity(){
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        }
    }