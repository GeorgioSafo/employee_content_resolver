package ru.example.contentresolver;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * @author Gevork Safaryan on 26/11/2019
 */
public class MainActivity extends Activity {

    static final String PROVIDER_NAME = "com.example.MyApplication.EmployeeProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/employee";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String GRADE = "grade";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(Employee.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(Employee.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        Uri students = Uri.parse(URL+"/6");

        Cursor c = getContentResolver().query(students, null, null, null, "name");
        try {
            if (c.moveToFirst()) {
                do {
                    Toast.makeText(this,
                            c.getString(c.getColumnIndex(_ID)) +
                                    ", " + c.getString(c.getColumnIndex(NAME)) +
                                    ", " + c.getString(c.getColumnIndex(GRADE)),
                            Toast.LENGTH_SHORT).show();


                    Log.d("Cursor", c.getString(c.getColumnIndex(_ID)) +
                            ", " + c.getString(c.getColumnIndex(NAME)) +
                            ", " + c.getString(c.getColumnIndex(GRADE)));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
    }
}
