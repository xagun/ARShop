package com.sagun.arshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;


public class HomeActivity extends AppCompatActivity {
LinearLayout arlook, viewpro, odesign, exit;
ImageView help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    arlook = findViewById(R.id.arlook);
    viewpro = findViewById(R.id.viewpro);
    odesign = findViewById(R.id.odesign);
    exit= findViewById(R.id.exit);
    help = findViewById(R.id.help);

    help.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
            startActivity(intent);
        }
    });

    arlook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });

    viewpro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
            startActivity(intent);
        }
    });

    odesign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HomeActivity.this, DesignsActivity.class);
            startActivity(intent);
        }
    });

    exit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });



    }




}
