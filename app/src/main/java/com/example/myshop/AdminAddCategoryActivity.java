package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminAddCategoryActivity extends AppCompatActivity {
    private ImageView coffee, food, menshirt, womanshirt, sportshose,womanshose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);

        coffee = (ImageView) findViewById(R.id.p_coffee);
        food = (ImageView) findViewById(R.id.p_food);
        menshirt = (ImageView) findViewById(R.id.p_men);
        womanshirt = (ImageView) findViewById(R.id.p_woman_clother);
        sportshose = (ImageView) findViewById(R.id.p_sport_shose);
        womanshose = (ImageView) findViewById(R.id.p_woman_shose);


        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("Category","coffee");
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("Category","food");
                startActivity(intent);
            }
        });
        menshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("Category","menshirts");
                startActivity(intent);
            }
        });
        womanshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("Category","womanshirt");
                startActivity(intent);
            }
        });
        sportshose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("Category","sportshose");
                startActivity(intent);
            }
        });
        womanshose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("Category","womanshose");
                startActivity(intent);
            }
        });
    }
}