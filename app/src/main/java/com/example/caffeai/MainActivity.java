package com.example.caffeai;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.caffeai.ml.SRCNN;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btLoadVideo;
    TextView tvResult;
    VideoView videoView;
    ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video_view);
        tvResult = findViewById(R.id.tv_result);
        btLoadVideo = findViewById(R.id.bt_load_video);


        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    try {
                        videoView.setVideoURI(result);
                        videoView.start(); // Start playing the video

                        Bitmap videoFrame = getFrameFromVideo(result);
                        if (videoFrame != null) {

                            outputGenerator(videoFrame);
                        } else {
                            tvResult.setText("hello");
                            Log.e("MainActivity", "Null Bitmap received");
                        }
                        //tvResult.setText("Video processed");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("MainActivity", "Exception: " + e.getMessage());
                    }
                } else {
                    Log.e("MainActivity", "Result Uri is null");
                }
            }
        });


        btLoadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("video/*");
            }
        });

        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.vn/search?q=" + tvResult.getText().toString()));
                startActivity(intent);
            }
        });
    }


    private Bitmap getFrameFromVideo(Uri videoUri) {
        if (videoUri == null) {
            Log.e("MainActivity", "Video Uri is null");
            return null;
        }
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(this, videoUri);
            return retriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MainActivity", "Error extracting frame from video: " + e.getMessage());
            return null;
        }
    }



    private void outputGenerator(Bitmap imageBitmap) {
        try {

            SRCNN model = SRCNN.newInstance(getApplicationContext());
            ByteBuffer byteBuffer = convertBitmapToByteBuffer(imageBitmap);
            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 1, 1, 3}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            SRCNN.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();


            float[] confidences=outputFeature0.getFloatArray();


            /*int maxPos=0;
            float maxConfidences=0;
            for (int i=0;i<confidences.length;i++){
                if (confidences[i]>maxConfidences)
                {
                    maxConfidences=confidences[i];
                    maxPos=i;
                }
            }*/


            tvResult.setText(MyClass.extractClass(confidences));
            //tvResult.setText(String.valueOf(confidences[0]));
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MainActivity", "IOException: " + e.getMessage());
            // Xử lý ngoại lệ ở đây, ví dụ: hiển thị thông báo cho người dùng.
        }
    }


    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        int inputSize = 1; // Update this value with your model's input size
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * inputSize * inputSize * 3); // 4 bytes per float, 3 channels
        byteBuffer.order(ByteOrder.nativeOrder());
        for (int i = 0; i < inputSize; ++i) {
            for (int j = 0; j < inputSize; ++j) {
                int pixel = bitmap.getPixel(i,j);
                byteBuffer.putFloat(((pixel >> 16) & 0xFF) / 255.0f);
                byteBuffer.putFloat(((pixel >> 8) & 0xFF) / 255.0f);
                byteBuffer.putFloat((pixel & 0xFF) / 255.0f);
            }
        }
        return byteBuffer;
    }


    private Bitmap UriToBitMap(Uri videoUri) throws IOException {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(this, videoUri);
            Bitmap videoFrame = retriever.getFrameAtTime(); // Lấy một khung hình từ video
            return videoFrame;
        } finally {
            retriever.release(); // Giải phóng bộ nhớ
        }
    }

}
