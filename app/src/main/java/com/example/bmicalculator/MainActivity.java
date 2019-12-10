package com.example.bmicalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView score;
    private Button gym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        score = (TextView) findViewById(R.id.score);
        gym = (Button) findViewById(R.id.gyms);
        gym.setVisibility(View.INVISIBLE);

    }

    public void calculateBMI(View a) {
        String yourHeight = height.getText().toString();
        String yourWeight = weight.getText().toString();
        if (yourHeight != null && !"".equals(yourHeight)
                && yourWeight != null && !"".equals(yourWeight)) {
            float valueOfHeight = Float.parseFloat(yourHeight);
            float valueOfWeight = Float.parseFloat(yourWeight);
            float bMIcalculator = valueOfWeight / (valueOfHeight * valueOfHeight) * 703;
            displayBMI(bMIcalculator);
        }
    }
    private void displayBMI(float bMIcalculator) {
        String label = "";
        gym = (Button) findViewById(R.id.gyms);
        if (Float.compare(bMIcalculator, 15f) <= 0) {
            label = getString(R.string.extremely_underweight);
            gym.setVisibility(View.VISIBLE);
        } else if (Float.compare(bMIcalculator, 15f) > 0 && Float.compare(bMIcalculator, 16f) <= 0) {
            label = getString(R.string.moderately_underweight);
            gym.setVisibility(View.VISIBLE);
        } else if (Float.compare(bMIcalculator, 16f) > 0 && Float.compare(bMIcalculator, 18.5f) <= 0) {
            label = getString(R.string.underweight);
            gym.setVisibility(View.VISIBLE);
        } else if (Float.compare(bMIcalculator, 18.5f) > 0 && Float.compare(bMIcalculator, 25f) <= 0) {
            label = getString(R.string.normal);
            gym.setVisibility(View.INVISIBLE);
        } else if (Float.compare(bMIcalculator, 25f) > 0 && Float.compare(bMIcalculator, 30f) <= 0) {
            label = getString(R.string.overweight);
            gym.setVisibility(View.VISIBLE);
        } else if (Float.compare(bMIcalculator, 30f) > 0 && Float.compare(bMIcalculator, 35f) <= 0) {
            label = getString(R.string.slightly_obese);
            gym.setVisibility(View.VISIBLE);
        } else if (Float.compare(bMIcalculator, 35f) > 0 && Float.compare(bMIcalculator, 40f) <= 0) {
            label = getString(R.string.very_obese);
            gym.setVisibility(View.VISIBLE);
        } else {
            label = getString(R.string.extremely_obese);
            gym.setVisibility(View.VISIBLE);
        }
        label = bMIcalculator + "\n\n" + label;
        score.setText(label);
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMapActivity();
            }
        });
    }

    public void startMapActivity() {
        Intent intent = new Intent(this,  MapsActivity.class);
        startActivity(intent);
    }
}