package com.pressure.githubinfo.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pressure.githubinfo.databinding.ActivityUserBinding;
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


    ActivityUserBinding activityUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserBinding = DataBindingUtil.setContentView(this,R.layout.activity_user);
        activityUserBinding.btnrepositories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,RepositoryActivity.class);
                intent.putExtra("userName",getIntent().getStringExtra("userName"));
                startActivity(intent);
            }
        });
        activityUserBinding.setInprogress(true);
        loaddata();
    }
    void loaddata()
    {

        GitUserEndPoint Apiservice = APIService.getClient().create(GitUserEndPoint.class);
        Call<GitUser> call = Apiservice.getUser(getIntent().getStringExtra("userName"));
        call.enqueue(new Callback<GitUser>() {
            @Override
            public void onResponse(Call<GitUser> call, Response<GitUser> response) {

                ImageDownloader imageDownloader = new ImageDownloader();
                try {
                    Bitmap image = imageDownloader.execute(response.body().getProfileavatar()).get();
                    GitUser user = new GitUser("Login :"+response.body().getLogin(),
                            "Name :"+response.body().getName(),
                            "Followers :"+response.body().getFollower(),
                            "Following :"+response.body().getFollowing(),
                            "Email :"+response.body().getEmail(),
                            "CreatedAt: "+response.body().getCreated().substring(0,10),
                            "UpdatedAt :"+response.body().getUpdated().substring(0,10),
                             response.body().getProfileavatar());
                    activityUserBinding.imageView.setImageBitmap(image);
                    activityUserBinding.setUser(user);
                    activityUserBinding.setInprogress(false);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<GitUser> call, Throwable t) {
                Toast.makeText(UserActivity.this, "invalid user", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

