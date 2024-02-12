package busit.treeFG1.slambud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText userNameInput;
    private EditText passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        dbHelper = new DBHelper(this);

        userNameInput = findViewById(R.id.user);
        passwordInput = findViewById(R.id.password);


        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignup = findViewById(R.id.btnSignup);

        // Register onClickListener for the Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        // Register onClickListener for the "Don't have an account?" text
         btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click on "Don't have an account?"
                // Open the registration activity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish(); // Close the current activity
            }
        });


    }

    private void login() {
        String enteredUsername = userNameInput.getText().toString().trim();
        String enteredPassword = passwordInput.getText().toString().trim();

        if (dbHelper.checkUser(enteredUsername, enteredPassword)) {

            Toast.makeText(this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, Home.class);
            intent.putExtra("USERNAME_KEY", enteredUsername);
            startActivity(intent);

        } else {

            Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
        }
    }
}
