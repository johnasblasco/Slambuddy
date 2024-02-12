package busit.treeFG1.slambud;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;  // Import Log class
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;

    Button logout;
    FloatingActionButton Buttons;
    DBHelper dbHelper;
    ArrayList<String> answer_id, user_id, friends_id, question_id, answer_text1, answer_text2, answer_text3,
            answer_text4, answer_text5, answer_text6, answer_text7, answer_text8, answer_text9, answer_text10,
            answer_text11, answer_text12, answer_text13, answer_text14, answer_text15;
    CustomAdapter customAdapter;
    
    @Override
    protected void onResume() {
        super.onResume();
        // Clear existing data lists
        answer_id.clear();
        user_id.clear();
        friends_id.clear();
        question_id.clear();
        answer_text1.clear();
        answer_text2.clear();
        answer_text3.clear();
        answer_text4.clear();
        answer_text5.clear();
        answer_text6.clear();
        answer_text7.clear();
        answer_text8.clear();
        answer_text9.clear();
        answer_text10.clear();
        answer_text11.clear();
        answer_text12.clear();
        answer_text13.clear();
        answer_text14.clear();
        answer_text15.clear();
        // Reload data from the database
        storedatainArrays();
        // Notify the adapter that the data set has changed
        customAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Log.d("AddActivity", "Layout set successfully");
        recyclerView = findViewById(R.id.recyclerView);
        Buttons = findViewById(R.id.floatingActionButton);

        Buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the existing Intent that started the AddActivity
                Intent intent = getIntent();
                if (intent != null) {
                    // Retrieve the username from the existing Intent
                    String actualUsername = intent.getStringExtra("USERNAME_KEY");
                    // Create a new Intent for ActivityQuestionnaire and pass the username
                    Intent nextIntent = new Intent(Home.this, InputActivity.class);
                    nextIntent.putExtra("USERNAME_KEY", actualUsername);
                        startActivity(nextIntent);
                }
            }
        });


        dbHelper = new DBHelper(Home.this);
        answer_id = new ArrayList<>();
        user_id = new ArrayList<>();
        friends_id = new ArrayList<>();
        question_id = new ArrayList<>();
        answer_text1 = new ArrayList<>();
        answer_text2 = new ArrayList<>();
        answer_text3 = new ArrayList<>();
        answer_text4 = new ArrayList<>();
        answer_text5 = new ArrayList<>();
        answer_text6 = new ArrayList<>();
        answer_text7 = new ArrayList<>();
        answer_text8 = new ArrayList<>();
        answer_text9 = new ArrayList<>();
        answer_text10 = new ArrayList<>();
        answer_text11 = new ArrayList<>();
        answer_text12 = new ArrayList<>();
        answer_text13 = new ArrayList<>();
        answer_text14 = new ArrayList<>();
        answer_text15 = new ArrayList<>();
                storedatainArrays();

                //ADAPTER HERE nigga
        customAdapter = new CustomAdapter(Home.this, answer_id, answer_text1,answer_text2);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
    }
    void storedatainArrays() {
        Cursor cursor = dbHelper.readallData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Log.d("AddActivity", "Storing data in arrays");
                answer_id.add(cursor.getString(0));
                user_id.add(cursor.getString(1));
                friends_id.add(cursor.getString(2));
                question_id.add(cursor.getString(3));
                answer_text1.add(cursor.getString(4));
                answer_text2.add(cursor.getString(5));
                answer_text3.add(cursor.getString(6));
                answer_text4.add(cursor.getString(7));
                answer_text5.add(cursor.getString(8));
                answer_text6.add(cursor.getString(9));
                answer_text7.add(cursor.getString(10));
                answer_text8.add(cursor.getString(11));
                answer_text9.add(cursor.getString(12));
                answer_text10.add(cursor.getString(13));
                answer_text10.add(cursor.getString(14));
                answer_text10.add(cursor.getString(15));
                answer_text10.add(cursor.getString(16));
                answer_text10.add(cursor.getString(17));
                answer_text10.add(cursor.getString(18));
            }
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Clear all data from the database
                dbHelper.deleteAllData();

                // Clear all data from the adapter
                answer_id.clear();
                answer_text1.clear();
                answer_text2.clear();
                // Clear other corresponding data if needed

                // Notify the adapter that the data set has changed
                customAdapter.notifyDataSetChanged();

                // Show a toast message indicating data deletion
                Toast.makeText(Home.this, "All data deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", null); // Handle cancel button click if needed
        builder.show();
    }


}