package busit.treeFG1.slambud;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class updateActivity extends AppCompatActivity {
    EditText editTextName_update, editTextNick_update, editTextBirthday_update,
            editTextContactNumber_update, editTextFavColor_update, editTextFavFood_update,
            editTextFavSong_update, editTextFavMovie_update, editFiveYears_update,
            editTextKids_update, editTextLive_update, editTextDeath_update,
            editTextAdvice_update, editTextQoutes_update, editTextSecrets_update;
    Button update, back2, delete;
    DBHelper dbHelper;
    String Name, Nickname;
    int answerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        dbHelper = new DBHelper(this);

        editTextName_update = findViewById(R.id.editTextName_update);
        editTextNick_update = findViewById(R.id.editTextNick_update);
        editTextBirthday_update = findViewById(R.id.editTextBirthday_update);
        editTextContactNumber_update = findViewById(R.id.editTextContactNumber_update);
        editTextFavColor_update = findViewById(R.id.editTextFavColor_update);
        editTextFavFood_update = findViewById(R.id.editTextFavFood_update);
        editTextFavSong_update = findViewById(R.id.editTextFavSong_update);
        editTextFavMovie_update = findViewById(R.id.editTextFavMovie_update);
        editFiveYears_update = findViewById(R.id.editTextFiveYears_update);
        editTextKids_update = findViewById(R.id.editTextKids_update);
        editTextLive_update = findViewById(R.id.editTextLive_update);
        editTextDeath_update = findViewById(R.id.editTextDeath_update);
        editTextAdvice_update = findViewById(R.id.editTextAdvice_update);
        editTextQoutes_update = findViewById(R.id.editTextQoutes_update);
        editTextSecrets_update = findViewById(R.id.editTextSecrets_update);

        update = findViewById(R.id.btnupdate_update);
        back2 = findViewById(R.id.btnBack);
        delete = findViewById(R.id.btndeletes_update);

        gettingIntentData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans1 = editTextName_update.getText().toString();
                String ans2 = editTextNick_update.getText().toString();
                String ans3 = editTextBirthday_update.getText().toString();
                String ans4 = editTextContactNumber_update.getText().toString();
                String ans5 = editTextFavColor_update.getText().toString();
                String ans6 = editTextFavFood_update.getText().toString();
                String ans7 = editTextFavSong_update.getText().toString();
                String ans8 = editTextFavMovie_update.getText().toString();
                String ans9 = editFiveYears_update.getText().toString();
                String ans10 = editTextKids_update.getText().toString();
                String ans11 = editTextLive_update.getText().toString();
                String ans12 = editTextDeath_update.getText().toString();
                String ans13 = editTextAdvice_update.getText().toString();
                String ans14 = editTextQoutes_update.getText().toString();
                String ans15 = editTextSecrets_update.getText().toString();

                dbHelper.updateData(answerId, ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9,
                        ans10, ans11, ans12, ans13, ans14, ans15);

                Intent intent = new Intent(updateActivity.this, Home.class);
                startActivity(intent);
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateActivity.this, Home.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    private void gettingIntentData() {
        answerId = getIntent().getIntExtra("id", -1);
        Log.d("updateActivity", "Answer ID: " + answerId);

        Name = getIntent().getStringExtra("Name");
        Nickname = getIntent().getStringExtra("Nickname");

        Cursor cursor = dbHelper.getAnswerById(answerId);

        if (cursor != null && cursor.moveToFirst()) {
            String[] answerTexts = new String[15];
            for (int i = 0; i < 15; i++) {
                int columnIndex = cursor.getColumnIndex("answer_text" + (i + 1));
                if (columnIndex != -1) {
                    answerTexts[i] = cursor.getString(columnIndex);
                } else {
                    answerTexts[i] = ""; // Or any default value you prefer
                }
            }
            editTextName_update.setText(answerTexts[0]);
            editTextNick_update.setText(answerTexts[1]);
            editTextBirthday_update.setText(answerTexts[2]);
            editTextContactNumber_update.setText(answerTexts[3]);
            editTextFavColor_update.setText(answerTexts[4]);
            editTextFavFood_update.setText(answerTexts[5]);
            editTextFavSong_update.setText(answerTexts[6]);
            editTextFavMovie_update.setText(answerTexts[7]);
            editFiveYears_update.setText(answerTexts[8]);
            editTextKids_update.setText(answerTexts[9]);
            editTextLive_update.setText(answerTexts[10]);
            editTextDeath_update.setText(answerTexts[11]);
            editTextAdvice_update.setText(answerTexts[12]);
            editTextQoutes_update.setText(answerTexts[13]);
            editTextSecrets_update.setText(answerTexts[14]);

            cursor.close();
        } else {
            Log.e("updateActivity", "Cursor is null or empty");
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Name + " ?");
        builder.setMessage("Are you sure you want to delete " + Name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.deleteOneRow(String.valueOf(answerId));
                finish(); // Finish the activity after deletion
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });
        builder.create().show();
    }
}
