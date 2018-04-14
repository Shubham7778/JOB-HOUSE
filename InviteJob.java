package nnk.com.jobhouse;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InviteJob extends AppCompatActivity {

    String name, grad, skill, email, year, exp, cno;
    TextView et_name, et_grad, et_skill, et_email, et_year, et_exp;
    public Intent chooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_job);
        et_name = (TextView)findViewById(R.id.tv1);
        et_grad = (TextView)findViewById(R.id.tv2);
        et_skill = (TextView)findViewById(R.id.tv3);
        et_exp = (TextView)findViewById(R.id.tv4);
        et_year = (TextView)findViewById(R.id.tv5);
        et_email = (TextView)findViewById(R.id.tv6);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        name = b.getString("k1");
        grad = b.getString("k4");
        skill = b.getString("k6");
        cno = b.getString("k3");
        email = b.getString("k2");
        year = b.getString("k5");
        exp = b.getString("k7");

        et_name.setText(name);
        et_grad.setText(grad);
        et_skill.setText(skill);
        et_exp.setText(exp);
        et_year.setText(year);
        et_email.setText(email);
    }
    public void invite(View v)
    {
     // Toast.makeText(this, "Invitaion Sent", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_SEND);
      //  Intent i = new Intent(Intent.ACTION_SEND, Uri.fromParts("mail to:", "dheru59225@gmail.com", null));
        i.setType("text/plain");
       // i.setType("message/rfc822");
        //i.setData(Uri.parse("mail to:"));
        //i.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");
        //i.putExtra(Intent.EXTRA_EMAIL, new String[] {"dheru59225@gmail.com"});
        //i.putExtra(Intent.EXTRA_SUBJECT,"Job Invitation");
        i.putExtra(Intent.EXTRA_TEXT,"Hello "+name+",\nYou are Invited for job" );
      //  chooser = Intent.createChooser(i, "Send Email");
        startActivity(i);
    }
    public void cancel(View view)
    {
        Intent intent = new Intent(this, LoginRecruiter.class);
        startActivity(intent);
    }
}
