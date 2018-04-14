package nnk.com.jobhouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by HP-PC on 16-03-2018.
 */

public class MyDatabase extends SQLiteOpenHelper {
    Context context;
    public static final String table_name1 = "job_details";
    public static final String col_name = "name";
    public static final String col_password = "password";
    public static final String col_email = "email";
    public static final String col_cno = "contact";
    public static final String col_sex = "gender";
    public static final String col_grad = "graduation";
    public static final String col_year = "year";
    public static final String col_jobfunction = "jobfunction";
    public static final String col_skills = "skills";

    public static final String table_name2 = "company_details";
    public static final String col_company = "company_name";
    public static final String col_companyPass = "company_pass";

   // public static final String col_cname = "c_name";
    public static final String col_eligi = "eligibility";
    public static final String col_exp = "experience";
    public static final String col_pos = "position";
    public static final String col_sal = "salary";
    public static final String col_location = "location";
    public static final String table_name3 = "applyJob_details";

    public static final String col_img = "image";
    public static final String table_name4 = "candidates";


    public MyDatabase(Context context)
    {
        super(context, "details", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry = "create table "+table_name1+ "(" +col_name+ " text," +col_password+ " text," +col_email+" text," +col_cno+" text," +col_sex+" text," +col_grad+" text," +col_year+" text," +col_jobfunction+" text," +col_skills+" text)";
        db.execSQL(qry);
        Toast.makeText(context, " Student Table Created", Toast.LENGTH_SHORT).show();

        String qry2 = "create table "+table_name2+ "(" +col_company+ " text," +col_companyPass+ " text)";
        db.execSQL(qry2);
        Toast.makeText(context, "company table created", Toast.LENGTH_SHORT).show();

        String qry3 = "create table "+table_name3+ "(" +col_company+ " text Primary Key,"+col_jobfunction+ " text," +col_eligi+ " text," +col_exp+" text," +col_skills+" text," +col_pos+" text," +col_sal+" text," +col_location+" text)";
        db.execSQL(qry3);
        Toast.makeText(context, "apply job table created", Toast.LENGTH_SHORT).show();

        String qry4 = "create table "+table_name4+ "(" +col_name+ " text," +col_email+ " text," +col_cno+" text," +col_grad+" text," +col_year+" text," +col_skills+" text," +col_exp+" text,"+col_img+" BLOB,"+col_company+" text)";
        db.execSQL(qry4);
        Toast.makeText(context, "candidate table created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+table_name1);
        db.execSQL("DROP TABLE IF EXIST "+table_name2);
        db.execSQL("DROP TABLE IF EXIST "+table_name3);
        db.execSQL("DROP TABLE IF EXIST "+table_name4);

    }
}
