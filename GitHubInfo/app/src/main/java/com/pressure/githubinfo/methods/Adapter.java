package com.pressure.githubinfo.methods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pressure.githubinfo.R;
import com.pressure.githubinfo.client.GitReposEndPoint;
import com.pressure.githubinfo.apiservice.APIService;
import com.pressure.githubinfo.model.GitRepo;

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
        TextView tvname;
        TextView tvdescription;
        TextView tvlanguage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvreponame);
            tvdescription = itemView.findViewById(R.id.tvrepodescription);
            tvlanguage = itemView.findViewById(R.id.tvrepolanguage);
        }
    }
    @NonNull
    @Override
    public Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewholder holder, int position) {
        holder.tvname.setText(list.get(position).getName());
        if(list.get(position).getDescription() !=null)
            holder.tvdescription.setText(list.get(position).getDescription());
         else
            holder.tvdescription.setText("not entered");
        if(list.get(position).getLanguage() !=null)
            holder.tvlanguage.setText("Language :"+list.get(position).getLanguage());
        else
            holder.tvlanguage.setText("Language: not entered");

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
