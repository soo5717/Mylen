package com.example.mylen.feature.eye.exercise;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.Image;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import com.example.mylen.R;
import com.example.mylen.feature.eye.report.EyeReportMain;
import com.example.mylen.feature.others.NavigationDrawer;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.util.concurrent.TimeUnit;

public class EyeExercise3 extends AppCompatActivity {

    TextView textView;
    TextureView cameraView;
    Boolean flag = false;
    Boolean faceFlag = false;
    TextView number;
    int count = 0;
    long true_past = System.currentTimeMillis();
    long false_past = System.currentTimeMillis();

    Button bt_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyeexercise3);
        startCamera();

        bt_bottom = findViewById(R.id.bt_bottom);

        bt_bottom.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        Intent intent = new Intent(EyeExercise3.this, EyeEnd.class);
        startActivity(intent);
    }

    private FirebaseVisionFaceDetectorOptions highAccuracyOpts =
            new FirebaseVisionFaceDetectorOptions.Builder()
                    .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                    .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                    .enableTracking()
                    .build();

    FirebaseVisionFaceDetector faceDetector = FirebaseVision.getInstance()
            .getVisionFaceDetector(highAccuracyOpts);

    private void startCamera() {

        PreviewConfig previewConfig = new PreviewConfig.Builder()
                .setLensFacing(CameraX.LensFacing.FRONT)
                .setTargetResolution(new Size(1080, 1920))
                .build();

        Preview preview = new Preview(previewConfig);

        preview.setOnPreviewOutputUpdateListener(
                output -> {
                    cameraView = findViewById(R.id.cameraView);
                    ViewGroup parent = (ViewGroup) cameraView.getParent();
                    parent.removeView(cameraView);
                    parent.addView(cameraView, 0);
                    SurfaceTexture surfaceTexture = output.getSurfaceTexture();
                    cameraView.setSurfaceTexture(surfaceTexture);

                }
        );

        ImageAnalysisConfig analyzerConfig = new ImageAnalysisConfig.Builder()
                .setLensFacing(CameraX.LensFacing.FRONT)
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis(analyzerConfig);
        imageAnalysis.setAnalyzer(new ImageProcessor());
        //

        CameraX.bindToLifecycle(this, preview, imageAnalysis);
    }

    private class ImageProcessor implements ImageAnalysis.Analyzer {

        private int degreesToFirebaseRotation(int degrees) {
            switch (degrees) {
                case 0:
                    return FirebaseVisionImageMetadata.ROTATION_0;
                case 90:
                    return FirebaseVisionImageMetadata.ROTATION_90;
                case 180:
                    return FirebaseVisionImageMetadata.ROTATION_180;
                case 270:
                    return FirebaseVisionImageMetadata.ROTATION_270;
                default:
                    throw new IllegalArgumentException(
                            "Rotation must be 0, 90, 180, or 270.");
            }
        }

        @Override
        public void analyze(ImageProxy imageProxy, int degrees) {

            final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            textView = (TextView) findViewById(R.id.label);
            number = (TextView) findViewById(R.id.number);
            long currentTimestamp = System.currentTimeMillis();
            if (currentTimestamp >= TimeUnit.SECONDS.toMillis(1)) {
                Image mediaImage = imageProxy.getImage();
                int imageRotation = degreesToFirebaseRotation(degrees);
                FirebaseVisionImage image = FirebaseVisionImage.fromMediaImage(mediaImage, imageRotation);
                faceDetector.detectInImage(image)
                        .addOnCompleteListener(
                                task -> {
                                    if(task.isComplete()) {
                                        textView.setText("complete");
                                    }
                                }

                        )
                        .addOnSuccessListener(
                                faces -> {
                                    faceFlag = false;
                                    for (FirebaseVisionFace face : faces) {
                                        if(face.getTrackingId() != -1) {
                                            faceFlag = true;
                                            Log.d("페이스플래그", "페이스플래그");
                                            if (flag == true) {
                                                Log.d("플래그트루", "플래그트루");
                                                long true_current = System.currentTimeMillis();
                                                long time = (long) ((true_current - true_past) / 1000.0);
                                                false_past = true_current;
                                                if (time >= 4.0) {
                                                    count = count + 1;
                                                    true_past = true_current;
                                                    false_past = true_current;
                                                    number.setText(String.valueOf(count));
                                                    vibrator.vibrate(500);
                                                }
                                            } else {
                                                Log.d("플래그 폴스", "플래그 폴스");
                                                long false_current = System.currentTimeMillis();
                                                long time = (long) ((false_current - false_past) / 1000.0);
                                                true_past = false_current;
                                                if (time >= 8.0) {
                                                    count = count + 1;
                                                    false_past = false_current;
                                                    true_past = false_current;
                                                    number.setText(String.valueOf(count));
                                                    vibrator.vibrate(500);
                                                }
                                            }
                                            Log.d("얼굴 있", "얼굴 있");
                                            if (face.getLeftEyeOpenProbability() < 0.4 || face.getRightEyeOpenProbability() < 0.4) {
                                                textView.setText("Blinked");
                                                flag = true;
                                            } else {
                                                textView.setText("Not Blinked");
                                                flag = false;
                                            }
                                        }

                                    }
                                    if(faceFlag == false){
                                        Log.d("페이스플래그 폴스", "페이스플래그 폴스");
                                        false_past = System.currentTimeMillis();
                                        true_past = System.currentTimeMillis();
                                    }

                                })
                        .addOnFailureListener(
                                e -> textView.setText("Error"));
            }
            if (imageProxy == null || imageProxy.getImage() == null) {
                return;
            }
        }
    }

}
