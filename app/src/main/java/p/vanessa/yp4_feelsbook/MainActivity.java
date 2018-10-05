
/*
FeelsBook: Quickly record, edit and view your emotions.
Copyright (C) 2018 Vanessa Peng yp4@ualberta.ca

 */

/* RESOURCES:
Activities
https://www.youtube.com/watch?v=AD5qt7xoUU8
loading and save file:
https://www.youtube.com/watch?v=EcfUkjlL9RI - coding in flow
 */


package p.vanessa.yp4_feelsbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //private static final String FILENAME = "file.sav";
    //List of Emotions entered
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


        loveButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        angerButton.setOnClickListener(this);
        sadnessButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);

        emotionAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emotionList);
        instantDisplay.setAdapter(emotionAdaptor);
        instantDisplay.setBackgroundColor(Color.CYAN);
        //load();
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
    protected void onStart() {
        super.onStart();
        //load();
    }


    // This TextViews are for displaying emotion counts
//    TextView loveView = findViewById(R.id.lcount);
//    TextView joyView = findViewById(R.id.jcount);
//    TextView surpriseView = findViewById(R.id.spscount);
//    TextView angerView = findViewById(R.id.acount);
//    TextView sadnessView = findViewById(R.id.scount);
//    TextView fearView = findViewById(R.id.fcount);
//
    Integer count_sad = 0;
    Integer count_joy = 0;
    Integer count_fear = 0;
    Integer count_anger = 0;
    Integer count_surprise = 0;
    Integer count_love = 0;
    // Check which button was clicked
    @Override
    public void onClick(View view) {
        EditText comment = findViewById(R.id.comment);
        //Switch Cases Between the Buttons
        switch (view.getId()) {
            //Button Pressed
            //Creates new object
            //Save comment
            //Adds the emotion to the emotionAraay in Emotion class
            //Save it to the file
            case R.id.lButton:
                Emotion_Love love = new Emotion_Love(new Date());
                love.saveComment(comment.getText().toString());
                emotionList.add(love);
                emotionAdaptor.notifyDataSetChanged();
                //loveView.setText(count_love+=1);
                save();
                break;
            case R.id.jButton:
                Emotion_Joy joy = new Emotion_Joy(new Date());
                joy.saveComment(comment.getText().toString());
                emotionList.add(joy);
                emotionAdaptor.notifyDataSetChanged();
                //joyView.setText();
                //save();
                break;
            case R.id.spsButton:
                Emotion_Surprise surprise = new Emotion_Surprise(new Date());
                surprise.saveComment(comment.getText().toString());
                emotionList.add(surprise);
                emotionAdaptor.notifyDataSetChanged();
                //surpriseView.setText();
                //save();
                break;
            case R.id.aButton:
                Emotion_Anger anger = new Emotion_Anger(new Date());
                anger.saveComment(comment.getText().toString());
                emotionList.add(anger);
                emotionAdaptor.notifyDataSetChanged();
                //angerView.setText();
                //save();
                break;
            case R.id.sadButton:
                Emotion_Sadness sadness = new Emotion_Sadness(new Date());
                sadness.saveComment(comment.getText().toString());
                emotionList.add(sadness);
                emotionAdaptor.notifyDataSetChanged();
                //sadnessView.setText());
                //save();
                break;
            case R.id.fButton:
                Emotion_Fear fear = new Emotion_Fear(new Date());
                fear.saveComment(comment.getText().toString());
                emotionList.add(fear);
                emotionAdaptor.notifyDataSetChanged();
                //fearView.setText();
                //save();
                //save(comment.getText().toString(), fear.getDate());
                break;
        }
    }



    private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(emotionList);
        editor.putString("Emotion List", json);
        editor.apply();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Emotion List", null);
        Type type = new TypeToken<ArrayList<Emotion_Joy>>() {}.getType();
        emotionList = gson.fromJson(json, type);
        if (emotionList == null) {
            emotionList = new ArrayList<>();
        }
    }


}

