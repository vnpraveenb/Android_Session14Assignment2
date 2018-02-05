package com.example.praveen.session14assignment2;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References the view object button from button view in the layout
        saveButton = (Button) findViewById(R.id.button);

        // Sets onClickListener To Store Image into External Storage
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check for build versions which are equal to or above Android Marshmallow
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    // Checks if is Permission Granted Or Not. If Not, Requests for Write External Storage Permission

                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this,new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }
                }

                // Convert Drawable item as BitMap
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_oreo_logo);

                // Read External Storage Folder
                File sd = Environment.getExternalStorageDirectory();
                String fileName = "ext.png";
                // Prepare to write Data to external Storage
                File dest = new File(sd, fileName);
                try {
                    // Read Data through fileStream and write in compress mode to external storage.
                    FileOutputStream out;
                    out = new FileOutputStream(dest);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();

                } catch (FileNotFoundException e) {
                      e.printStackTrace();
                } catch (IOException e) {
                     e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Successfully saved to external storage.", Toast.LENGTH_LONG).show();

            }

        });
    }
}
