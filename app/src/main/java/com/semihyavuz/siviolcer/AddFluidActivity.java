package com.semihyavuz.siviolcer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddFluidActivity extends AppCompatActivity {

    private EditText fluidNameInput, densityInput, weightInput;
    private Button addButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fluid);

        fluidNameInput = findViewById(R.id.fluidNameInput);
        densityInput = findViewById(R.id.densityInput);
        weightInput = findViewById(R.id.weightInput);
        addButton = findViewById(R.id.addButton);
        dbHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(v -> {
            String fluidName = fluidNameInput.getText().toString();
            String densityStr = densityInput.getText().toString();
            String weightStr = weightInput.getText().toString();

            if (!fluidName.isEmpty() && !densityStr.isEmpty() && !weightStr.isEmpty()) {
                double density = Double.parseDouble(densityStr);
                double weight = Double.parseDouble(weightStr);
                addFluidToDatabase(fluidName, density, weight);
                Toast.makeText(this, "Sıvı eklendi", Toast.LENGTH_SHORT).show();
                fluidNameInput.setText("");
                densityInput.setText("");
                weightInput.setText("");
            } else {
                Toast.makeText(this, "Tüm alanları doldurun", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addFluidToDatabase(String name, double density, double weightPerLiter) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("density", density);
        values.put("weight_per_liter", weightPerLiter);
        db.insert("fluids", null, values);
    }
}
