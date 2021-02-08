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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.johnmelodyme.supercalculator.Const.Const;
import com.johnmelodyme.supercalculator.R;

public class CalculatorActivity extends AppCompatActivity
{
    private static final String TAG = "MYWAGES =>";
    public boolean isRadioButtonChecked, isMalaysian;
    public double KWSP_CONTRIBUTION;
    public String userSalaryInput, nationality;
    public Intent onSurvey, onEmploymentAct;
    public EditText inputSalary;
    public TextView epfEmployee, epfEmployer, perkesoEmployee, perkesoEmployer;
    public TextView eisEmployee, eisEmployer, total;
    public RadioGroup kwsp_contribution;
    public RadioButton epfSeven, epfNine, epfEleven;
    public RadioButton malaysian, foreigner;
    public Button calculate, clickedButton;

    @SuppressLint("ResourceAsColor")
    public void initiateUserInterface()
    {
        inputSalary = (EditText) findViewById(R.id.inputsalary);
        inputSalary.setHintTextColor(R.color.grey);

        kwsp_contribution = (RadioGroup) findViewById(R.id.kwspcontribution);

        epfSeven = (RadioButton) findViewById(R.id.kwspseven);
        epfNine = (RadioButton) findViewById(R.id.kwspnine);
        epfEleven = (RadioButton) findViewById(R.id.kwspeleven);

        malaysian = (RadioButton) findViewById(R.id.malaysian);
        foreigner = (RadioButton) findViewById(R.id.nonmalaysian);
        //* Set Malaysian As `Default`
        malaysian.setChecked(true);

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

    @SuppressLint("NonConstantResourceId")
    public void onCitizenship(View view)
    {
        isMalaysian = ((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.malaysian:
            {
                isMalaysian = true;
                Log.i(TAG, "onCitizenship:  Malaysian");
                break;
            }

            case R.id.nonmalaysian:
            {
                isMalaysian = false;
                Log.i(TAG, "onCitizenship:  Non-Malaysian");
                break;
            }

            default:
            {
                Log.e(TAG, "onCitizenship: Come one, Pick a side!");
                break;
            }
        }
    }

    /**
     * @param view <p>
     *             Reference : https://www.kwsp.gov.my/employer/contribution/all-about-your-responsibility
     *             </p>
     */
    @SuppressLint({"SetTextI18n", "ResourceType"})
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

            if (!(TextUtils.isEmpty(userSalaryInput)) && !(kwsp_contribution.getCheckedRadioButtonId() < 0))
            {
                double INCOME = Double.parseDouble(userSalaryInput);

                //* User Selected 7% KWSP Contribution
                if (epfSeven.isChecked())
                {
                    EMPLOYEE_KWSP = (int) (INCOME * Const.FORMER_CURRENT_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (int) (INCOME * Const.FORMER_CURRENT_KWSP_EMPLOYER_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                    epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                    perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                    eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.valueOf(TOTAL);
                    total.setText(TOTAL_VALUE);
                }
                //* User Selected 9% KWSP Contribution
                else if (epfNine.isChecked())
                {
                    //* Malaysian Whose Salary > 5000;
                    if (INCOME > 5000 && isMalaysian)
                    {
                        Log.i(TAG, "onCalculateButton: Malaysian Whose Salary > 5000");
                        EMPLOYEE_KWSP = (int) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                        EMPLOYER_KWSP = (int) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE_SALARY_ABOVE_AVERAGE / 100);
                        KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);
                        Log.i(TAG, "KWSP SALARY MORE THAN 5K");

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.valueOf(TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                    //* Foreigner Whose Salary > 5000;
                    else if (INCOME > 5000 && !isMalaysian)
                    {
                        Log.i(TAG, "onCalculateButton: Foreigner Whose Salary > 5000");
                        EMPLOYEE_KWSP = (int) (INCOME * Const.FOREIGNER_CURRENT_KWSP_RATE_EMPLOYEE / 100);
                        EMPLOYER_KWSP = (int) (INCOME * Const.INCOME_MORE_FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER);
                        KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);
                        Log.i(TAG, "KWSP SALARY MORE THAN 5K");

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.valueOf(TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                    //* Foreigner Whose Salary < 5000;
                    else if (INCOME < 5000 && !isMalaysian)
                    {
                        Log.i(TAG, "onCalculateButton: Foreigner Whose Salary < 5000");
                        EMPLOYEE_KWSP = (int) (INCOME * Const.FOREIGNER_CURRENT_KWSP_RATE_EMPLOYEE / 100);
                        EMPLOYER_KWSP = (int) (INCOME * Const.FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER);
                        KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.valueOf(TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                    //* Malaysian Whose Salary < 5000;
                    else
                    {
                        Log.i(TAG, "onCalculateButton:  Malaysian Whose Salary < 5000");
                        EMPLOYEE_KWSP = (int) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                        EMPLOYER_KWSP = (int) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                        KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.valueOf(TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                }
                //* User Selected 11% KWSP Contribution
                else if (epfEleven.isChecked())
                {
                    EMPLOYEE_KWSP = (int) (INCOME * Const.FORMER_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (int) (INCOME * Const.FORMER_KWSP_EMPLOYER_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.valueOf(EMPLOYER_KWSP);
                    epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "KWSP Value =>" + KWSP_EMPLOYEE_VALUE + KWSP_EMPLOYER_VALUE);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (int) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (int) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.valueOf(EMPLOYER_PERKESO);
                    perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (int) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (int) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.valueOf(EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.valueOf(EMPLOYER_EIS);
                    eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (int) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.valueOf(TOTAL);
                    total.setText("MYR " + TOTAL_VALUE);
                } else
                {
                    Log.e(TAG, "...");
                }
            }
            //* If User Didn't clicked the KWSP Contribution;
            else if (kwsp_contribution.getCheckedRadioButtonId() < 0)
            {
                Toast.makeText(
                        this,
                        Const.EXCEPTION_RADIO_GROUP,
                        Toast.LENGTH_LONG
                ).show();
            }
            //* Raise Exception to User when User enter NOTHING;
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
