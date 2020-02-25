package com.pressure.githubinfo.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pressure.githubinfo.methods.ImageDownloader;
import com.pressure.githubinfo.R;
import com.pressure.githubinfo.client.GitUserEndPoint;
import com.pressure.githubinfo.apiservice.APIService;
import com.pressure.githubinfo.model.GitUser;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity  {
    ImageView ivprofile;
    TextView tvlogin;
    TextView tvname;
    TextView tvemail;
    TextView tvfollowers;
    TextView tvfollowing;
    TextView tvcreated;
    TextView tvupdated;
    ProgressBar progressBar;
    Button btrepositories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ivprofile = findViewById(R.id.imageView);
        tvlogin = findViewById(R.id.tvlogin);
        tvname = findViewById(R.id.tvuserName);
        tvemail = findViewById(R.id.tvemail);
        tvfollowers = findViewById(R.id.tvfollower);
        tvfollowing = findViewById(R.id.tvfollowing);
        tvcreated = findViewById(R.id.tvCreatedAt);
        tvupdated = findViewById(R.id.tvUpdatedAt);
        progressBar = findViewById(R.id.progressBar);
        btrepositories = findViewById(R.id.btnrepositories);
        btrepositories.setVisibility(View.GONE);
        btrepositories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,RepositoryActivity.class);
                intent.putExtra("userName",getIntent().getStringExtra("userName"));
                startActivity(intent);
            }
        });
        loaddata();
    }
    void loaddata()
    {

        GitUserEndPoint Apiservice = APIService.getClient().create(GitUserEndPoint.class);
        Call<GitUser> call = Apiservice.getUser(getIntent().getStringExtra("userName"));
        call.enqueue(new Callback<GitUser>() {
            @Override
            public void onResponse(Call<GitUser> call, Response<GitUser> response) {

                progressBar.setVisibility(View.VISIBLE);

                ImageDownloader imageDownloader = new ImageDownloader();
                try {
                    Bitmap image = imageDownloader.execute(response.body().getProfileavatar()).get();
                    if(image != null)
                    ivprofile.setImageBitmap(image);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(response.body().getName() == null)
                    tvname.setText("Name : Not entered");
                else
                    tvname.setText("Name : "+response.body().getName());

                tvlogin.setText("Login : "+response.body().getLogin());
                tvcreated.setText("Created At : "+response.body().getCreated().substring(0,10));
                tvupdated.setText("Updated At : "+response.body().getUpdated().substring(0,10));

                if(response.body().getEmail() == null)
                    tvemail.setText("Email : "+"Not entered");
                else
                    tvemail.setText("Email : "+response.body().getEmail());

                if(response.body().getFollower() == null)
                    tvfollowers.setText("Followers : "+"Not entered");
                else
                    tvfollowers.setText("Followers : "+response.body().getFollower());

                if(response.body().getFollowing() == null)
                    tvfollowing.setText("Following : "+"Not entered");
                else
                    tvfollowing.setText("Following : "+response.body().getFollowing());
                progressBar.setVisibility(View.GONE);
                btrepositories.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call<GitUser> call, Throwable t) {
                Toast.makeText(UserActivity.this, "invalid user", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
