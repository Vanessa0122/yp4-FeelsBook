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


public class Edit extends AppCompatActivity {

    int emotionType;
    static timeTasks timeTasks = new timeTasks();
    Button saveButton;
    Button deleteButton;
    EditText editDate;
    EditText  editEmotion;
    EditText editComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();

        //Find the ID for the text boxes
        editDate = findViewById(R.id.editDate);
        editComment = findViewById(R.id.editComment);
        editEmotion = findViewById(R.id.editEmotion);
        saveButton = findViewById(R.id.savebutton);
        emotionType = intent.getIntExtra("emotionType", -99);

        editDate.setText(timeTasks.format(
                MainActivity.emotionList.get(emotionType).getDate()));
        editComment.setText(MainActivity.emotionList.get(emotionType).getComment());
        editEmotion.setText(MainActivity.emotionList.get(emotionType).toString());

        // When the save Button is pressed
        // Will save all the changes
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editComment.getText().toString();

                if (emotionType != -1) {
                    // If the object is an existing one
                    // Simply update the time and the comment
                    // DO NOT let the user change the actual emotion
                    Emotion emotionList = MainActivity.emotionList.get(emotionType);
                    emotionList.saveComment(comment);

                }
            }
        });
    }
}

//        public void save(){
//            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            Gson gson = new Gson();
//            String json = gson.toJson(MainActivity.emotionList);
//            editor.putString("save", json);
//            editor.apply();
//        }

