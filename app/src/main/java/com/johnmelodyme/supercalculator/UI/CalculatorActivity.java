package com.johnmelodyme.supercalculator.UI;
/**
 * Copyright © 2021 by John Melody Me
 * <p>
 * All rights reserved. No part of this software may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods,
 * without the prior written permission of the developer, except in the
 * case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. For permission requests,
 * write to the code-owner, addressed “Attention: Permissions Coordinator,”
 * at the address below.
 * <p>
 * https://johnmelodyme.github.io/
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.johnmelodyme.supercalculator.Const.Const;
import com.johnmelodyme.supercalculator.R;

public class CalculatorActivity extends AppCompatActivity
{
    private static final String TAG = "MYWAGES =>";
    public boolean isRadioButtonChecked;
    public double KWSP_CONTRIBUTION;
    public String userSalaryInput;
    public Intent onSurvey, onEmploymentAct;
    public EditText inputSalary;
    public TextView epfEmployee, epfEmployer, perkesoEmployee, perkesoEmployer;
    public TextView eisEmployee, eisEmployer, total;
    public RadioButton epfSeven, epfNine, epfEleven;
    public Button calculate, clickedButton;

    @SuppressLint("ResourceAsColor")
    public void initiateUserInterface()
    {
        inputSalary = (EditText) findViewById(R.id.inputsalary);
        inputSalary.setHintTextColor(R.color.grey);

        epfSeven = (RadioButton) findViewById(R.id.kwspseven);
        epfNine = (RadioButton) findViewById(R.id.kwspnine);
        epfEleven = (RadioButton) findViewById(R.id.kwspeleven);

        epfEmployee = (TextView) findViewById(R.id.epfemployee);
        epfEmployer = (TextView) findViewById(R.id.epfemployer);
        perkesoEmployee = (TextView) findViewById(R.id.perkesoemployee);
        perkesoEmployer = (TextView) findViewById(R.id.perkesoemployer);
        eisEmployee = (TextView) findViewById(R.id.eisemployee);
        eisEmployer = (TextView) findViewById(R.id.eisemployer);

        total = (TextView) findViewById(R.id.total);

        epfEmployee.setText(Const.ON_START_VALUE);
        epfEmployer.setText(Const.ON_START_VALUE);
        perkesoEmployee.setText(Const.ON_START_VALUE);
        perkesoEmployer.setText(Const.ON_START_VALUE);
        eisEmployee.setText(Const.ON_START_VALUE);
        eisEmployer.setText(Const.ON_START_VALUE);

        total.setText(Const.ON_START_VALUE);

        calculate = (Button) findViewById(R.id.calculate);

        Log.i(TAG, "initiateUserInterface: User Interface Component Rendered");
    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view)
    {
        isRadioButtonChecked = ((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.kwspseven:
            {
                if (isRadioButtonChecked)
                {
                    KWSP_CONTRIBUTION = Const.FORMER_CURRENT_KWSP_EMPLOYEE_RATE;
                    Log.d(TAG, "onRadioButtonClicked: " + KWSP_CONTRIBUTION + " EPF Contribution Selected ");
                }

                break;
            }

            case R.id.kwspnine:
            {
                if (isRadioButtonChecked)
                {
                    KWSP_CONTRIBUTION = Const.CURRENT_KWSP_EMPLOYEE_RATE;
                    Log.d(TAG, "onRadioButtonClicked: " + KWSP_CONTRIBUTION + " EPF Contribution Selected ");
                }

                break;
            }

            case R.id.kwspeleven:
            {
                if (isRadioButtonChecked)
                {
                    KWSP_CONTRIBUTION = Const.FORMER_KWSP_EMPLOYEE_RATE;
                    Log.d(TAG, "onRadioButtonClicked: " + KWSP_CONTRIBUTION + " EPF Contribution Selected ");
                }

                break;
            }

            default:
            {
                Log.e(TAG, "onRadioButtonClicked: What User is Clicking?");
                break;
            }
        }
    }

    public void onCalculateButton(View view)
    {
        clickedButton = (Button) view;
        userSalaryInput = inputSalary.getText().toString();

        int EMPLOYEE_KWSP, EMPLOYER_KWSP;
        int EMPLOYEE_PERKESO, EMPLOYER_PERKESO;
        int EMPLOYEE_EIS, EMPLOYER_EIS;
        int TOTAL;

        String KWSP_EMPLOYEE_VALUE, KWSP_EMPLOYER_VALUE;
        String PERKESO_EMPLOYEE_VALUE, PERKESO_EMPLOYER_VALUE;
        String EIS_EMPLOYEE_VALUE, EIS_EMPLOYER_VALUE;
        String TOTAL_VALUE;


        if (clickedButton.getId() == R.id.calculate)
        {
            Log.d(TAG, "onCalculateButton:  Calculation");

            if (!(TextUtils.isEmpty(userSalaryInput)))
            {
                double INCOME = Double.parseDouble(userSalaryInput);

                //* KWSP Value:
                if (epfSeven.isChecked())
                {
                    EMPLOYEE_KWSP = (int) (INCOME * Const.FORMER_CURRENT_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (int) (INCOME * Const.FORMER_CURRENT_KWSP_EMPLOYER_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                    epfEmployee.setText(KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText(KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                    perkesoEmployee.setText(PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText(PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                    eisEmployee.setText(EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText(EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.valueOf(TOTAL);
                    total.setText(TOTAL_VALUE);
                }
                else if (epfNine.isChecked())
                {
                    EMPLOYEE_KWSP = (int) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (int) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                    epfEmployee.setText(KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText(KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                    perkesoEmployee.setText(PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText(PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                    eisEmployee.setText(EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText(EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.valueOf(TOTAL);
                    total.setText(TOTAL_VALUE);
                }
                else if (epfEleven.isChecked())
                {
                    EMPLOYEE_KWSP = (int) (INCOME * Const.FORMER_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (int) (INCOME * Const.FORMER_KWSP_EMPLOYEE_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                    epfEmployee.setText(KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText(KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "KWSP Value =>" + KWSP_EMPLOYEE_VALUE + KWSP_EMPLOYER_VALUE);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                    perkesoEmployee.setText(PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText(PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                    eisEmployee.setText(EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText(EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.valueOf(TOTAL);
                    total.setText(TOTAL_VALUE);
                }
                else
                {
                    Log.e(TAG, "onCalculateButton: No Value , What is User Doing man?");
                }


            }
            else
            {
                inputSalary.setError("Please Enter Your Salary Before Any Deduction.");
                Log.e(TAG, "onCalculateButton: No Value , What is User Doing man?");
            }
        }
        Log.e(TAG, "onCalculateButton: No Value , What is User Doing man?");
    }

    public void onCLickedSurvey(View view)
    {
        onSurvey = new Intent(CalculatorActivity.this, SurveyWeb.class);
        startActivity(onSurvey);
    }

    public void onEmploymentAct(View view)
    {
        onEmploymentAct = new Intent(CalculatorActivity.this, EmploymentAct.class);
        startActivity(onEmploymentAct);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // * Initiate User Interface Component
        initiateUserInterface();
        
    }
}
