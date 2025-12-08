package com.example.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn, exit;
    private EditText etName, etAddress;
    private RadioButton male, female;
    private boolean maleFemale;
    private String name, address;
    public static Bitmap image;
    private int age;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                image = (Bitmap) data.getExtras().get("data");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.photo);
        etName = findViewById(R.id.name);
        etAddress = findViewById(R.id.address);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        exit = findViewById(R.id.exit);

        Spinner spinner = findViewById(R.id.spinner);

        // Create a list of ages 0–20
        ArrayList<Object> ages = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            ages.add(String.valueOf(i));
        }

// Create adapter
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ages
        );

// Set dropdown layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Attach adapter to spinner
        spinner.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleFemale = true;
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleFemale = false;
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                address = etAddress.getText().toString();
                String selectedAge = spinner.getSelectedItem().toString();
                age = Integer.parseInt(selectedAge);
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("ADDRESS", address);
                intent.putExtra("GENDER", maleFemale);
                intent.putExtra("AGE", age);

                startActivity(intent);
                finish();
            }
        });
    }
}