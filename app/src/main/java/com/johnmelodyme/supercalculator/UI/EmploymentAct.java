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
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.johnmelodyme.supercalculator.Const.Const;
import com.johnmelodyme.supercalculator.R;

public class EmploymentAct extends AppCompatActivity
{
    private static final String TAG = "EmploymentAct";
    private WebView webview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment);

        webview = (WebView) findViewById(R.id.employmentwebview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().supportZoom();
        webview.loadUrl(Const.EMPLOYMENT_ACT_URL);

        Log.i(TAG, "onCreate: " + "Load" + Const.EMPLOYMENT_ACT_URL);
    }
}