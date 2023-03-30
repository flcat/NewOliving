package com.solgae.newoliving.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownLoadFile extends AsyncTask<URL,Integer, List<Bitmap>> {

    private ProgressDialog mProgressDialog;
    private CoordinatorLayout mCLayout;
    Context context;
    private List<String> images;
    int test = 0;
    public DownLoadFile(Context context, List<String> images) {

        this.context = context;
        this.images = images;

        mProgressDialog = new ProgressDialog(this.context);
        mProgressDialog.setIndeterminate(false);
        // Progress dialog horizontal style
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // Progress dialog title
        mProgressDialog.setTitle("AsyncTask");
        // Progress dialog message
        mProgressDialog.setMessage("Please wait, we are downloading your image files...");
        mProgressDialog.setCancelable(true);

        // Set a progress dialog dismiss listener
        mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                // Cancel the AsyncTask
                BusProvider.getInstance().post(new PushEvent(InfintiApplication.UPDATE_END, ""));
            }
        });
    }



// Before the tasks execution
    protected void onPreExecute(){
// Display the progress dialog on async task start
        mProgressDialog.show();
        mProgressDialog.setProgress(0);
    }

    // Do the task in background/non UI thread
    protected List<Bitmap> doInBackground(URL...urls){
        int count = urls.length;
        HttpURLConnection connection = null;
        List<Bitmap> bitmaps = new ArrayList<>();



// Loop through the urls
        for(int i=0;i<count;i++){
            URL currentURL = urls[i];


// So download the image from this url
            try{
// Initialize a new http url connection
                connection = (HttpURLConnection) currentURL.openConnection();

// Connect the http url connection
                connection.connect();

// Get the input stream from http url connection
                InputStream inputStream = connection.getInputStream();

// Initialize a new BufferedInputStream from InputStream
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Log.d("why","here1");

// Convert BufferedInputStream to Bitmap object
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                Log.d("why","here2");

// Add the bitmap to list
                bitmaps.add(bmp);
                Log.d("why","here3");


// Publish the async task progress
// Added 1, because index start from 0
                publishProgress((int) (((i+1) / (float) count) * 100));
                if(isCancelled()){
                    break;
                }

            }catch(IOException e){
                e.printStackTrace();
            }catch(NullPointerException e){
                e.printStackTrace();
            }finally {
            // Disconnect the http url connection
                // - 김세진

                Log.d("catch",test + "");
                test++;
                connection.disconnect();
            }
        }
        // Return bitmap list
        return bitmaps;
    }

    // On progress update
    protected void onProgressUpdate(Integer... progress){
        // Update the progress bar
        mProgressDialog.setProgress(progress[0]);
        Log.d("catch","cat");

    }

    // On AsyncTask cancelled
    protected void onCancelled(){
        Snackbar.make(mCLayout,"Task Cancelled.", Snackbar.LENGTH_LONG).show();
    }

    // When all async task done
    protected void onPostExecute(List<Bitmap> result){
// Hide the progress dialog

        mProgressDialog.dismiss();
        Log.d("catch","mouse");

// Loop through the bitmap list
        for(int i=0;i<result.size();i++){
            if (null != result.get(i)) {
                Bitmap bitmap = result.get(i);
                // Save the bitmap to internal storage
                saveImageToInternalStorage(bitmap,i);
            }
        }
    }


// Custom method to save a bitmap into internal storage
    protected Uri saveImageToInternalStorage(Bitmap bitmap, int index){
        Uri savedImageURI = null;
// Initialize ContextWrapper
        ContextWrapper wrapper = new ContextWrapper(context);

        File file = new File(Environment.getExternalStorageDirectory()+"/xperon");

        if (!file.isDirectory()) {
            file.mkdir();
        }

        if (!images.get(index).equalsIgnoreCase("")) {
            // Create a file to save the image
            file = new File(file, images.get(index));

            try{
                // Initialize a new OutputStream
                OutputStream stream = null;

                // If the output file exists, it can be replaced or appended to it
                stream = new FileOutputStream(file);

                // Compress the bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

                // Flushes the stream
                stream.flush();

                // Closes the stream
                stream.close();

            }catch (IOException e) // Catch the exception
            {
                e.printStackTrace();
            }

            // Parse the gallery image url to uri
            savedImageURI = Uri.parse(file.getAbsolutePath());
        }


        // Return the saved image Uri
        return savedImageURI;
    }



}
