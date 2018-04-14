package nnk.com.jobhouse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ApplyJob extends AppCompatActivity {
    TextView name_tv, elig_tv, skill_tv, exp_tv, pos_tv, sal_tv, loc_tv;
    String username, name, graduation, year, skill, email, cno, cname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);
        name_tv = (TextView)findViewById(R.id.aj_tv2);
        elig_tv = (TextView)findViewById(R.id.aj_tv4);
        exp_tv = (TextView)findViewById(R.id.aj_tv6);
        pos_tv = (TextView)findViewById(R.id.aj_tv8);
        sal_tv = (TextView)findViewById(R.id.aj_tv10);
        loc_tv = (TextView)findViewById(R.id.aj_tv12);
        skill_tv= (TextView)findViewById(R.id.tl_skill);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        cname = b.getString("k0");
        String elig = b.getString("k2");
        String exp = b.getString("k3");
        String pos = b.getString("k4");
        String sal = b.getString("k5");
        String loc = b.getString("k6");
        String skill = b.getString("k7");
        username = b.getString("k8");

        name_tv.setText(cname);
        elig_tv.setText(elig);
        exp_tv.setText(exp);
        pos_tv.setText(pos);
        sal_tv.setText(sal);
        loc_tv.setText(loc);
        skill_tv.setText(skill);
    }
    public void applyForJob(View view)
    {
        MyDatabase mydb = new MyDatabase(this);
        SQLiteDatabase db = mydb.getWritableDatabase();
        String qry = "select * from job_details where name = '"+username+"'";
        Cursor c = db.rawQuery(qry, null);
        if (c.moveToFirst())
        {

            name = c.getString(0);
            graduation = c.getString(5);
            year = c.getString(6);
            skill = c.getString(8);
            email = c.getString(2);
            cno = c.getString(3);
           // Toast.makeText(this, name+graduation+year+skill, Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, ApplyJobNext.class);
        intent.putExtra("k1",name);
        intent.putExtra("k2", graduation);
        intent.putExtra("k3", year);
        intent.putExtra("k4", skill);
        intent.putExtra("k5", email);
        intent.putExtra("k6",cno);
        intent.putExtra("k7", cname);
        startActivity(intent);
    }
}
