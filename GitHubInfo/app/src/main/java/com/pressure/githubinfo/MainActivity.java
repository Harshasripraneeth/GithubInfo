package com.pressure.githubinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pressure.githubinfo.UI.UserActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etuserName;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuserName = findViewById(R.id.etuserName);
        btnlogin = findViewById(R.id.button);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etuserName.getText().toString();
                if(s.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "enter text", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    intent.putExtra("userName",s);
                    startActivity(intent);
                }
            }
        });
    }
}
