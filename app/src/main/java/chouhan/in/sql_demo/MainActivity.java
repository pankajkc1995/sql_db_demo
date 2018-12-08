package chouhan.in.sql_demo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    DatabaseHelper dbHelper= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Log.i("<---Starting Time--->",String.valueOf(new Date().getTime()));
        dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
               dbHelper.prepareDatabase();
           // SQLiteDatabase db = null;
            //long size = new File(dbHelper.getPath()).length();
            } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        // long size = new File(getFilesDir().getAbsolutePath()).length();


        Log.i("<----Loading Time---->",String.valueOf(new Date().getTime()));
        showData();
        Log.i("<----Show Time-------->",String.valueOf(new Date().getTime()));
    }

    private void showData() {
        byte[] b;
        List<Employee> list = dbHelper.getEmployees();
        StringBuffer data = new StringBuffer();
        for (int i =0; i< list.size(); i++) {
            Employee emp = list.get(i);
            data.append(emp.getId()).append(",").append(emp.getName())
                    .append(",").append(emp.getAge()).append("<br/>");
            String jj=emp.getId()+emp.getName()+emp.getAge();
             b = jj.getBytes();
            Log.i("--Size---"+i+"-->"+jj,String.valueOf(b));
        }
        TextView textView = (TextView)findViewById(R.id.bodytext);
        textView.setText(Html.fromHtml(data.toString()));
    }
}
