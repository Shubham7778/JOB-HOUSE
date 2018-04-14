package nnk.com.jobhouse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginRecruiter extends AppCompatActivity {
    TextInputLayout til1, til2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_recruiter);

        til1 = (TextInputLayout)findViewById(R.id.til_companyName);
        til2 = (TextInputLayout)findViewById(R.id.til_companypass);
    }
    public void newCompany(View view)
    {
        Intent i =new Intent(this, companyAccount.class);
        startActivity(i);
    }
    public void loginCompany(View view)
    {
        String cname = til1.getEditText().getText().toString().trim();
        String cpass = til2.getEditText().getText().toString().trim();
        if(cname.isEmpty())
        {
            til1.setError("empty");
        }
        else
        {
            til1.setErrorEnabled(false);
            if(cpass.isEmpty())
            {
                til2.setError("empty");
            }
            else
            {
                til2.setErrorEnabled(false);
                MyDatabase mydb = new MyDatabase(this);
                SQLiteDatabase db = mydb.getWritableDatabase();

                String qry = "select * from company_details where company_name = '"+cname+"'";
                Cursor c = db.rawQuery(qry,null);
                if(c.moveToFirst())
                {
                    String company_name = c.getString(0);
                    String pass = c.getString(1);

                    if( (cname.equals(company_name)) && (cpass.equals(pass)) )
                    {
                        Toast.makeText(this, "validation successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this,CompanyWelcome.class);
                        i.putExtra("k1", cname);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(this, "invalid user", Toast.LENGTH_SHORT).show();
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
