package nnk.com.jobhouse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class companyAccount extends AppCompatActivity {
    TextInputLayout til1, til2;
    MyDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_account);
        til1 = (TextInputLayout)findViewById(R.id.til_companyName);
        til2 = (TextInputLayout)findViewById(R.id.til_password);
    }
    public void registerCompany(View view)
    {
        String companyname = til1.getEditText().getText().toString().trim();
        String companypass = til2.getEditText().getText().toString().trim();

        if(companyname.isEmpty())
        {
            til1.setError("Empty");
            Toast.makeText(this, companyname+companypass,Toast.LENGTH_SHORT).show();
        }
        else
        {
            til1.setErrorEnabled(false);
            if(companypass.isEmpty())
            {
                til2.setError("Empty");
            }
            else
            {
                til2.setErrorEnabled(false);

                ContentValues cv = new ContentValues();
                cv.put(MyDatabase.col_company, companyname);
                cv.put(MyDatabase.col_companyPass, companypass);
                mydb = new MyDatabase(this);
                SQLiteDatabase db = mydb.getWritableDatabase();

                //String qry = "insert into company_details values('" +companyname+ "','" + companypass + "')";
                //db.execSQL(qry);
                long res = db.insert(MyDatabase.table_name2, null, cv);
                if(res!=-1)
                {
                    Toast.makeText(this, "company details inserted", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(this, LoginRecruiter.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }

               /* Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
                til1.getEditText().setText(" ");
                til2.getEditText().setText("");
                til1.getEditText().requestFocus();*/
            }
        }
    }
}
