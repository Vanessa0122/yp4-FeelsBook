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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    static ArrayList<Emotion> emotionList = new ArrayList<>();
    ArrayAdapter emotionAdaptor;

    // This function is for declaring and initializing the data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView instantDisplay = findViewById(R.id.displayInstantly);
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
        Emotion_Joy joy = new Emotion_Joy(new Date());

        emotionList.add(joy);

        emotionAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emotionList);
        //emoTasks = new EmotionTasks(getApplicationContext(), emotionList);
        instantDisplay.setAdapter(emotionAdaptor);

        instantDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int spot, long l) {
                Intent editStuff = new Intent(getApplicationContext(), Edit.class);
                editStuff.putExtra("emotionType", spot);
                startActivity(editStuff);
            }
        });

    }


    @Override
    protected void onStart(){
        super.onStart();
        load();
    }

    // Check which button was clicked
        @Override
        public void onClick(View view) {
            EditText comment = findViewById(R.id.comment);
            switch(view.getId()){
                case R.id.lButton:
                    Emotion_Love love = new Emotion_Love(new Date());
                    emotionList.add(love);
                    love.saveComment(comment.getText().toString());
                    //Refreshes the listview
                    emotionAdaptor.notifyDataSetChanged();
                    emoCalled();
                    save();
                    break;
                case R.id.jButton:
                    Emotion_Joy joy = new Emotion_Joy(new Date());
                    emotionList.add(joy);
                    joy.saveComment(comment.getText().toString());
                    emoCalled();
                    save();
                    break;
                case R.id.spsButton:
                    Emotion_Surprise surprise = new Emotion_Surprise(new Date());
                    surprise.saveComment(comment.getText().toString());
                    emoCalled();
                    emotionList.add(surprise);
                    save();
                    break;
                case R.id.aButton:
                    Emotion_Anger anger = new Emotion_Anger(new Date());
                    anger.saveComment(comment.getText().toString());
                    emotionList.add(anger);
                    save();
                    emoCalled();
                    break;
                case R.id.sadButton:
                    Emotion_Sadness sadness = new Emotion_Sadness(new Date());
                    emotionList.add(sadness);
                    sadness.saveComment(comment.getText().toString());
                    emoCalled();
                    save();
                    break;
                case R.id.fButton:
                    Emotion_Fear fear = new Emotion_Fear(new Date());
                    emotionList.add(fear);
                    fear.saveComment(comment.getText().toString());
                    emoCalled();
                    save();
                    break;
                case R.id.hisButton:
                    openHistoryActivity();
            }
        }



    //If checking the history button is clicked, we are redirecting the user to a new activity page
    public void openHistoryActivity(){
        Intent intent = new Intent(getApplicationContext(), HistoryPage.class);
        startActivity(intent);
    }


    private void load() {

    }

    private void emoCalled(){
//        save();
//        if (instantDisplay.getText().toString().trim().length() == 0) {
//            moveToDetails();
//        }
//        else {
//            //sort
//            instantDisplay.getText().clear();
//        }
    }


    public void save(){
        SharedPreferences sharedPreferences = getSharedPreferences("FILENAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.emotionList);
        editor.putString("Emotion list", json);
        editor.apply();
    }
}

