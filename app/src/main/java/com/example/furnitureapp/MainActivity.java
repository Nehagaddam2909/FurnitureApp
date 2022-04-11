package com.example.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
ImageView i1,i2,i3,i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i1=findViewById(R.id.imageView1);//table
        i2=findViewById(R.id.imageView2);//sofa
        i3=findViewById(R.id.imageView3);//cupboard
        i4=findViewById(R.id.imageView4);//chair
      i1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(MainActivity.this,Table.class);
              startActivity(i);

          }
      });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Sofa.class);
                startActivity(i);

            }
        });i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Cupboard.class);
                startActivity(i);

            }
        });i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Chair.class);
                startActivity(i);

            }
        });

    }


}
