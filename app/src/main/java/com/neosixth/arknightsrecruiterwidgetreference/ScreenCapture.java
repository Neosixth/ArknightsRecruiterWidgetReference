package com.neosixth.arknightsrecruiterwidgetreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Date;

//originally extends Activity
public class ScreenCapture extends Activity implements ImageReader.OnImageAvailableListener{
    private static final String SCREENCAP_NAME = "screencap";
    private static final int VIRTUAL_DISPLAY_FLAGS = DisplayManager.VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY | DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC;
    MediaProjectionManager mediaProjectionManager;
    MediaProjection mediaProjection;

    private ImageReader mImageReader;
    //NOTE. MVIRTUALDISPLAY IS REQUIRED TO WORK
    private VirtualDisplay mVirtualDisplay;
    private Handler mHandler;
    private Display mDisplay;
    private int mDensity;
    private int mWidth;
    private int mHeight;

    private static final String CHANNEL_ID = "channel_01";

    private TextView ocrOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.startForegroundService();
        setContentView(R.layout.screencapturelayout);

        Toast.makeText(this, "Processing Image", Toast.LENGTH_LONG).show();
        /*
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            CharSequence name = getString(R.string.app_name);
            // Create the channel for the notification
            @SuppressLint("WrongConstant")
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_LOW);
            // Set the Notification Channel for the Notification Manager.
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
            }


            startForegroundService(new Intent(ScreenCapture.this, FloatingViewService.class));
            //We only need to call this for SDK 26+, since startForeground always has to be called after startForegroundService.
            //startForeground(NOTIFICATION_ID, getNotification());
        }


         */
        //NotificationManager.createNotificationChannel(mChannel);



