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
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private static final String FILE_NAME = "data.txt";
    EditText mEditText;
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
        Button saveButton = findViewById(R.id.savButton);
        mEditText = findViewById(R.id.commentToast);

        loveButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        angerButton.setOnClickListener(this);
        sadnessButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

    }

    // After the user clicked the emotion button, the program leads them to the toast
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.lButton:
                showToast();
                break;
            case R.id.jButton:
                showToast();
                break;
            case R.id.spsButton:
                showToast();
                break;
            case R.id.aButton:
                showToast();
                break;
            case R.id.sadButton:
                showToast();
                break;
            case R.id.fButton:
                showToast();
                break;
//            case R.id.hisButton:
//                openHistoryActivity(View v);
//            case R.id.savButton:
//                save(View v);
        }
    }

    // After the user clicked on the emotion button, they are given a toast to enter comment
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

    // After the user enters comment and hit the save button, the program saves it in the history
    public void save(View v){
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // Leads to the History Activity Page
    public void openHistoryActivity(View v){

        // Direct the user to the new activity
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);

        // Start to load the history to the page
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}
