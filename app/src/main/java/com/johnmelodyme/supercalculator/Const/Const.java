package com.johnmelodyme.supercalculator.Const;

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

public class Const
{
    public static String APP_NAME;
    public static String SURVEY_URL;
    public static String ON_START_VALUE;
    public static String EMPTY_VALUE_EXCEPTION;
    public static String EMPLOYMENT_ACT_URL;
    public static String EXCEPTION_RADIO_GROUP;

    static
    {
        EXCEPTION_RADIO_GROUP = "Please Select A Valid KWSP Contribution !";
        EMPLOYMENT_ACT_URL = "https://drive.google.com/file/d/1XHZr5vqweFdSvG29nsqG13WGPePnz23q/view?usp=sharing";
        EMPTY_VALUE_EXCEPTION = "Please Enter Your Salary Before Any Deduction.";
        ON_START_VALUE = "0.00";
        APP_NAME = "Malaysian Wages Calculator";
        SURVEY_URL = "https://docs.google.com/forms/d/e/1FAIpQLSexRHdz27arRjfH4rIeVNhP-oLUe5QgT8ZqV2gM-b3ER9vdYw/viewform";
    }

    public static double FORMER_KWSP_EMPLOYEE_RATE;
    public static double FORMER_CURRENT_KWSP_EMPLOYEE_RATE;
    public static double CURRENT_KWSP_EMPLOYEE_RATE;
    public static double CURRENT_KWSP_EMPLOYEE_RATE_SALARY_ABOVE_AVERAGE;
    public static double FORMER_KWSP_EMPLOYER_RATE;
    public static double FORMER_CURRENT_KWSP_EMPLOYER_RATE;
    public static double CURRENT_KWSP_EMPLOYER_RATE;
    public static double EMPLOYEE_PERKESO_RATE;
    public static double EMPLOYER_PERKESO_RATE;
    public static double FOR_PERKESO_RATE;
    public static double EMPLOYEE_EIS_RATE;
    public static double EMPLOYER_EIS_RATE;
    public static double FOREIGNER_CURRENT_KWSP_RATE_EMPLOYEE;
    public static double FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER;
    public static double INCOME_MORE_FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER;

    static
    {
        INCOME_MORE_FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER = (double) 5.5;
        FOREIGNER_CURRENT_KWSP_RATE_EMPLOYER = (double) 0.005;
        FOREIGNER_CURRENT_KWSP_RATE_EMPLOYEE = (double) 9;
        CURRENT_KWSP_EMPLOYEE_RATE_SALARY_ABOVE_AVERAGE = 12;
        CURRENT_KWSP_EMPLOYER_RATE = (double) 13;
        FORMER_KWSP_EMPLOYER_RATE = (double) 13;
        EMPLOYEE_EIS_RATE = 0.2;
        EMPLOYER_EIS_RATE = 0.2;
        FORMER_CURRENT_KWSP_EMPLOYER_RATE = (double) 13;
        FORMER_CURRENT_KWSP_EMPLOYEE_RATE = (double) 7;
        FORMER_KWSP_EMPLOYEE_RATE = (double) 11;
        CURRENT_KWSP_EMPLOYEE_RATE = (double) 9;
        EMPLOYEE_PERKESO_RATE = (double) 0.5;
        EMPLOYER_PERKESO_RATE = (double) 1.75;
    }
}