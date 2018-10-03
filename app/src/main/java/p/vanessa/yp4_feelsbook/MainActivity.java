package p.vanessa.yp4_feelsbook;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    static ArrayList<Emotion> emotionList = new ArrayList<>();


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
                    showToast();
                    Emotion_Love love = new Emotion_Love(new Date());
                    emotionList.add(love);
                    //save();
                    break;
                case R.id.jButton:
                    showToast();
                    Emotion_Joy joy = new Emotion_Joy(new Date());
                    emotionList.add(joy);
                    //save();
                    break;
                case R.id.spsButton:
                    showToast();
                    Emotion_Surprise surprise = new Emotion_Surprise(new Date());
                    emotionList.add(surprise);
                    //save();
                    break;
                case R.id.aButton:
                    showToast();
                    Emotion_Anger anger = new Emotion_Anger(new Date());
                    emotionList.add(anger);
                    //save();
                    break;
                case R.id.sadButton:
                    showToast();
                    Emotion_Sadness sadness = new Emotion_Sadness(new Date());
                    emotionList.add(sadness);
                    //save();
                    break;
                case R.id.fButton:
                    showToast();
                    Emotion_Fear fear = new Emotion_Fear(new Date());
                    emotionList.add(fear);
                    //save();
                    break;
                case R.id.hisButton:
                    openHistoryActivity();
            }
        }

    protected void onStart() {
        super.onStart();
        //save();
    }


    private void showToast() {
        Toast.makeText(this, "Recorded", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toasty));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    //If checking the history button is clicked, we are redirecting the user to a new activity page
    public void openHistoryActivity(){
        Intent intent = new Intent(getApplicationContext(), HistoryPage.class);
        startActivity(intent);
    }

//    public void save(){
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(MainActivity.emotionList);
//        editor.putString("task list", json);
//        editor.apply();
//    }
//}
//
//



}