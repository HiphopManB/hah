package com.example.richkang.mydemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
    private MainActivity instance;

    private SensorListener listener;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        listener = new SensorListener();

        registerSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    class SensorListener implements SensorEventListener {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            System.out.println(event.values.length);
            count+= event.values[0];
            System.out.println(count);
            Toast.makeText(MainActivity.this,count+"",Toast.LENGTH_LONG).show();
            showToast(count+"xxxxxxxxxxxxxxxxxxxxxxx");

        }

    }


    private void registerSensor(int sensorType) {
        mSensorManager =(SensorManager) getActivity().getSystemService(Activity.SENSOR_SERVICE);
        // sensorType is either Sensor.TYPE_STEP_COUNTER or Sensor.TYPE_STEP_DETECTOR
        mSensor = mSensorManager.getDefaultSensor(sensorType);
        if (mSensorManager.getDefaultSensor(sensorType) != null){
            // Success! There's a magnetometer.
            showToast("sensorType支持");
        }
        else {
            // Failure! No magnetometer.
            showToast("sensorType 不支持");
        }
        mSensorManager.registerListener(listener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

}

    private Activity getActivity() {
        // TODO Auto-generated method stub
        return instance;
    }

    public void showToast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
        Log.e("steplog",msg);
    }
}
