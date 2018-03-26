package com.gdg.gdgiot;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private SoftPwm mLED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLED = new SoftPwm();
        mLED.setGpio("BCM13");
        mLED.setPwmFrequncy(120);
        mLED.setPwmDutyOn(40);
        mLED.setEable(true);
    }
}