        //ocrOutput = (TextView) findViewById(R.id.ocrOutputXML);
        mediaProjectionManager = (MediaProjectionManager)this.getSystemService(MEDIA_PROJECTION_SERVICE);
        //Toast.makeText(this, "Processing Image     -11", Toast.LENGTH_LONG).show();
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Toast.makeText(this, "Processing Image -22", Toast.LENGTH_LONG).show();
        // start capture handling thread
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler();
                Looper.loop();
            }
        }.start();
        //Toast.makeText(this, "Processing Image -2",Toast.LENGTH_LONG).show();
        startMediaProjection();


    }



    private void startMediaProjection(){
        //Toast.makeText(this, "Processing Image    -3",Toast.LENGTH_LONG).show();

        DisplayMetrics metrics = new DisplayMetrics();
        //activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics = getResources().getDisplayMetrics();
        //mScreenDensity = metrics.densityDpi;
        mDensity = metrics.densityDpi;
        mDisplay = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        mDisplay.getSize(size);

        mWidth = size.x;
        mHeight = size.y;
        //Toast.makeText(this, "Processing Image -4 ",Toast.LENGTH_LONG).show();
        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 1);
        //Toast.makeText(this, "Processing Image  2",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(this, "Processing Image      -5 ",Toast.LENGTH_LONG).show();


        if (requestCode == 1) {
            //Toast.makeText(this, "Processing Image  4",Toast.LENGTH_LONG).show();

            if (resultCode == Activity.RESULT_OK) {
                //mResultCode = resultCode;
                //mResultData = data;
                //Toast.makeText(this, "Processing Image 5",Toast.LENGTH_LONG).show();

                mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);
                //int mWidth = mSurfaceView.getWidth();
                //int mHeight = mSurfaceView.getHeight();
                //Toast.makeText(this, "Processing Image  6",Toast.LENGTH_LONG).show();

                int density = this.getResources().getDisplayMetrics().densityDpi;
                Display display;
                display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

                //Toast.makeText(this, "Processing Image 7",Toast.LENGTH_LONG).show();

                mImageReader = ImageReader.newInstance(mWidth, mHeight, PixelFormat.RGBA_8888, 2);

                //Toast.makeText(this, "Processing Image  8",Toast.LENGTH_LONG).show();

                mVirtualDisplay = mediaProjection.createVirtualDisplay(SCREENCAP_NAME, mWidth, mHeight, mDensity, VIRTUAL_DISPLAY_FLAGS, mImageReader.getSurface(), null, mHandler);
                mImageReader.setOnImageAvailableListener(new ScreenCapture(), mHandler);
                //Toast.makeText(this, "Processing Image 9",Toast.LENGTH_LONG).show();



                Image image = null;
                image = mImageReader.acquireLatestImage();
                //Toast.makeText(this, "Processing Image  10",Toast.LENGTH_LONG).show();

                if(image==null) {
                    Toast.makeText(this, "Please wait... image is processing... ",Toast.LENGTH_LONG).show();
                }


                while(image==null){
                    image = mImageReader.acquireLatestImage();
                }



                takeScreenshot(image);


            }


        }


    } // End of onActivityResult

    private void takeScreenshot(Image image) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {

            //Toast.makeText(this, "Processing Image 11",Toast.LENGTH_LONG).show();
            File filepath = Environment.getExternalStorageDirectory();
            //File filepath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            //Toast.makeText(this, "Processing Image  12",Toast.LENGTH_LONG).show();
            File dir = new File(filepath.getAbsoluteFile()+"/xxx/");
            //Toast.makeText(this, "Processing Image 13",Toast.LENGTH_LONG).show();
            //dir.mkdir();
            //Toast.makeText(this, "Processing Image  14",Toast.LENGTH_LONG).show();


            //Toast.makeText(getApplicationContext(), "cropped image saved to gallery", Toast.LENGTH_SHORT).show();
            ///Image image = null;
            //Toast.makeText(this, "Processing Image 15",Toast.LENGTH_LONG).show();
            Bitmap bitmap2;
            //Toast.makeText(this, "Processing Image  16",Toast.LENGTH_LONG).show();
            //mediaProjection.stop();
            ///image = mImageReader.acquireLatestImage();
            //image = mImageReader.acquireNextImage();
            //Toast.makeText(this, "Processing Image  17",Toast.LENGTH_LONG).show();

            /*
            if(image == null){
                Toast.makeText(this, "IMAGE IS NULL",
                        Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(this, "ZEON",
                        Toast.LENGTH_LONG).show();
            }

             */





            Image.Plane[] planes = image.getPlanes();
            //Toast.makeText(this, "Processing Image 18",Toast.LENGTH_LONG).show();

            ByteBuffer buffer = planes[0].getBuffer();

            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride();
            int rowPadding = rowStride - pixelStride * mWidth;

// create bitmap
            bitmap2 = Bitmap.createBitmap(mWidth+rowPadding/pixelStride, mHeight, Bitmap.Config.ARGB_8888);
            bitmap2.copyPixelsFromBuffer(buffer);
            if (image !=null)
                image.close();

            mImageReader.close();
            getTextFromImage(bitmap2);


        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            Log.e("Some Tag", e.getMessage(), e);
            System.out.println("404"+e.getMessage());
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Take screenshot failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void getTextFromImage(Bitmap bitmap){
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a12);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if(!textRecognizer.isOperational()){
            Toast.makeText(getApplicationContext(), "textRecognizer is not operational", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ScreenCapture.this, FloatingViewService.class);
            String strOutput = "textRecognizer is not operational";
            i.putExtra("ocrOutput", strOutput);
            startService(i);
            stopMediaProjection();
            finish();

        } else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < items.size(); i++){
                TextBlock myItem = items.valueAt(i);
                sb.append(myItem.getValue()+"4444");
                //sb.append(" ");
            }
            //ocrOutput.setText(sb.toString());
            Intent i = new Intent(ScreenCapture.this, FloatingViewService.class);
            String strOutput = sb.toString();
            i.putExtra("ocrOutput", strOutput);
            startService(i);
            stopMediaProjection();
            finish();
        }
    }

    @Override
    public void onImageAvailable(ImageReader reader) {


        //takeScreenshot();
    }

    private void stopMediaProjection() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mediaProjection != null) {
                    mediaProjection.stop();
                }
            }
        });
    }


}
