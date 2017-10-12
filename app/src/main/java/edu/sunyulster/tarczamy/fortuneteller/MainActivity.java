package edu.sunyulster.tarczamy.fortuneteller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends AppCompatActivity {

    private TextView answerDisplay;
    private TextView questionDisplay;
    private SensorManager sm;
    private float accel;
    private float lastAccel;
    private float shakeForce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        accel = SensorManager.GRAVITY_EARTH;
        lastAccel = SensorManager.GRAVITY_EARTH;
        shakeForce = 0.00f;

        answerDisplay = (TextView) findViewById(R.id.answer);
        questionDisplay = (TextView) findViewById(R.id.question);

        //Set the answer text to blank
        answerDisplay.setText("");

        //Set the question text to the input from QuestionActivity
        questionDisplay.setText(QuestionActivity.userQuestion);
    }

    protected String getResponse() {
        //Create an array of possible responses
        String[] responses = {"Yes", "No", "Maybe", "Ask again later", "No chance", "Without a doubt", "Concentrate and ask again", "Outlook not so good", "Better not tell you now",
        "Don't count on it", "Very doubtful", "You may rely on it", "Most likely" };

        //Generate a random number to pick from the array
        Random rand = new Random();
        int range = rand.nextInt(responses.length);

        //Pick a random value from the array
        String answer = responses[range];

        //Return the chosen response
        return answer;
    }   //End getResponse

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            lastAccel = accel;
            accel = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = accel - lastAccel;
            shakeForce = shakeForce * 0.9f + delta;

            //If the device is shaken at the set force
            if (shakeForce > 2) {
                answerDisplay.setText(getResponse());
            }   //End if


        }   //End onSensorChanged

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }   //End onAccuracyChanged
    };
}   //End MainActivity
