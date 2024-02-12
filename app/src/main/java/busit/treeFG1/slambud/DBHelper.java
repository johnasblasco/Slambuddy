    package busit.treeFG1.slambud;

    import android.content.ContentValues;
    import android.content.Context;
    import android.content.SharedPreferences;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;
    import android.widget.Toast;

    public class DBHelper extends SQLiteOpenHelper {
        private Context context;

        // create slambud db
        private static final String DATABASE_NAME = "Slambud.db";
        private static final int DATABASE_VERSION =12;

        // for login

        private static final String TABLE_USERS = "USERS";
        private static final String COLUMN_USERID = "user_id";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_PASSWORD = "password";

        //user
        private static final String TABLE_USER = "USER";
        private static final String COLUMN_USER_ID = "user_id";
        private static final String COLUMN_USER_NAME = "user_name";

        //question
          private static final String TABLE_QUESTIONS = "QUESTIONS";
          private static final String COLUMN_QUESTION_ID = "question_id";
          private static final String COLUMN_QUESTION_TEXT1 = "question_text1";
          private static final String COLUMN_QUESTION_TEXT2 = "question_text2";
          private static final String COLUMN_QUESTION_TEXT3 = "question_text3";
          private static final String COLUMN_QUESTION_TEXT4 = "question_text4";
          private static final String COLUMN_QUESTION_TEXT5 = "question_text5";
          private static final String COLUMN_QUESTION_TEXT6 = "question_text6";
          private static final String COLUMN_QUESTION_TEXT7 = "question_text7";
          private static final String COLUMN_QUESTION_TEXT8 = "question_text8";
          private static final String COLUMN_QUESTION_TEXT9 = "question_text9";
          private static final String COLUMN_QUESTION_TEXT10 = "question_text10";
        private static final String COLUMN_QUESTION_TEXT11 = "question_text11";
        private static final String COLUMN_QUESTION_TEXT12 = "question_text12";
        private static final String COLUMN_QUESTION_TEXT13 = "question_text13";
        private static final String COLUMN_QUESTION_TEXT14 = "question_text14";
        private static final String COLUMN_QUESTION_TEXT15 = "question_text15";

        //answers
           public static final String TABLE_ANSWER = "ANSWER";
           public static final String COLUMN_ANSWER_ID = "answer_id";
           public static final String COLUMN_USER_FK = "user_id";
           public static final String COLUMN_FRIENDS_FK = "friends_id";
           public static final String COLUMN_QUESTION_FK = "question_id";
           public static final String COLUMN_ANSWER_TEXT1 = "answer_text1";
           public static final String COLUMN_ANSWER_TEXT2 = "answer_text2";
        public static final String COLUMN_ANSWER_TEXT3 = "answer_text3";
        public static final String COLUMN_ANSWER_TEXT4 = "answer_text4";
        public static final String COLUMN_ANSWER_TEXT5 = "answer_text5";
        public static final String COLUMN_ANSWER_TEXT6 = "answer_text6";
        public static final String COLUMN_ANSWER_TEXT7 = "answer_text7";
        public static final String COLUMN_ANSWER_TEXT8 = "answer_text8";
        public static final String COLUMN_ANSWER_TEXT9 = "answer_text9";
        public static final String COLUMN_ANSWER_TEXT10 = "answer_text10";
        public static final String COLUMN_ANSWER_TEXT11 = "answer_text11";
        public static final String COLUMN_ANSWER_TEXT12 = "answer_text12";
        public static final String COLUMN_ANSWER_TEXT13 = "answer_text13";
        public static final String COLUMN_ANSWER_TEXT14 = "answer_text14";
        public static final String COLUMN_ANSWER_TEXT15 = "answer_text15";




        private boolean isDefaultQuestionsInserted = false;
        public DBHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // User Table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                    COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT UNIQUE, " +
                    COLUMN_PASSWORD + " TEXT);";
            db.execSQL(createUsersTable);

            // Friends table
            String createFriendsTable = "CREATE TABLE " + TABLE_USER + "(" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_NAME + " TEXT);";
            db.execSQL(createFriendsTable);

            // Questions table
            String createQuestable = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                    COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_QUESTION_TEXT1 + " TEXT, " +
                    COLUMN_QUESTION_TEXT2 + " TEXT, " +
                    COLUMN_QUESTION_TEXT3 + " TEXT, " +
                    COLUMN_QUESTION_TEXT4 + " TEXT, " +
                    COLUMN_QUESTION_TEXT5 + " TEXT, " +
                    COLUMN_QUESTION_TEXT6 + " TEXT, " +
                    COLUMN_QUESTION_TEXT7 + " TEXT, " +
                    COLUMN_QUESTION_TEXT8 + " TEXT, " +
                    COLUMN_QUESTION_TEXT9 + " TEXT, " +
                    COLUMN_QUESTION_TEXT10 + " TEXT, " +
                    COLUMN_QUESTION_TEXT11 + " TEXT, " +
                    COLUMN_QUESTION_TEXT12 + " TEXT, " +
                    COLUMN_QUESTION_TEXT13 + " TEXT, " +
                    COLUMN_QUESTION_TEXT14 + " TEXT, " +
                    COLUMN_QUESTION_TEXT15 + " TEXT);";
            db.execSQL(createQuestable);

            // Answers table
            String createAnswerTable = "CREATE TABLE " + TABLE_ANSWER + "(" +
                    COLUMN_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_FK + " INTEGER, " +
                    COLUMN_FRIENDS_FK + " INTEGER, " +
                    COLUMN_QUESTION_FK + " INTEGER, " +
                    COLUMN_ANSWER_TEXT1 + " TEXT, " +
                    COLUMN_ANSWER_TEXT2 + " TEXT, " +
                    COLUMN_ANSWER_TEXT3 + " TEXT, " +
                    COLUMN_ANSWER_TEXT4 + " TEXT, " +
                    COLUMN_ANSWER_TEXT5 + " TEXT, " +
                    COLUMN_ANSWER_TEXT6 + " TEXT, " +
                    COLUMN_ANSWER_TEXT7 + " TEXT, " +
                    COLUMN_ANSWER_TEXT8 + " TEXT, " +
                    COLUMN_ANSWER_TEXT9 + " TEXT, " +
                    COLUMN_ANSWER_TEXT10 + " TEXT, " +
                    COLUMN_ANSWER_TEXT11 + " TEXT, " +
                    COLUMN_ANSWER_TEXT12 + " TEXT, " +
                    COLUMN_ANSWER_TEXT13 + " TEXT, " +
                    COLUMN_ANSWER_TEXT14 + " TEXT, " +
                    COLUMN_ANSWER_TEXT15 + " TEXT, " +
                    "FOREIGN KEY (" + COLUMN_USER_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USERID + "), " +
                    "FOREIGN KEY (" + COLUMN_FRIENDS_FK + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + ") ON DELETE CASCADE, " +
                    "FOREIGN KEY (" + COLUMN_QUESTION_FK + ") REFERENCES " + TABLE_QUESTIONS + "(" + COLUMN_QUESTION_ID + ") ON DELETE CASCADE);";

            db.execSQL(createAnswerTable);

        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWER);
            onCreate(db);
        }

        public long addUser(String username, String password) {
            long insertResult = -1;

            try (SQLiteDatabase db = this.getWritableDatabase()) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_USERNAME, username);
                values.put(COLUMN_PASSWORD, password);
                insertResult = db.insert(TABLE_USERS, null, values);
                Log.d("DB_INSERT", "Result: " + insertResult);

                // Check if insertion was successful and store the user ID in SharedPreferences
                if (insertResult != -1) {
                    long userId = getUserIdByUsername(username);
                    if (userId != -1) {
                        // Assuming you have a SharedPreferences object named preferences
                        SharedPreferences preferences = context.getSharedPreferences("YOUR_PREFERENCES_NAME", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        // Replace "USER_ID_KEY" with the actual key you use to store the user ID in SharedPreferences
                        editor.putLong("USER_ID_KEY", userId);
                        editor.apply();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return insertResult;
        }


        // Modify the addQuestions method to return the generated question_id
        public long addQuestions() {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_QUESTION_TEXT1, "What is your name?");
            cv.put(COLUMN_QUESTION_TEXT2, "What is your nickname?");
            cv.put(COLUMN_QUESTION_TEXT3, "When is your birthday?");
            cv.put(COLUMN_QUESTION_TEXT4, "What is your contact number?");
            cv.put(COLUMN_QUESTION_TEXT5, "What is your Favorite Color?");
            cv.put(COLUMN_QUESTION_TEXT6, "What is your favorite Food?");
            cv.put(COLUMN_QUESTION_TEXT7, "What is your Favorite Song?");
            cv.put(COLUMN_QUESTION_TEXT8, "What is your Favorite Movie?");
            cv.put(COLUMN_QUESTION_TEXT9, "Who are you after Five years?");
            cv.put(COLUMN_QUESTION_TEXT10, "How many kids do you want to have?");
            cv.put(COLUMN_QUESTION_TEXT11, "How many years you want to live? if ever");
            cv.put(COLUMN_QUESTION_TEXT12, "What kind of death would you prefer?");
            cv.put(COLUMN_QUESTION_TEXT13, "What advice would you offer if you encountered your past selves?");
            cv.put(COLUMN_QUESTION_TEXT14, "Give me your Best Qoutes");
            cv.put(COLUMN_QUESTION_TEXT15, "Can you tell me one of your secrets?");

            // Insert the questions into the QUESTIONS table
            long insertResult = db.insert(TABLE_QUESTIONS, null, cv);

            // Retrieve the question_id of the last inserted row
            long questionId = -1;
            if (insertResult != -1) {
                Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
                if (cursor != null && cursor.moveToFirst()) {
                    questionId = cursor.getLong(0);
                    cursor.close();
                }
            }

            db.close();

            return questionId; // Return the question_id of the last inserted row, or -1 if an error occurred.
        }


        public void addbtn(int userId, int friendId, int questionId,
                           String ans1, String ans2, String ans3, String ans4, String ans5,
                           String ans6, String ans7, String ans8, String ans9, String ans10,
                           String ans11,String ans12,String ans13,String ans14,String ans15) {
            try (SQLiteDatabase db = getWritableDatabase()) {
                db.beginTransaction();

                try {
                    ContentValues cv = new ContentValues();

                    cv.put(COLUMN_USER_FK, userId);
                    cv.put(COLUMN_FRIENDS_FK, friendId);
                    cv.put(COLUMN_QUESTION_FK, questionId);
                    cv.put(COLUMN_ANSWER_TEXT1, ans1);
                    cv.put(COLUMN_ANSWER_TEXT2, ans2);
                    cv.put(COLUMN_ANSWER_TEXT3, ans3);
                    cv.put(COLUMN_ANSWER_TEXT4, ans4);
                    cv.put(COLUMN_ANSWER_TEXT5, ans5);
                    cv.put(COLUMN_ANSWER_TEXT6, ans6);
                    cv.put(COLUMN_ANSWER_TEXT7, ans7);
                    cv.put(COLUMN_ANSWER_TEXT8, ans8);
                    cv.put(COLUMN_ANSWER_TEXT9, ans9);
                    cv.put(COLUMN_ANSWER_TEXT10, ans10);
                    cv.put(COLUMN_ANSWER_TEXT11, ans11);
                    cv.put(COLUMN_ANSWER_TEXT12, ans12);
                    cv.put(COLUMN_ANSWER_TEXT13, ans13);
                    cv.put(COLUMN_ANSWER_TEXT14, ans14);
                    cv.put(COLUMN_ANSWER_TEXT15, ans15);

                    // Insert the answers into the ANSWER table
                    long insertResult = db.insert(TABLE_ANSWER, null, cv);
                    Log.d("DB_INSERT_ANSWER", "Result: " + insertResult);

                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public long addFriend(String friendName) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_USER_NAME, friendName);

            long friendId = db.insert(TABLE_USER, null, cv);

            // Close the database connection
            db.close();

            return friendId;
        }
        public long getUserIdByUsername(String username) {
            SQLiteDatabase db = this.getReadableDatabase();
            long userId = -1; // Default value if user not found

            try {
                Cursor cursor = db.query(TABLE_USERS,
                        new String[]{COLUMN_USERID},
                        COLUMN_USERNAME + " = ?",
                        new String[]{username},
                        null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(COLUMN_USERID);
                    userId = (columnIndex >= 0) ? cursor.getLong(columnIndex) : -1;
                }

                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (db != null && db.isOpen()) {
                    db.close();
                }
            }

            return userId;
        }

        public boolean checkUser(String username, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] columns = {COLUMN_USERID};
            String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            cursor.close();
            db.close();

            return count > 0;
        }

        public Cursor readallData(){
            String query = "SELECT * FROM " + TABLE_ANSWER;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = null;
            if (db != null){
                cursor = db.rawQuery(query, null);
            }
            return cursor;
        }

        // Modify the method to retrieve question_id from the USERS table
        public boolean isUsernameTaken(String username) {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] columns = {COLUMN_USERID};
            String selection = COLUMN_USERNAME + " = ?";
            String[] selectionArgs = {username};
            Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            cursor.close();
            db.close();

            return count > 0;
        }

        public Cursor getAnswerById(int answerId) {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {
                    "answer_id",
                    "user_id",
                    "friends_id",
                    "question_id",
                    "answer_text1",
                    "answer_text2",
                    "answer_text3",
                    "answer_text4",
                    "answer_text5",
                    "answer_text6",
                    "answer_text7",
                    "answer_text8",
                    "answer_text9",
                    "answer_text10",
                    "answer_text11",
                    "answer_text12",
                    "answer_text13",
                    "answer_text14",
                    "answer_text15"
            };
            String selection = "answer_id=?";
            String[] selectionArgs = { String.valueOf(answerId) };
            return db.query("ANSWER", projection, selection, selectionArgs, null, null, null);
        }
        public int getColumnIndex(String columnName) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("ANSWER", null, null, null, null, null, null);
            int index = cursor.getColumnIndex(columnName);
            cursor.close();
            return index;
        }
        void updateData(int answer_id,String ans1, String ans2, String ans3, String ans4, String ans5, String ans6, String ans7, String ans8, String ans9, String ans10,String ans11,String ans12,String ans13,String ans14,String ans15) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("answer_text1", ans1);
            cv.put("answer_text2", ans2);
            cv.put("answer_text3", ans3);
            cv.put("answer_text4", ans4);
            cv.put("answer_text5", ans5);
            cv.put("answer_text6", ans6);
            cv.put("answer_text7", ans7);
            cv.put("answer_text8", ans8);
            cv.put("answer_text9", ans9);
            cv.put("answer_text10", ans10);
            cv.put("answer_text11", ans11);
            cv.put("answer_text12", ans12);
            cv.put("answer_text13", ans13);
            cv.put("answer_text14", ans14);
            cv.put("answer_text15", ans15);

            long result = db.update("ANSWER", cv, "answer_id=?", new String[]{String.valueOf(answer_id)});
            if (result == -1) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
        void deleteOneRow(String row_id) {
            SQLiteDatabase db = this.getWritableDatabase();

            // Delete row from ANSWER table
            long answerResult = db.delete(TABLE_ANSWER, "answer_id=?", new String[]{row_id});

            // Delete corresponding row from USER table
            long userResult = db.delete(TABLE_USER, "user_id=?", new String[]{row_id});

            // Log deletion results
            Log.d("DELETE_RESULT", "Answer Table Result: " + answerResult);
            Log.d("DELETE_RESULT", "User Table Result: " + userResult);

            // Show toast message based on deletion result
            if (answerResult == -1 || userResult == -1) {
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }



        void deleteAllData(){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_ANSWER);
        }


        }


