package com.johnmelodyme.supercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @DEVELOPER: JOHN MELODY ME
 * @DATETIME: 12/12/2019;
 * @COPYRIGHT: 2019 - 2023;
 * @PROJECTNAME: SUPER CALCULATOR;
 * @ACTIVITY: SUPERCAL;
 */
public class SUPERCAL extends AppCompatActivity {
    EditText NETT_INCOME;
    TextView EPF_EMPLOYER, EPF_EMPLOYEE, SOCSO_EPLOYER, SOCSO_EMPLOYEE, EIS_EMPLOYER, EIS_EMPLOYEE ,RESULT;
    Button CALCULATE;

    private void INIT_DECLARATION() {
        NETT_INCOME = findViewById(R.id.Salary_nett);
        EPF_EMPLOYER = findViewById(R.id.epf_employer);
        EPF_EMPLOYEE = findViewById(R.id.epf_employee);
        SOCSO_EPLOYER = findViewById(R.id.socso_employer);
        SOCSO_EMPLOYEE = findViewById(R.id.socso_employee);
        EIS_EMPLOYER = findViewById(R.id.EIS_employer);
        EIS_EMPLOYEE = findViewById(R.id.eis_employee);
        RESULT = findViewById(R.id.results);
        CALCULATE = findViewById(R.id.Calculate);
    }

    @Override
    public void onStart(){
        super.onStart();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        String EMPTY;
        EMPTY = " ";
        EPF_EMPLOYEE.setText(EMPTY);
        EPF_EMPLOYER.setText(EMPTY);
        SOCSO_EMPLOYEE.setText(EMPTY);
        SOCSO_EPLOYER.setText(EMPTY);
        EIS_EMPLOYEE.setText(EMPTY);
        EIS_EMPLOYER.setText(EMPTY);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT_DECLARATION();
        CALCULATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String INCOME;
                INCOME = NETT_INCOME.getText().toString();

                if (TextUtils.isEmpty(INCOME)){
                    System.out.println("Please Enter Your Salary (Before Deduction)");
                    NETT_INCOME.setError( "Please Enter Your Salary (Before Deduction)");
                    NETT_INCOME.requestFocus();
                } else {
                    float salary = Float.parseFloat(INCOME); // AMOUNT ENTERED

                    ///// EPF////////
                    int ee; // EMPLOYER EPF
                    ee = (int) (salary * 13/100);
                    String e = String.valueOf(ee);

                    int EE; // EMPLOYEE EPF
                    EE = (int) (salary * 11/100);
                    String E = String.valueOf(EE);

                    ////SOCSO///////
                    int se; // SOCSO EMPLOYER
                    se = (int) (salary * 1.75/100);
                    String s = String.valueOf(se);

                    int SE; // SOCSO EMPLOYEE
                    SE = (int) (salary * 0.5/100);
                    String S = String.valueOf(SE);

                    //// EIS ////
                    int eis; // EIS EMPLOYER && EMPLOYEE
                    eis = (int) (salary * 0.2/100);
                    String EIS = String.valueOf(eis);

                    ///// SUM UP /////
                    int total;
                    total = (int) (salary - eis - EE - SE);
                    String TOTAL = String.valueOf(total);

                    System.out.println("SUCCESS");
                    EPF_EMPLOYER.setText(e);
                    EPF_EMPLOYEE.setText(E);
                    SOCSO_EPLOYER.setText(s);
                    SOCSO_EMPLOYEE.setText(S);
                    EIS_EMPLOYER.setText(EIS);
                    EIS_EMPLOYEE.setText(EIS);
                    RESULT.setText(TOTAL);
                }
            }
        });
    }
}
