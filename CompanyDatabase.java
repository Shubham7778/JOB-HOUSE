package nnk.com.jobhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by HP-PC on 16-03-2018.
 */

public class CompanyDatabase extends SQLiteOpenHelper {
    Context context;
    public CompanyDatabase(Context context)
    {
        super(context, "details", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table company_details(companyname text,password text)";
        db.execSQL(qry);
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
