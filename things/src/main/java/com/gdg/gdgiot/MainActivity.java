package com.gdg.gdgiot;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

import static com.gdg.gdgiot.Constants.DO_FREQ_INT;
import static com.gdg.gdgiot.Constants.FA_FREQ_INT;
import static com.gdg.gdgiot.Constants.LA_FREQ_INT;
import static com.gdg.gdgiot.Constants.MI_FREQ_INT;
import static com.gdg.gdgiot.Constants.RE_FREQ_INT;
import static com.gdg.gdgiot.Constants.SO_FREQ_INT;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String PWM_BUS = "PWM1";
    private Speaker mSpeaker;
    private static final String RED_LED_PIN = "BCM20";
    private static final String GREEN_LED_PIN = "BCM19";
    private static final String BLUE_LED_PIN = "BCM21";
    private static final String BUTTOM_PIN = "BCM12";
    private Gpio redLed,greenLed,blueLed,mGpioButton;
    private SoftPwm redLedPwm,greenLedPwm,blueLedPwm;
    private gpioButton mButton;
    private boolean switchLedOn=true;
    private int i=0;
    private int[] littleStart = {
            DO_FREQ_INT, DO_FREQ_INT, SO_FREQ_INT, SO_FREQ_INT, LA_FREQ_INT, LA_FREQ_INT, SO_FREQ_INT, FA_FREQ_INT, FA_FREQ_INT, MI_FREQ_INT, MI_FREQ_INT, RE_FREQ_INT, RE_FREQ_INT, DO_FREQ_INT,
            SO_FREQ_INT, SO_FREQ_INT, FA_FREQ_INT, FA_FREQ_INT, MI_FREQ_INT, MI_FREQ_INT, RE_FREQ_INT, SO_FREQ_INT, SO_FREQ_INT, FA_FREQ_INT, FA_FREQ_INT, MI_FREQ_INT, MI_FREQ_INT, RE_FREQ_INT,
            DO_FREQ_INT, DO_FREQ_INT, SO_FREQ_INT, SO_FREQ_INT, LA_FREQ_INT, LA_FREQ_INT, SO_FREQ_INT, FA_FREQ_INT, FA_FREQ_INT, MI_FREQ_INT, MI_FREQ_INT, RE_FREQ_INT, RE_FREQ_INT, DO_FREQ_INT };
    private Handler mHandlerTime = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSpeaker();
        try {
            mSpeaker.play(/* G4 */ DO_FREQ_INT);//391.995
        } catch (IOException e) {
            Log.e(TAG, "Error playing note", e);
        }

        //PeripheralManager service = PeripheralManager.getInstance();
        try {
            redLed = PeripheralManager.getInstance().openGpio(RED_LED_PIN);
            greenLed = PeripheralManager.getInstance().openGpio(GREEN_LED_PIN);
            blueLed = PeripheralManager.getInstance().openGpio(BLUE_LED_PIN);
            mGpioButton = PeripheralManager.getInstance().openGpio(BUTTOM_PIN);
            mGpioButton.setDirection(Gpio.DIRECTION_IN);
            mGpioButton.setEdgeTriggerType(Gpio.EDGE_FALLING);
            mGpioButton.registerGpioCallback(new GpioCallback() {
                @Override
                public boolean onGpioEdge(Gpio gpio) {
                    Log.i(TAG, "GPIO changed, button pressed");
                    // Return true to continue listening to events
                    if(switchLedOn){
                        redLedPwm.setEable(false);
                        greenLedPwm.setEable(false);
                        blueLedPwm.setEable(false);
                        switchLedOn = false;
                        try {
                            mSpeaker.stop();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.d("kevin","Button ON press = "+switchLedOn);
                    }else{
                        mHandlerTime.postDelayed(timerRun, 500);
                        redLedPwm.setEable(true);
                        greenLedPwm.setEable(true);
                        blueLedPwm.setEable(true);
                        switchLedOn = true;
                        try {
                            mSpeaker.play(DO_FREQ_INT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("kevin","Button Off press = "+switchLedOn);
                    }

                    return true;
                }
            });
//            mButton = new gpioButton(BUTTOM_PIN,
//                    // high signal indicates the button is pressed
//                    // use with a pull-down resistor
//                    gpioButton.LogicState.PRESSED_WHEN_HIGH
//            );
//            mButton.setOnButtonEventListener(new gpioButton.OnButtonEventListener() {
//                @Override
//                public void onButtonEvent(gpioButton button, boolean pressed) {
//                    // do something awesome
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        redLedPwm= new SoftPwm(redLed,50,1);
        redLedPwm.setEable(true);
        greenLedPwm = new SoftPwm(greenLed,50,1);
        greenLedPwm.setEable(true);
        blueLedPwm = new SoftPwm(blueLed,50,1);
        blueLedPwm.setEable(true);

        mHandlerTime.postDelayed(timerRun, 500);
    }

    private final Runnable timerRun = new Runnable()
    {
        public void run()
        {


            if(i<littleStart.length){
                try {
                    mSpeaker.play(littleStart[i]);
                    Log.d("kevin","play little star ="+littleStart[i]+"/"+i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                i++;

            }else{
                i=0;
            }
            if(switchLedOn) {
                mHandlerTime.postDelayed(this, 500);
            }else{
                try {
                    mSpeaker.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroySpeaker();
        try {
            redLedPwm.close();
            redLed.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            greenLedPwm.close();
            greenLed.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            blueLedPwm.close();
            blueLed.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mButton.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setupSpeaker() {
        try {
            mSpeaker = new Speaker(PWM_BUS);
            mSpeaker.stop(); // in case the PWM pin was enabled already
        } catch (IOException e) {
            Log.e(TAG, "Error initializing speaker");
        }
    }

    private void destroySpeaker() {
        if (mSpeaker != null) {
            try {
                mSpeaker.stop();
                mSpeaker.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing speaker", e);
            } finally {
                mSpeaker = null;
            }
        }
    }
}
