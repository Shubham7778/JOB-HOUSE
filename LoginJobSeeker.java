package nnk.com.jobhouse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginJobSeeker extends AppCompatActivity {
    TextInputLayout til1, til2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_job_seeker);

        til1 = (TextInputLayout)findViewById(R.id.til1);
        til2 = (TextInputLayout)findViewById(R.id.til2);
    }
    public void registration(View view)
    {
        Intent i = new Intent(this, JobSeekerReg.class);
        startActivity(i);
    }
    public void loginUser(View view)
    {
        String name = til1.getEditText().getText().toString().trim();
        String password = til2.getEditText().getText().toString().trim();
        if(name.isEmpty())
        {
            til1.setError("empty");
        }
        else
        {
            til1.setEnabled(false);
            if(password.isEmpty())
            {
                til2.setError("empty");
            }
            else
            {
                til2.setEnabled(false);
                MyDatabase mydb = new MyDatabase(this);
                SQLiteDatabase db = mydb.getWritableDatabase();

                String qry = "select * from job_details where name = '"+name+"'";
                Cursor c = db.rawQuery(qry,null);

                if(c.moveToFirst())
                {
                    String username = c.getString(0);
                    String pass = c.getString(1);
                    String jobFunc = c.getString(7);
                   // String cno = c.getString(3);
                    //String grad = c.getString(5);

                    if( (username.equals(name)) && (password.equals(pass)) )
                    {
                        //open company lists
                       // Toast.makeText(this, name+pass+email+cno+grad, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this,CompanyList.class);
                        i.putExtra("k7",jobFunc);
                        i.putExtra("k1", username);
                        Toast.makeText(this, jobFunc, Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        til1.getEditText().setText("");
                        til2.getEditText().setText("");
                        finish();

                    }
                    else
                    {
                        Toast.makeText(this, "invalid user", Toast.LENGTH_SHORT).show();
                        til1.getEditText().setText("");
                        til2.getEditText().setText("");
                    }
                }
                else
                {
                    Toast.makeText(this, "Not Reistered", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }
    }
}
