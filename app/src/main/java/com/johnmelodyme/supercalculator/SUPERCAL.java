package com.johnmelodyme.supercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @DEVELOPER: JOHN MELODY ME
 * @DATETIME: 12/12/2019;
 * @COPYRIGHT: 2019 - 2023;
 * @PROJECTNAME: SUPER CALCULATOR;
 * @ACTIVITY: SUPERCAL;
 */
public class SUPERCAL extends AppCompatActivity {
    TextView NETT_INCOME;

    private void INIT_DECLARATION() {
        NETT_INCOME = findViewById(R.id.Salary_nett);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT_DECLARATION();
    }

}
