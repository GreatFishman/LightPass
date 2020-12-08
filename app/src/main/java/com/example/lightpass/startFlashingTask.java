package com.example.lightpass;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;

import java.util.ArrayList;

public class startFlashingTask extends AsyncTask <ArrayList<Integer>, Void, Void> {

    Context context_m;

    public startFlashingTask(Context context) {
        context_m = context;
    }

    @Override
    protected Void doInBackground(ArrayList<Integer>... params) {
        CameraManager cameraManager = (CameraManager) context_m.getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            ArrayList<Integer> instructions = params[0];
            for (int i = instructions.size() - 1; i >= 0; i--) {
                int currentInstruction = instructions.get(i);
                for (int j = 0; j < currentInstruction; j++) {
                    cameraManager.setTorchMode(cameraId, true);
                    Thread.sleep(300);
                    cameraManager.setTorchMode(cameraId,false);
                    Thread.sleep(300);
                }
                Thread.sleep(3000);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
