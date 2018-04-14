package nnk.com.jobhouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP-PC on 20-03-2018.
 */

public class MyAdapter extends BaseAdapter {
    Context c;
    String cname[];
    String jobFunction[];
    String eligibility[];
    String experience[];
    String skill[];
    String pos[];
    String location[];
    String salary[];
    String username[];
    int i,layout;

    public MyAdapter(Context c,String[] username, String[] cname, String[] jobFunction, String[] eligibility, String[] experience,String[] skill, String[] pos, String[] salary, String[] location, int i, int list_view_style)
    {
        this.c = c;
        this.username = username;
        this.jobFunction = jobFunction;
        this.cname = cname;
        this.eligibility = eligibility;
        this.experience = experience;
        this.skill = skill;
        this.pos = pos;
        this.salary = salary;
        this.location = location;
        this.i = i;
        layout = list_view_style;
    }
    @Override
    public int getCount() {
        return i;
    }

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v1 = li.inflate(layout, parent, false);

        TextView tv1 = (TextView)v1.findViewById(R.id.tv1);
        tv1.setText(cname[position]);
        Toast.makeText(c, "welcome "+username[0], Toast.LENGTH_SHORT).show();

        TextView tv2 = (TextView)v1.findViewById(R.id.tv2);
        tv2.setText("Eligibility: "+eligibility[position]);

        TextView tv3 = (TextView)v1.findViewById(R.id.tv3);
        tv3.setText("Job location: "+location[position]);
       // Toast.makeText(c, location[0], Toast.LENGTH_SHORT).show();


        Button b = (Button)v1.findViewById(R.id.view);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(c,ApplyJob.class);
                i.putExtra("k0", cname[position]);
                i.putExtra("k1", jobFunction[position]);
                i.putExtra("k2", eligibility[position]);
                i.putExtra("k3", experience[position]);
                i.putExtra("k4", pos[position]);
                i.putExtra("k5", salary[position]);
                i.putExtra("k6", location[position]);
                i.putExtra("k7", skill[position]);
                i.putExtra("k8", username[0]);
                c.startActivity(i);
               // Toast.makeText(c, location[position], Toast.LENGTH_SHORT).show();
            }
        });

        return v1;
    }
}
