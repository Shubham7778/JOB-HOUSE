package nnk.com.jobhouse;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class JobSeekerReg extends AppCompatActivity {
   // EditText et1, et2, et3, et4;
    TextInputLayout til1, til2, til3, til4;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_reg);

        til1 = (TextInputLayout)findViewById(R.id.til_name);
        til2 = (TextInputLayout)findViewById(R.id.til_pass);
        til3 = (TextInputLayout)findViewById(R.id.til_email);
        til4 = (TextInputLayout)findViewById(R.id.til_cno);
        rg = (RadioGroup)findViewById(R.id.rg);
    }

    public void next(View view)
    {
        String name = til1.getEditText().getText().toString().trim();
        String pas = til2.getEditText().getText().toString().trim();
        String email = til3.getEditText().getText().toString().trim();
        String cno = til4.getEditText().getText().toString().trim();
        int id = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(id);
        String gender = rb.getText().toString().trim();
       Toast.makeText(this, name+pas+email+cno, Toast.LENGTH_SHORT).show();

        if(name.isEmpty())
        {
            til1.requestFocus();
            til1.setError("empty");
            til1.setBackgroundColor(Color.WHITE);
        }
        else
        {
            if(pas.isEmpty())
            {
                til2.requestFocus();
                til2.setError("empty");
                til2.setBackgroundColor(Color.WHITE);
            }
            else
            {
                if(email.isEmpty())
                {
                    til3.requestFocus();
                    til3.setError("empty");
                    til3.setBackgroundColor(Color.WHITE);
                }
                else
                {
                    if(cno.isEmpty())
                    {
                       til4.requestFocus();
                       til4.setError("empty");
                       til4.setBackgroundColor(Color.WHITE);
                    }
                    else
                    {
                        Intent i = new Intent(this, JobSeekerReg2.class);
                        i.putExtra("k1", name);
                        i.putExtra("k2", pas);
                        i.putExtra("k3", email);
                        i.putExtra("k4", cno);
                        i.putExtra("k5", gender);
                        startActivity(i);
                    }
                }
            }
        }

    }
}
