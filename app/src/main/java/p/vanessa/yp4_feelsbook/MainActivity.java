
/*
FeelsBook: Quickly record, edit and view your emotions.
Copyright (C) 2018 Vanessa Peng yp4@ualberta.ca

 */

/*
loading and save file:
https://www.youtube.com/watch?v=EcfUkjlL9RI - coding in flow
 */




package p.vanessa.yp4_feelsbook;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.view.Display;
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
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FILENAME = "file.sav";
    //List of Emotions entered
    static ArrayList<Emotion> emotionList = new ArrayList<>();
    ArrayAdapter emotionAdaptor;

    // This TextViews are for displaying emotion counts
    TextView loveView = findViewById(R.id.lcount);
    TextView joyView = findViewById(R.id.jcount);
    TextView surpriseView = findViewById(R.id.spscount);
    TextView angerView = findViewById(R.id.acount);
    TextView sadnessView = findViewById(R.id.scount);
    TextView fearView = findViewById(R.id.fcount);



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
        //historyButton.setOnClickListener(this);

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
                Emotion.addEmotion(love.getEmotionType());
                emotionAdaptor.notifyDataSetChanged();
                loveView.setText(Emotion.getCount(love.getEmotionType().toString()));
                save();
                break;
            case R.id.jButton:
                Emotion_Joy joy = new Emotion_Joy(new Date());
                joy.saveComment(comment.getText().toString());
                Emotion.addEmotion(joy.getEmotionType());
                //emotionList.add(joy);
                emotionAdaptor.notifyDataSetChanged();
                joyView.setText(Emotion.getCount(joy.getEmotionType().toString()));
                //save();
                break;
            case R.id.spsButton:
                Emotion_Surprise surprise = new Emotion_Surprise(new Date());
                surprise.saveComment(comment.getText().toString());
                Emotion.addEmotion(surprise.getEmotionType());
                //emotionList.add(surprise);
                emotionAdaptor.notifyDataSetChanged();
                surpriseView.setText(Emotion.getCount(surprise.getEmotionType().toString()));
                //save();
                break;
            case R.id.aButton:
                Emotion_Anger anger = new Emotion_Anger(new Date());
                anger.saveComment(comment.getText().toString());
                Emotion.addEmotion(anger.getEmotionType());
                //emotionList.add(anger);
                emotionAdaptor.notifyDataSetChanged();
                angerView.setText(Emotion.getCount(anger.getEmotionType().toString()));
                //save();
                break;
            case R.id.sadButton:
                Emotion_Sadness sadness = new Emotion_Sadness(new Date());
                sadness.saveComment(comment.getText().toString());
                Emotion.addEmotion(sadness.getEmotionType());
                //emotionList.add(sadness);
                emotionAdaptor.notifyDataSetChanged();
                sadnessView.setText(Emotion.getCount(sadness.getEmotionType().toString()));
                //save();
                break;
            case R.id.fButton:
                Emotion_Fear fear = new Emotion_Fear(new Date());
                fear.saveComment(comment.getText().toString());
                Emotion.addEmotion(fear.getEmotionType());
                //emotionList.add(fear);
                emotionAdaptor.notifyDataSetChanged();
                fearView.setText(Emotion.getCount(fear.getEmotionType().toString()));
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


//    private String[] load() {
//        ArrayList<String> emotions = new ArrayList<String>();
//        try {
//            FileInputStream fis = openFileInput(FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            String line = in.readLine();
//            while (line != null) {
//                emotions.add(line);
//                line = in.readLine();
//            }
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return emotions.toArray(new String[emotions.size()]);
//    }
//
//    private void save(String text, Date date) {
//        try {
//            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
//            fos.write(new String(date.toString() + " | " + text)
//                    .getBytes());
//            fos.close();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
}

