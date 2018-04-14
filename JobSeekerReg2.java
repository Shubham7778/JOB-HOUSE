package nnk.com.jobhouse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class JobSeekerReg2 extends AppCompatActivity {
    MyDatabase mydb;
    TextInputLayout til1, til2;
    Spinner sp1, sp2;
    String jobSpecification[] = {"Job function", "Accounting", "Engineering", "Administrative", "Arts and Design", "Consult"};
    String year[] = {"year of passing","2019", "2018", "2017","2016", "2015", "2014", "2013", "2012", "2011", "2010"};
    String name, password, email, contact, gender, grad, yr, jobfunc, skills;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_reg2);
        sp1 = (Spinner)findViewById(R.id.sp1);
        sp2 = (Spinner)findViewById(R.id.sp2);
        til1 = (TextInputLayout)findViewById(R.id.til_graduation);
        til2 = (TextInputLayout)findViewById(R.id.til_skills);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        name = b.getString("k1");
        password = b.getString("k2");
        email = b.getString("k3");
        contact = b.getString("k4");
        gender  =b.getString("k5");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, year);
        sp1.setAdapter(aa);
        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, jobSpecification);
        sp2.setAdapter(aa1);
      //  Toast.makeText(this, name+password+email+contact+gender, Toast.LENGTH_SHORT).show();
    }
    public void submitDetails(View view)
    {
        grad = til1.getEditText().getText().toString().trim();
        yr = sp1.getSelectedItem().toString().trim();
        jobfunc = sp2.getSelectedItem().toString().trim();
        skills = til2.getEditText().getText().toString().trim();
       // Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show();

        if(grad.isEmpty())
        {
            til1.requestFocus();
            til1.setError("empty");
        }
        else
        {
            if(skills.isEmpty())
            {
                til2.requestFocus();
                til2.setError("empty");
            }
            else
            {
                //Toast.makeText(this, grad+year+jobfunction+skills, Toast.LENGTH_SHORT).show();
                ContentValues cv = new ContentValues();
                cv.put(MyDatabase.col_name, name);
                cv.put(MyDatabase.col_password, password);
                cv.put(MyDatabase.col_email, email);
                cv.put(MyDatabase.col_sex, gender);
                cv.put(MyDatabase.col_grad, grad);
                cv.put(MyDatabase.col_year, yr);
                cv.put(MyDatabase.col_jobfunction, jobfunc);
                cv.put(MyDatabase.col_skills, skills);

                mydb = new MyDatabase(this);
                SQLiteDatabase db = mydb.getWritableDatabase();
                long res = db.insert(MyDatabase.table_name1, null, cv);
                if(res!=-1)
                {
                    Toast.makeText(this, "registration done", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(this,LoginJobSeeker.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
