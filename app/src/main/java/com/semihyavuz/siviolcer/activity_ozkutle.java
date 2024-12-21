package com.semihyavuz.siviolcer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_ozkutle extends AppCompatActivity {

    private EditText etMass, etVolume;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozkutle);

        etMass = findViewById(R.id.et_mass);
        etVolume = findViewById(R.id.et_volume);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDensity();
            }
        });
    }

    private void calculateDensity() {
        String massStr = etMass.getText().toString();
        String volumeStr = etVolume.getText().toString();

        if (!massStr.isEmpty() && !volumeStr.isEmpty()) {
            double mass = Double.parseDouble(massStr);
            double volume = Double.parseDouble(volumeStr);

            if (volume != 0) {
                double density = mass / volume;
                tvResult.setText("Sonuç: " + density + " kg/m³");
            } else {
                tvResult.setText("Hacim sıfır olamaz!");
            }
        } else {
            tvResult.setText("Lütfen tüm alanları doldurun.");
        }
    }
}
