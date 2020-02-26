package com.pressure.githubinfo.methods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pressure.githubinfo.R;
import com.pressure.githubinfo.UI.RepositoryActivity;
import com.pressure.githubinfo.client.GitReposEndPoint;
import com.pressure.githubinfo.apiservice.APIService;
import com.pressure.githubinfo.databinding.ActivityRepositoryBinding;
import com.pressure.githubinfo.databinding.AdapterLayoutBinding;
import com.pressure.githubinfo.model.GitRepo;
import com.pressure.githubinfo.model.GitUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {
    List<GitRepo> list = new ArrayList<GitRepo>();
    List<GitRepo> repolist;
    Context context;
    String user;

    public Adapter(Context context,String user)
    {
        this.context = context;
        this.user = user;
    }
    class viewholder extends RecyclerView.ViewHolder
    {
        AdapterLayoutBinding adapterLayoutBinding;
        public viewholder(@NonNull AdapterLayoutBinding itemView) {
            super(itemView.getRoot());
            adapterLayoutBinding = itemView;
        }
    }
    @NonNull
    @Override
    public Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterLayoutBinding adapterLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.adapter_layout,parent,false);
        return new viewholder(adapterLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewholder holder, int position) {
        GitRepo repo = list.get(position);
        holder.adapterLayoutBinding.setRepo(repo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void loadData()
    {
        GitReposEndPoint apiservice = APIService.getClient().create(GitReposEndPoint.class);
        Call<List<GitRepo>> repos = apiservice.getRepos(user);
        repos.enqueue(new Callback<List<GitRepo>>() {
            @Override
            public void onResponse(Call<List<GitRepo>> call, Response<List<GitRepo>> response) {
                list.addAll(response.body());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitRepo>> call, Throwable t) {

            }
        });
    }


}
