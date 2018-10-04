package p.vanessa.yp4_feelsbook;

import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Edit_Previous_Emotion extends AppCompatActivity {

    int emotionIndex;
    static timeTasks timeTasks = new timeTasks();
    String emotionType;
    Button saveButton;
    Button deleteButton;
    EditText editDate;
    EditText  editEmotion;
    EditText editComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_previous_emotion);


        // Get the new change on the screen
        final EditText FeditComment = findViewById(R.id.editComment);
        Intent intentComment = getIntent();

        final EditText FeditEmotion= findViewById(R.id.editEmotion);
        Intent intentEmotion = getIntent();

        final EditText FeditDate = findViewById(R.id.editDate);
        Intent intentDate = getIntent();


        //Find the ID for the text boxes
        editDate = findViewById(R.id.editDate);
        editComment = findViewById(R.id.editComment);
        editEmotion = findViewById(R.id.editEmotion);

        emotionIndex = intentEmotion.getIntExtra("emotionOrder", -1);
        emotionType = intentComment.getStringExtra("comment");



        // Populate the spinner, the comment section. and the date
        // This will vary based on whether the user clicked on an existing
        // emotion or decided to add in a new one
        List<Emotion> List = new ArrayList<>();
        if (emotionIndex != -1){
            Emotion emotion = MainActivity.emotionList.get(emotionIndex);
            List.add(emotion);
            editDate.setText(timeTasks.format(
                    MainActivity.emotionList.get(emotionIndex).getDate()));
            editComment.setText(MainActivity.emotionList.get(emotionIndex).getComment());
        }



        // When the save Button is pressed
        // Will save all the changes
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editComment.getText().toString();
                String Emotion = editEmotion.getText().toString();

                if (emotionIndex != -1){
                    // If the object is an existing one
                    // Simply update the time and the comment
                    // DO NOT let the user change the actual emotion
                    Emotion emotion = MainActivity.emotionList.get(emotionIndex);
                    emotion.setComment(comment);
                    if (checkDateFormat(editDate.getText().toString())) {
                        emotion.setDate(timeTasks.recordTime((editDate.getText().toString())));
                        recorded();
                        saveChange();
                        openMainActivity();
                    }
                    else {
                        toss();
                    }
                }
            }
        });


        // Set a listener for delete
        // Will only delete an object that already exists, otherwise, it will just redirect to main
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emotionIndex != -1){
                    MainActivity.emotionList.remove(MainActivity.emotionList.get(emotionIndex));
                    saveChange();
                }
                openMainActivity();
            }
        });
    }

    // If saving an emotion is successful
    public void recorded(){
        Toast.makeText(Edit_Previous_Emotion.this, "Recorded",
                Toast.LENGTH_LONG).show();
    }

    // If saving an emotion fails
    public void toss(){
        Toast.makeText(Edit_Previous_Emotion.this, "Invalid format",
                Toast.LENGTH_LONG).show();
    }

    // Used to open the main Activity
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    // We can only save the date when the format is correct
    public Boolean checkDateFormat(String date) {
        try {
            DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            timeFormat.parse(date);
            Date finalResult = timeFormat.parse(date);
            Date currentTime = new Date();
            if (currentTime.after(finalResult) || currentTime.equals(finalResult)){
                return true;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }


    public void saveChange(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.emotionList);
        editor.putString("toDo", json);
        editor.apply();
    }
}
