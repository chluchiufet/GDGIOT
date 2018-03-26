package com.gdg.gdgiot;

import android.util.Log;

/**
 * Created by kevin on 2018/3/17.
 */

public class SoftPwm {
    private String mGpio = "";
    private int mPwmDutyOn = 50;
    private int mPwmDutyOff = 50;
    private int mPwmDutyOnTime = 500;
    private int mPwmDutyOffTime = 500;
    private int mPwmFrequncy = 50;
    private final double PWMTIME = 0.01;
    private double mPwmTimeDouble;
    private int mPwmTime;
    private boolean isEnable;

    public void SoftPwm(String gpio,int pwmDutyOn, int pwmFrequency){
        mGpio = gpio;
        mPwmDutyOn = pwmDutyOn;
        mPwmFrequncy = pwmFrequency;
    }

    private void pwmSetTime(){
        if(mPwmFrequncy !=0) {
            mPwmTimeDouble = 1000 / mPwmFrequncy;
        }else{
            mPwmTimeDouble = 1000 / 50;
        }

        if(mPwmDutyOn>100){
            mPwmDutyOn = 100;
        }
        mPwmTime = (int) Math.round(mPwmTimeDouble);
        mPwmDutyOnTime = (int) Math.round(mPwmDutyOn*mPwmTime*PWMTIME);
        mPwmDutyOffTime = mPwmTime - mPwmDutyOnTime;

    }

    private void pwmStart(){
        new Thread(new Runnable() {
            public void run() {
                while (isEnable){
                    Log.d("kevin","PwmTimeRun");
                    if(mPwmDutyOnTime>0){
                        try {
                            Thread.sleep(mPwmDutyOnTime);
                            Log.d("kevin","PwmDutyON ="+mGpio+"_"+mPwmDutyOnTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (mPwmDutyOffTime>0){
                        try {
                            Thread.sleep(mPwmDutyOffTime);
                            Log.d("kevin","PwmDutyOFF ="+mGpio+"_"+mPwmDutyOffTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void setGpio(String gpio){
        mGpio = gpio;
    }

    public void setPwmDutyOn(int pwmDutyOn){
        mPwmDutyOn = pwmDutyOn;
        pwmSetTime();
    }

    public void setPwmFrequncy(int pwmFrequncy){
        mPwmFrequncy = pwmFrequncy;
        pwmSetTime();
    }

    public void setEable(boolean enable){
        if(enable){
            pwmStart();
        }
    }
}
