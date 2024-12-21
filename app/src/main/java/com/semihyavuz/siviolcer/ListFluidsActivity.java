package com.semihyavuz.siviolcer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListFluidsActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fluids);

        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        loadFluids();
    }

    private void loadFluids() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, density, weight_per_liter FROM fluids", null);
        ArrayList<String> fluidList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                double density = cursor.getDouble(1);
                double weightPerLiter = cursor.getDouble(2);
                fluidList.add(name + " - Özkütle: " + density + " kg/m3, Litre Başına Ağırlık: " + weightPerLiter + " kg");
            } while (cursor.moveToNext());
        }

        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fluidList);
        listView.setAdapter(adapter);
    }
}
