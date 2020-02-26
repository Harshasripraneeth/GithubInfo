package com.pressure.githubinfo.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pressure.githubinfo.R;
import com.pressure.githubinfo.databinding.ActivityRepositoryBinding;
import com.pressure.githubinfo.methods.Adapter;

public class RepositoryActivity extends AppCompatActivity {

    ActivityRepositoryBinding repositoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repositoryBinding = DataBindingUtil.setContentView(this,R.layout.activity_repository);
        repositoryBinding.setUser("The Login name is "+getIntent().getStringExtra("userName"));
        repositoryBinding.recyclerView.setHasFixedSize(true);
        repositoryBinding.recyclerView.setLayoutManager(new LinearLayoutManager(RepositoryActivity.this));
        Adapter adapter = new Adapter(RepositoryActivity.this,getIntent().getStringExtra("userName"));
        repositoryBinding.setInprogress(true);
        adapter.loadData();
        repositoryBinding.recyclerView.setAdapter(adapter);
        repositoryBinding.setInprogress(false);
    }
}
