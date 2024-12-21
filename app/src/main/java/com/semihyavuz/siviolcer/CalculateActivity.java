package com.semihyavuz.siviolcer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {

    private EditText densityInput, volumeInput;
    private Button calculateButton;
    private TextView resultTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        densityInput = findViewById(R.id.densityInput);
        volumeInput = findViewById(R.id.volumeInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        dbHelper = new DatabaseHelper(this);

        calculateButton.setOnClickListener(v -> {
            String densityStr = densityInput.getText().toString();
            String volumeStr = volumeInput.getText().toString();

            if (!densityStr.isEmpty() && !volumeStr.isEmpty()) {
                double density = Double.parseDouble(densityStr);
                double volume = Double.parseDouble(volumeStr);
                double weight = density * volume;
                String fluidName = dbHelper.findFluidByDensityAndWeight(density, weight);
                resultTextView.setText("Sıvı: " + fluidName + "\nAğırlık: " + weight + " kg");
            } else {
                resultTextView.setText("Tüm alanları doldurun");
            }
        });
    }
}
