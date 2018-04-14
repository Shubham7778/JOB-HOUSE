package nnk.com.jobhouse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class JobPost extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5, et6, et7;
    Spinner sp;
    String jobFunction[] = {"Job function", "Accounting", "Engineering", "Administrative", "Arts and Design", "Consult"};
    String jobfunc, eligibility, experience, skills, position, salary, location, c_name;
    MyDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        et7 = (EditText)findViewById(R.id.cname);
        et1 = (EditText)findViewById(R.id.eligibility);
        et2 = (EditText)findViewById(R.id.experience);
        et3 = (EditText)findViewById(R.id.skills);
        et4 = (EditText)findViewById(R.id.position);
        et5 = (EditText)findViewById(R.id.salary);
        et6 =(EditText)findViewById(R.id.location);
        sp = (Spinner)findViewById(R.id.sp1);

        ArrayAdapter aa = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, jobFunction);
        sp.setAdapter(aa);
    }
    public void applyJob(View view)
    {
        c_name = et7.getText().toString().trim();
        jobfunc = sp.getSelectedItem().toString().trim();
        eligibility = et1.getText().toString().trim();
        experience = et2.getText().toString().trim();
        skills = et3.getText().toString().trim();
        position = et4.getText().toString().trim();
        salary = et5.getText().toString().trim();
        location = et6.getText().toString().trim();

        if(eligibility.isEmpty())
        {
            et1.requestFocus();
            et1.setError("Empty");
        }
        else
        {
            if(experience.isEmpty())
            {
                et2.requestFocus();
                et2.setError("Empty");
            }
            else
            {
                if(skills.isEmpty())
                {
                    et3.requestFocus();
                    et3.setError("Empty");
                }
                else
                {
                    if(position.isEmpty())
                    {
                        et4.requestFocus();
                        et4.setError("Empty");
                    }
                    else
                    {
                        if(salary.isEmpty())
                        {
                            et5.requestFocus();
                            et5.setError("empty");
                        }
                        else
                        {
                            if (location.isEmpty())
                            {
                                et6.requestFocus();
                                et6.setText("empty");
                            }
                            else
                            {
                                ContentValues cv = new ContentValues();
                                cv.put(MyDatabase.col_company, c_name);
                                cv.put(MyDatabase.col_jobfunction, jobfunc);
                                cv.put(MyDatabase.col_eligi, eligibility);
                                cv.put(MyDatabase.col_exp, experience);
                                cv.put(MyDatabase.col_skills, skills);
                                cv.put(MyDatabase.col_pos, position);
                                cv.put(MyDatabase.col_sal, salary);
                                cv.put(MyDatabase.col_location, location);

                                mydb = new MyDatabase(this);
                                SQLiteDatabase db = mydb.getWritableDatabase();
                                long res = db.insert(MyDatabase.table_name3, null, cv);
                                if(res!=-1)
                                {
                                    Toast.makeText(this, "successfully posted", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent i = new Intent(this,LoginJobSeeker.class);
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
