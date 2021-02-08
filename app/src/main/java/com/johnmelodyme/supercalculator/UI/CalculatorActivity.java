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
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    public String userSalaryInput;
    public Intent onSurvey, onEmploymentAct, onDonation, onAboutDev;
    public EditText inputSalary;
    public TextView epfEmployee, epfEmployer, perkesoEmployee, perkesoEmployer;
    public TextView eisEmployee, eisEmployer, total;
    public RadioGroup kwsp_contribution;
    public RadioButton epfSeven, epfNine, epfEleven, epfTwelve, epfThirteen;
    public RadioButton malaysian, foreigner;
    public Button calculate, clickedButton;
    public MenuInflater menuInflater;

    @SuppressLint("ResourceAsColor")
    public void initiateUserInterface()
    {
        inputSalary = (EditText) findViewById(R.id.inputsalary);
        inputSalary.setHintTextColor(R.color.grey);

        kwsp_contribution = (RadioGroup) findViewById(R.id.kwspcontribution);

        epfSeven = (RadioButton) findViewById(R.id.kwspseven);
        epfNine = (RadioButton) findViewById(R.id.kwspnine);
        epfEleven = (RadioButton) findViewById(R.id.kwspeleven);
        epfTwelve = (RadioButton) findViewById(R.id.kwsptwelve);
        epfThirteen = (RadioButton) findViewById(R.id.kwspthirteen);

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

        inputSalary.setText(null);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.donation:
            {
                onDonation = new Intent(Intent.ACTION_VIEW);
                onDonation.setData(Uri.parse(Const.DONATION_URL));
                startActivity(onDonation);
                break;
            }
            case R.id.about:
            {
                onAboutDev = new Intent(Intent.ACTION_VIEW);
                onAboutDev.setData(Uri.parse(Const.ABOUT_URL));
                startActivity(onAboutDev);
                break;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item);
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

            case R.id.kwsptwelve:
            {
                if (isRadioButtonChecked)
                {
                    KWSP_CONTRIBUTION = Const.KWSP_TWELVE_PERCENT;
                    Log.d(TAG, "onRadioButtonClicked: " + KWSP_CONTRIBUTION + " EPF Contribution Selected ");
                }

                break;
            }

            case R.id.kwspthirteen:
            {
                if (isRadioButtonChecked)
                {
                    KWSP_CONTRIBUTION = Const.KWSP_THIRTEEN_PERCENT;
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
     *             <br> Reference : https://www.kwsp.gov.my/employer/contribution/all-about-your-responsibility
     *             <br> Reference : https://www.nbc.com.my/socso/socso-table-rates-jadual-caruman-socso.html
     *             <br> Reference: https://blog.talenox.com/calculate-eis-contribution/
     *             </p>
     */
    @SuppressLint({"SetTextI18n", "ResourceType", "DefaultLocale"})
    public void onCalculateButton(View view)
    {
        clickedButton = (Button) view;
        userSalaryInput = inputSalary.getText().toString();

        double EMPLOYEE_KWSP, EMPLOYER_KWSP;
        double EMPLOYEE_PERKESO, EMPLOYER_PERKESO;
        double EMPLOYEE_EIS, EMPLOYER_EIS;
        double TOTAL;

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
                    EMPLOYEE_KWSP = (double) (INCOME * Const.FORMER_CURRENT_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (double) (INCOME * Const.FORMER_CURRENT_KWSP_EMPLOYER_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                    epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                    perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                    eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.format("%.2f", TOTAL);
                    total.setText(TOTAL_VALUE);
                }
                //* User Selected 9% KWSP Contribution
                else if (epfNine.isChecked())
                {
                    //* Malaysian Whose Salary > 5000;
                    if (INCOME > 5000 && isMalaysian)
                    {
                        Log.i(TAG, "onCalculateButton: Malaysian Whose Salary > 5000");
                        EMPLOYEE_KWSP = (double) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                        EMPLOYER_KWSP = (double) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE_SALARY_ABOVE_AVERAGE / 100);
                        KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);
                        Log.i(TAG, "KWSP SALARY MORE THAN 5K");

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.format("%.2f", TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                    //* Foreigner Whose Salary > 5000;
                    else if (INCOME > 5000 && !isMalaysian)
                    {
                        Log.i(TAG, "onCalculateButton: Foreigner Whose Salary > 5000");
                        EMPLOYEE_KWSP = (double) (INCOME * Const.FOREIGNER_CURRENT_KWSP_RATE_EMPLOYEE / 100);
                        EMPLOYER_KWSP = (double) (INCOME * Const.INCOME_MORE_FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER);
                        KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);
                        Log.i(TAG, "KWSP SALARY MORE THAN 5K");

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.format("%.2f", TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                    //* Foreigner Whose Salary < 5000;
                    else if (INCOME < 5000 && !isMalaysian)
                    {
                        Log.i(TAG, "onCalculateButton: Foreigner Whose Salary < 5000");
                        EMPLOYEE_KWSP = (double) (INCOME * Const.FOREIGNER_CURRENT_KWSP_RATE_EMPLOYEE / 100);
                        EMPLOYER_KWSP = (double) (INCOME * Const.FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER);
                        KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.format("%.2f", TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                    //* Malaysian Whose Salary < 5000;
                    else
                    {
                        Log.i(TAG, "onCalculateButton:  Malaysian Whose Salary < 5000");
                        EMPLOYEE_KWSP = (double) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                        EMPLOYER_KWSP = (double) (INCOME * Const.CURRENT_KWSP_EMPLOYEE_RATE / 100);
                        KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                        KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                        epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                        epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                        Log.i(TAG, "Value =>" + KWSP_EMPLOYEE_VALUE + EMPLOYER_KWSP);

                        //* PERKESO Value:
                        EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                        EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                        PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                        PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                        perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                        perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                        Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                        //* EIS Value:
                        EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                        EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                        EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                        EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                        eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                        eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                        Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                        //* Total Value :
                        TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                        TOTAL_VALUE = String.format("%.2f", TOTAL);
                        total.setText(TOTAL_VALUE);
                    }
                }
                //* User Selected 11% KWSP Contribution
                else if (epfEleven.isChecked())
                {
                    EMPLOYEE_KWSP = (double) (INCOME * Const.FORMER_KWSP_EMPLOYEE_RATE / 100);
                    EMPLOYER_KWSP = (double) (INCOME * Const.FORMER_KWSP_EMPLOYER_RATE / 100);
                    KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                    epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "KWSP Value =>" + KWSP_EMPLOYEE_VALUE + KWSP_EMPLOYER_VALUE);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                    perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                    eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.format("%.2f", TOTAL);
                    total.setText("MYR " + TOTAL_VALUE);
                }
                //* User Selected 12% KWSP Contribution
                else if (epfTwelve.isChecked())
                {
                    EMPLOYEE_KWSP = (double) (INCOME * Const.KWSP_TWELVE_PERCENT / 100);
                    EMPLOYER_KWSP = (double) (INCOME * Const.KWSP_THIRTEEN_PERCENT / 100);
                    KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                    epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "KWSP Value =>" + KWSP_EMPLOYEE_VALUE + KWSP_EMPLOYER_VALUE);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                    perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                    eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.format("%.2f", TOTAL);
                    total.setText("MYR " + TOTAL_VALUE);
                }
                //* User Selected 13% KWSP Contribution
                else if (epfThirteen.isChecked())
                {
                    EMPLOYEE_KWSP = (double) (INCOME * Const.KWSP_THIRTEEN_PERCENT / 100);
                    EMPLOYER_KWSP = (double) (INCOME * Const.KWSP_THIRTEEN_PERCENT / 100);
                    KWSP_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_KWSP);
                    KWSP_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_KWSP);
                    epfEmployee.setText("MYR " + KWSP_EMPLOYEE_VALUE);
                    epfEmployer.setText("MYR " + KWSP_EMPLOYER_VALUE);
                    Log.i(TAG, "KWSP Value =>" + KWSP_EMPLOYEE_VALUE + KWSP_EMPLOYER_VALUE);

                    //* PERKESO Value:
                    EMPLOYEE_PERKESO = (double) (INCOME * Const.EMPLOYEE_PERKESO_RATE / 100);
                    EMPLOYER_PERKESO = (double) (INCOME * Const.EMPLOYER_PERKESO_RATE / 100);
                    PERKESO_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_PERKESO);
                    PERKESO_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_PERKESO);
                    perkesoEmployee.setText("MYR " + PERKESO_EMPLOYEE_VALUE);
                    perkesoEmployer.setText("MYR " + PERKESO_EMPLOYER_VALUE);
                    Log.i(TAG, "PERKESO Value =>" + PERKESO_EMPLOYEE_VALUE + PERKESO_EMPLOYER_VALUE);

                    //* EIS Value:
                    EMPLOYEE_EIS = (double) (INCOME * Const.EMPLOYEE_EIS_RATE / 100);
                    EMPLOYER_EIS = (double) (INCOME * Const.EMPLOYER_EIS_RATE / 100);
                    EIS_EMPLOYEE_VALUE = String.format("%.2f", EMPLOYEE_EIS);
                    EIS_EMPLOYER_VALUE = String.format("%.2f", EMPLOYER_EIS);
                    eisEmployee.setText("MYR " + EIS_EMPLOYEE_VALUE);
                    eisEmployer.setText("MYR " + EIS_EMPLOYER_VALUE);
                    Log.i(TAG, "EIS Value =>" + EIS_EMPLOYEE_VALUE + EIS_EMPLOYER_VALUE);

                    //* Total Value :
                    TOTAL = (double) (INCOME - EMPLOYEE_EIS - EMPLOYEE_KWSP - EMPLOYEE_PERKESO);
                    TOTAL_VALUE = String.format("%.2f", TOTAL);
                    total.setText("MYR " + TOTAL_VALUE);
                }
                else
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

        // * Clear All;
        calculate.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                initiateUserInterface();
                return true;
            }
        });

        // * Add Action Key Listener;
        inputSalary.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND)
                {
                    onUserInteraction();
                    handled = true;
                }
                return handled;
            }
        });
    }

    // * Hide Keyboard;
    @Override
    public void onUserInteraction()
    {
        if (getCurrentFocus() != null)
        {
            InputMethodManager imm;
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 1);
        }
    }
}
