package com.example.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView tv;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        imageView = findViewById(R.id.image2);
        tv = findViewById(R.id.data);
        exit = findViewById(R.id.exit2);

        Intent get = getIntent();
        boolean maleFemale;
        String name, address;
        int age;
        Bitmap image;

        name = get.getStringExtra("NAME");
        address = get.getStringExtra("ADDRESS");
        maleFemale = get.getBooleanExtra("GENDER", true);
        age = get.getIntExtra("AGE", 0);
        image = get.getExtras().getParcelable("IMAGE");

        if (imageView != null) {
            if (image != null) {
                imageView.setImageBitmap(image);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }

        String text = "";
        text += name + '\n';
        text += address + '\n';
        if (maleFemale) {
            text += "male" + '\n';
        } else {
            text += "female" + '\n';
        }
        text += age + '\n';
        tv.setText(text);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}