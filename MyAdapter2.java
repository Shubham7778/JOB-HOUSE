package nnk.com.jobhouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by HP-PC on 25-03-2018.
 */

public class MyAdapter2 extends BaseAdapter {
    Context c;
    String name[];
    String email[];
    String cno[];
    String graduation[];
    String year[];
    String skill[];
    String exp[];
    int i,layout;
    public MyAdapter2(Context c, String[] name, String[] email, String[] cno, String[] graduation, String[] year, String[] skill, String[] exp, int i, int viewstyle)
    {
        this.c = c;
        this.name = name;
        this.email = email;
        this.cno = cno;
        this.graduation = graduation;
        this.year = year;
        this.skill = skill;
        this.exp = exp;
        this.i = i;
        layout = viewstyle;
    }
    @Override
    public int getCount() {
        return i;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v1 = li.inflate(layout, parent, false);

        TextView tv1 = (TextView)v1.findViewById(R.id.tv1);
        tv1.setText("Name:  "+name[position]);

        TextView tv2 = (TextView)v1.findViewById(R.id.tv2);
        tv2.setText("Graduation:  "+graduation[position]);

        TextView tv3 = (TextView)v1.findViewById(R.id.tv3);
        tv3.setText("skill:  "+skill[position]);

        Button b = (Button)v1.findViewById(R.id.view);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(c, InviteJob.class);
                intent.putExtra("k1", name[position]);
                intent.putExtra("k2", email[position]);
                intent.putExtra("k3", cno[position]);
                intent.putExtra("k4", graduation[position]);
                intent.putExtra("k5", year[position]);
                intent.putExtra("k6", skill[position]);
                intent.putExtra("k7", exp[position]);
                c.startActivity(intent);

            }
        });

        return v1;
    }
}
