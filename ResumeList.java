package nnk.com.jobhouse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ResumeList extends AppCompatActivity {

    String company;
    ListView lv;
   // ArrayList al;
    Bitmap bit;
   // String keys[]={"k1","k2","k3","k4","k5","k6","k7","k8","k9"};
    String name[] = new String[50];
    String email[] = new  String[50];
    String cno[] = new String[50];
    String graduation[] = new String[50];
    String year[] = new String[50];
    String skill[] = new String[50];
    String exp[] = new String[50];
    int i = 0;
    Bitmap bitmap[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_list);
        lv = (ListView)findViewById(R.id.lv);
        Intent intent = getIntent();
        Bundle b =intent.getExtras();
        company = b.getString("k1");

        Toast.makeText(this, company, Toast.LENGTH_SHORT).show();
        MyDatabase mydb = new MyDatabase(this);
        SQLiteDatabase db = mydb.getWritableDatabase();
        String qry = "select * from candidates where company_name = '"+company+"'";

        Cursor c = db.rawQuery(qry,null);
        if(c.moveToFirst())
        {
            do
            {
                name[i]=c.getString(0);
                email[i]=c.getString(1);
                cno[i] = c.getString(2);
                graduation[i] = c.getString(3);
                year[i] = c.getString(4);
                skill[i] = c.getString(5);
                exp[i]=c.getString(6);
               // byte[] image = c.getBlob(8);
               // bitmap[i] =  BitmapFactory.decodeByteArray(image, 0 , image.length);
               // company = c.getString(7);
                //image[]=c.getBlob(8);
                i++;
                //Toast.makeText(this, name[0]+email[0]+exp[0],Toast.LENGTH_SHORT).show();
            }while(c.moveToNext());

            MyAdapter2 my = new MyAdapter2(this, name, email, cno, graduation, year,skill, exp, i, R.layout.viewstyle);
            lv.setAdapter(my);

        }
       /* else
        {
            Toast.makeText(this,"no image available",Toast.LENGTH_SHORT).show();
            finish();
        }*/

    }
}
