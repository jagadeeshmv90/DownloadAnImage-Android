package com.j7ech.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView myImageView;

    public void getImage(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show(); // Testing

        ImageDownloader doIt = new ImageDownloader();
        Bitmap myImage;
        try {
            myImage = doIt.execute("https://cdn.dribbble.com/users/729823/screenshots/4756127/a_1x.png").get();
            myImageView.setImageBitmap(myImage);

        } catch (Exception error) {

        }

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]); //Creating URL from Argument
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();    //Creating and opening a connection to the url
                connection.connect();      //Starting connection
                InputStream in = connection.getInputStream();   //Defining the input stream
                Bitmap myImage = BitmapFactory.decodeStream(in);
                return myImage;
            } catch (Exception error) {
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImageView = findViewById(R.id.myImageView);

    }
}