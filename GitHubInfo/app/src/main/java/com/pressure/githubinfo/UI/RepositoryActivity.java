package com.pressure.githubinfo.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pressure.githubinfo.R;
import com.pressure.githubinfo.methods.Adapter;

public class RepositoryActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        textView = findViewById(R.id.textView);
        String user =getIntent().getStringExtra("userName");
        textView.setText("The Login name is "+user);
         recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RepositoryActivity.this));
        Adapter adapter = new Adapter(RepositoryActivity.this,user);
        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
        adapter.loadData();
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
}
