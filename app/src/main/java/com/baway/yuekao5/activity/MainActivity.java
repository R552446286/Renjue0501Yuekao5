package com.baway.yuekao5.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baway.yuekao5.R;
import com.baway.yuekao5.view.MyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView myView = (MyView) findViewById(R.id.myView);
        myView.setOnCircleClickListener(new MyView.OnCircleClickListener() {
            @Override
            public void onCircleClick(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                if (text.equals("在圆环内")){
                    Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
