package com.tecksolke.json2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   public static Button bjson;
    public static TextView tjson;
    public static ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bjson = findViewById(R.id.get_json);
        spinner = findViewById(R.id.progressBar);
        tjson = findViewById(R.id.textshowjson);

        tjson.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        spinner.getIndeterminateDrawable().setColorFilter(Color.parseColor("#303F9F"), android.graphics.PorterDuff.Mode.MULTIPLY);

        bjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessJson PJSON = new ProcessJson();
                PJSON.execute();
                Toast.makeText(MainActivity.this,
                        "Processing Json...", Toast.LENGTH_LONG).show();
            }
        });
    }


}
