package com.example.sccm.sqlitedbfromjsonexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView mobile_codes_recyclerView;
    MobileCodesDatabase mMobileCodesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mobile_codes_recyclerView = findViewById(R.id.mobile_codes_recyclerView);

        mMobileCodesDatabase = new MobileCodesDatabase(this);

        getCountryCodesData();


    }

    private ArrayList<CountryCodeModel> getCountryCodesData() {

        ArrayList<CountryCodeModel> arrayList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("mobilecodes");

            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                HashMap<String, String> hashMap = new HashMap<>();

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("name"));
                String country_name = jo_inside.getString("name");
                String dial_code = jo_inside.getString("dial_code");
                String country_code = jo_inside.getString("code");

                ContentValues contentValues = new ContentValues();
                SQLiteDatabase database = MobileCodesDatabase.getInstance(this).getWritableDatabase();

                hashMap.put("name", country_name);
                hashMap.put("dial_code", dial_code);
                hashMap.put("code", country_code);

                contentValues.put(MobileCodesDatabase.COUNTRY_NAME,country_name);
                contentValues.put(MobileCodesDatabase.COUNTRY_DIAL_CODE,dial_code);
                contentValues.put(MobileCodesDatabase.COUNTRY_CODE,country_code);

                database.insert(MobileCodesDatabase.TABLE_MOBILE_DATA,null,contentValues);

                arrayList.add(new CountryCodeModel(country_name, dial_code));

                Log.e("data", country_name + "\n" + dial_code);


                showDataInRecyclerView();


                //formList.add(m_li);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    private void showDataInRecyclerView() {


        ArrayList arrayList = mMobileCodesDatabase.getAllMobileData();
        AllMobileCodesDataAdapter directorySubListAdapter = new AllMobileCodesDataAdapter(arrayList,this);
        mobile_codes_recyclerView.setHasFixedSize(true);
        mobile_codes_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mobile_codes_recyclerView.setAdapter(directorySubListAdapter);

    }





    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("mobilecodes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
