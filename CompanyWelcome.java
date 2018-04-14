package nnk.com.jobhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CompanyWelcome extends AppCompatActivity {
     TextView tv;
    String cname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_welcome);

        tv = (TextView)findViewById(R.id.tv);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        cname = b.getString("k1");
        tv.setText("Company: "+cname);
    }
    public void jobPost(View view)
    {
        Intent i = new Intent(this,JobPost.class);
        i.putExtra("k1",cname);
        startActivity(i);
    }
    public void viewResume(View view)
    {
        Intent intent = new Intent(this, ResumeList.class);
        intent.putExtra("k1", cname);
        startActivity(intent);
    }
}
