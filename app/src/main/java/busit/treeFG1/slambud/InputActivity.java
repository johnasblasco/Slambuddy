package busit.treeFG1.slambud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class InputActivity extends AppCompatActivity {
    public static Button btnNext, btnBack;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);


        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextNick = findViewById(R.id.editTextNick);
        EditText editTextBirthday = findViewById(R.id.editTextBirthday);
        EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);
        EditText editTextFavColor = findViewById(R.id.editTextFavColor);
        EditText editTextFavFood = findViewById(R.id.editTextFavFood);
        EditText editTextFavSong = findViewById(R.id.editTextFavSong);
        EditText editTextFavMovie = findViewById(R.id.editTextFavMovie);
        EditText editFiveYears = findViewById(R.id.editFiveYears);
        EditText editTextKids = findViewById(R.id.editTextKids);
        EditText editTextLive = findViewById(R.id.editTextLive);
        EditText editTextDeath = findViewById(R.id.editTextDeath);
        EditText editTextAdvice = findViewById(R.id.editTextAdvice);
        EditText editTextQoutes = findViewById(R.id.editTextQoutes);
        EditText editTextSecrets = findViewById(R.id.editTextSecrets);

        btnNext = findViewById(R.id.btnSubmit);
        btnBack = findViewById(R.id.btnBackq);

        Button btnNext = findViewById(R.id.btnSubmit);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value1 = editTextName.getText().toString();
                String value2 = editTextNick.getText().toString();
                String value3 = editTextBirthday.getText().toString();
                String value4 = editTextContactNumber.getText().toString();
                String value5 = editTextFavColor.getText().toString();
                String value6 = editTextFavFood.getText().toString();
                String value7 = editTextFavSong.getText().toString();
                String value8 = editTextFavMovie.getText().toString();
                String value9 = editFiveYears.getText().toString();
                String value10 = editTextKids.getText().toString();
                String value11 = editTextLive.getText().toString();
                String value12 = editTextDeath.getText().toString();
                String value13 = editTextAdvice.getText().toString();
                String value14 = editTextQoutes.getText().toString();
                String value15 = editTextSecrets.getText().toString();

                // Check if any of the EditText fields are empty
                if (value1.isEmpty() || value2.isEmpty() || value3.isEmpty() || value4.isEmpty() || value5.isEmpty()
                        || value6.isEmpty() || value7.isEmpty() || value8.isEmpty() || value9.isEmpty() || value10.isEmpty()
                        || value11.isEmpty() || value12.isEmpty() || value13.isEmpty() || value14.isEmpty() || value15.isEmpty()) {
                    // Show a message indicating that all fields must be filled
                    Toast.makeText(InputActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                } else {
                    // All fields are filled, proceed with adding data to the database
                    Intent intent = getIntent();
                    String actualUsername = intent.getStringExtra("USERNAME_KEY");
                    long userId = dbHelper.getUserIdByUsername(actualUsername);
                    long questionId = dbHelper.addQuestions();
                    long friendId = dbHelper.addFriend(value1);

                    // Now, you have the friendId, username, and questionText, you can add answers to the ANSWER table
                    dbHelper.addbtn((int) userId, (int) friendId, (int) questionId, value1, value2, value3, value4, value5,
                            value6, value7, value8, value9, value10, value11, value12, value13, value14, value15);

                    Toast.makeText(InputActivity.this, "Data Added to Database", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(InputActivity.this, Home.class);
                    startActivity(homeIntent);
                    // finish();  Optional: finish the current activity to prevent going back with back button
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, Home.class);
                startActivity(intent);
            }
        });
    }

}
