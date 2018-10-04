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

    private static final String FILENAME = "file.sav";
    static ArrayList<Emotion> emotionList = new ArrayList<>();
    EmotionTasks emoTasks;
    ListView instantDisplay = findViewById(R.id.displayInstantly);
    Button loveButton = findViewById(R.id.lButton);
    Button joyButton = findViewById(R.id.jButton);
    Button surpriseButton = findViewById(R.id.spsButton);
    Button angerButton = findViewById(R.id.aButton);
    Button sadnessButton = findViewById(R.id.sadButton);
    Button fearButton = findViewById(R.id.fButton);
    Button historyButton = findViewById(R.id.hisButton);
    EditText comment = findViewById(R.id.comment);
    // This function is for declaring and initializing the data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loveButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        angerButton.setOnClickListener(this);
        sadnessButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        instantDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int spot, long l) {
                Intent editStuff = new Intent(getApplicationContext(), Edit_Previous_Emotion.class);
                editStuff.putExtra("emotionIndex", spot);
                startActivity(editStuff);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        load();
        emoTasks = new EmotionTasks(getApplicationContext(), emotionList);
        instantDisplay.setAdapter(emoTasks);
    }

    // Check which button was clicked
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.lButton:
                    Emotion_Love love = new Emotion_Love(new Date());
                    emotionList.add(love);
                    emoTasks.change();
                    love.setComment(comment.getText().toString());
                    emoCalled();
                    save();
                    break;
                case R.id.jButton:
                    Emotion_Joy joy = new Emotion_Joy(new Date());
                    emotionList.add(joy);
                    save();
                    break;
                case R.id.spsButton:
                    Emotion_Surprise surprise = new Emotion_Surprise(new Date());
                    emotionList.add(surprise);
                    save();
                    break;
                case R.id.aButton:
                    Emotion_Anger anger = new Emotion_Anger(new Date());
                    emotionList.add(anger);
                    save();
                    break;
                case R.id.sadButton:
                    Emotion_Sadness sadness = new Emotion_Sadness(new Date());
                    emotionList.add(sadness);
                    save();
                    break;
                case R.id.fButton:
                    Emotion_Fear fear = new Emotion_Fear(new Date());
                    emotionList.add(fear);
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
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Emotion_Joy>>(){}.getType();
            emotionList = gson.fromJson(in, listType);

            } catch (FileNotFoundException e) {
                emotionList = new ArrayList<Emotion>();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

    private void emoCalled(){
        save();
        if (instantDisplay.getText().toString().trim().length() == 0) {
            moveToDetails();
        }
        else {
            //sort
            emoTasks.change();
            instantDisplay.getText().clear();
        }
    }

    // Method to move to the specific view of each emotion
    // Allows the user to modify new emotions
    // IMPORTANT: This method should only called for the most-recently added item
    private void moveToDetails(){
        int index = emotionList.size()-1;
        Intent intent = new Intent(getApplicationContext(), Edit_Previous_Emotion.class);
        intent.putExtra("emotionOrder", index);
        startActivity(intent);
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

