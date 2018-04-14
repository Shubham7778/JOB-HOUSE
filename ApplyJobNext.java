package nnk.com.jobhouse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ApplyJobNext extends AppCompatActivity {
    ImageView iv;
    EditText et_exp, et_writeup;
    Bitmap b1=null;
    String exp, writeup;
    //boolean res;
    String name, graduation, year, skill, email, cno, cname;
    MyDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job_next);
        iv = (ImageView)findViewById(R.id.iv);
        et_exp = (EditText)findViewById(R.id.et_exp);
        et_writeup = (EditText)findViewById(R.id.et_writeup);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        name=b.getString("k1");
        graduation = b.getString("k2");
        year = b.getString("k3");
        skill = b.getString("k4");
        email = b.getString("k5");
        cno = b.getString("k6");
        cname = b.getString("k7");
       // Toast.makeText(this, name+graduation+year, Toast.LENGTH_SHORT).show();

    }
    public void openCamera(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 1212);
    }
    public void jobApply(View view)
    {
        exp = et_exp.getText().toString().trim();
        writeup = et_writeup.getText().toString().trim();
        if (exp.isEmpty())
        {
            et_exp.requestFocus();
            et_exp.setError("empty");
        }
        else if(writeup.isEmpty())
        {
            et_writeup.requestFocus();
            et_writeup.setError("empty");
        }
        else if(b1!= null)
        {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            boolean res = b1.compress(Bitmap.CompressFormat.JPEG, 100, bout);

            if (res)
            {
                byte img[] = bout.toByteArray();

                mydb = new MyDatabase(this);
                SQLiteDatabase db = mydb.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(MyDatabase.col_name, name);
                cv.put(MyDatabase.col_grad, graduation);
                cv.put(MyDatabase.col_year, year);
                cv.put(MyDatabase.col_skills, skill);
                cv.put(MyDatabase.col_email, email);
                cv.put(MyDatabase.col_cno, cno);
                cv.put(MyDatabase.col_exp, exp);
                cv.put(MyDatabase.col_img, img);
                cv.put(MyDatabase.col_company, cname);

                long result=db.insert(MyDatabase.table_name4, null, cv);
                if (result!=-1)
                {
                    Toast.makeText(this,"successfully applied",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(this," error ",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "error in image uploading", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            ImageView iv=new ImageView(this);
            iv.setImageResource(R.drawable.img);
            Toast t=new Toast(this);
            t.setView(view);
            t.setDuration(Toast.LENGTH_SHORT);
            t.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1212) {
            Bundle b = data.getExtras();
            b1 = (Bitmap) b.get("data");
            iv.setImageBitmap(b1);
        }
        if(requestCode==1234){

            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                iv.setImageBitmap(selectedImage);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this, "You have done",Toast.LENGTH_LONG).show();
        }
    }


    public void uploadPhoto(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1234);
    }


}
