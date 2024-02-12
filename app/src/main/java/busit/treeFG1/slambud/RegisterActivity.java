package busit.treeFG1.slambud;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private EditText userNameInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        dbHelper = new DBHelper(this);

        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.password);
        confirmPasswordInput = findViewById(R.id.confirmPassword);

        Button btnRegister = findViewById(R.id.btnRegister);
        TextView textViewAlreadyHaveAccount = findViewById(R.id.textViewAlreadyHaveAccount);

        // Register onClickListener for the Register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        // Register onClickListener for the "Already have an Account?" text
        textViewAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click on "Already have an Account?"
                // Open the login activity
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish(); // Close the current activity
            }
        });
    }

    private void register() {
        // Retrieve input values
        String username = userNameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Check if any field is empty
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the username is already taken
        if (dbHelper.isUsernameTaken(username)) {
            Toast.makeText(this, "Username already taken. Choose a different one.", Toast.LENGTH_SHORT).show();
            userNameInput.setError("Username already taken");
            return;
        } else {
            userNameInput.setError(null);
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add the new user to the database
        long result = dbHelper.addUser(username, password);

        Log.d("Registration", "Result: " + result);  // Log statement added here

        if (result != -1) {
            // Registration successful
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish(); // Close the current activity
        } else {
            // Registration failed
            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

}
