package com.uniftec.sensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView active;
    private ImageView disabled;
    private TextView valueClose;
    private TextView valueLight;
    private ConstraintLayout layout;

    private SensorManager ManagerSensor;
    private Sensor NearBy;
    private Sensor Light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        active = (ImageView) findViewById(R.id.imageView);
        disabled = (ImageView) findViewById(R.id.imageView2);
        valueClose = (TextView) findViewById(R.id.textView);
        valueLight = (TextView) findViewById(R.id.textView2);
        layout = (ConstraintLayout) findViewById(R.id.layout);

        ManagerSensor = (SensorManager)getSystemService(SENSOR_SERVICE);

        if (ManagerSensor.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            NearBy = ManagerSensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }

        if (ManagerSensor.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            Light = ManagerSensor.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        ManagerSensor.registerListener(new SensorProximidade(), NearBy, SensorManager.SENSOR_DELAY_UI);
        ManagerSensor.registerListener(new SensorLuminosidade(), Light, SensorManager.SENSOR_DELAY_UI);
    }

    class SensorProximidade implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            valueClose.setText(String.valueOf(x));

            if (x < 6)
            {
                active.setVisibility(View.VISIBLE);
                disabled.setVisibility(View.INVISIBLE);
            }
            else
            {
                active.setVisibility(View.INVISIBLE);
                disabled.setVisibility(View.VISIBLE);
            }
        }
    }

    class SensorLuminosidade implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            valueLight.setText(String.valueOf(x));

            if (x < 10000)
                layout.setBackgroundColor(Color.GRAY);
            else
                layout.setBackgroundColor(Color.WHITE);
        }
    }
}