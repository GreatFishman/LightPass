package com.example.lightpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startFlashing(View view) {
        TextView passnumber_editText = findViewById(R.id.editTextPass);
        int passnumber = Integer.parseInt(passnumber_editText.getText().toString());
        ArrayList<Integer> flashInstructions = new ArrayList<>();
        int modded = passnumber;
        for (int i = 0; i < passnumber_editText.getText().length(); i++) {
            flashInstructions.add(modded % 10);
            modded = (modded - flashInstructions.get(i)) / 10;
        }

        startFlashingTask async = new startFlashingTask(this);
        async.execute(flashInstructions);
    }

    public void shortFlash(View view) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            Thread.sleep(300);
            cameraManager.setTorchMode(cameraId,false);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void longFlash(View view) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            Thread.sleep(1500);
            cameraManager.setTorchMode(cameraId,false);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}