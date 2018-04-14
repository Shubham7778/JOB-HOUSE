package nnk.com.jobhouse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyList extends AppCompatActivity {

    int i = 0;
    String cname[] = new String[50];
    String jobfunc[] = new String[50];
    String experience[] = new String[50];
    String eligibility[] = new String[50];
    String position[] = new String[50];
    String location[] = new String[50];
    String skill[] = new String[50];
    String salary[] = new String[50];
    String username[] = new String[50];

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        lv = (ListView)findViewById(R.id.lv);
        TextView tv = new TextView(this);
        tv.setText("job offer matching your profile");
        tv.setTextSize(20);
        tv.setTextColor(Color.GRAY);
        lv.addHeaderView(tv);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String jobCat = bundle.getString("k7");
       // username[0] = bundle.getString("k1");

        MyDatabase mydb = new MyDatabase(this);
        SQLiteDatabase db = mydb.getWritableDatabase();
        String qry = "select * from applyJob_details where jobfunction= '"+jobCat+"'";
        Cursor c = db.rawQuery(qry,null);

        if(c.moveToFirst())
        {
            do
            {
                username[0] = bundle.getString("k1");
                cname[i]  = c.getString(0);
                jobfunc[i] = c.getString(1);
                eligibility[i] = c.getString(2);
                experience[i] = c.getString(3);
                skill[i]  = c.getString(4);
                position[i] = c.getString(5);
                salary[i] = c.getString(6);
                location[i] = c.getString(7);
                i++;
              // Toast.makeText(this, cname[0]+jobfunc[0]+experience[0], Toast.LENGTH_SHORT).show();
            } while(c.moveToNext());

             MyAdapter my = new MyAdapter(this, username,cname, jobfunc, eligibility, experience, skill, position, salary, location, i, R.layout.list_view_style);
             lv.setAdapter(my);
        }
        else
        {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
        }

    }
}
