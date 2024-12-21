package com.semihyavuz.siviolcer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton, addFluidButton, listFluidsButton, settingsButton, aboutButton, densityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = findViewById(R.id.calculateButton);
        addFluidButton = findViewById(R.id.ekleFluidButton);
        listFluidsButton = findViewById(R.id.listFluidsButton);
        settingsButton = findViewById(R.id.settingsButton);
        aboutButton = findViewById(R.id.aboutButton);
        densityButton = findViewById(R.id.densityButton); // Yeni butonu burada tanımlıyoruz.

        calculateButton.setOnClickListener(v -> startActivity(new Intent(this, CalculateActivity.class)));
        addFluidButton.setOnClickListener(v -> startActivity(new Intent(this, AddFluidActivity.class)));
        listFluidsButton.setOnClickListener(v -> startActivity(new Intent(this, ListFluidsActivity.class)));
        settingsButton.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        aboutButton.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
        densityButton.setOnClickListener(v -> startActivity(new Intent(this, activity_ozkutle.class))); // Yeni butonun tıklama olayını tanımlıyoruz.
    }
}
