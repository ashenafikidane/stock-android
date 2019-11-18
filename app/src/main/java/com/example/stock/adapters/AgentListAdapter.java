package com.example.stock.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.models.User;
import com.example.stock.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgentListAdapter extends RecyclerView.Adapter<AgentListAdapter.ViewHolder> {

    private List<User> agents;
    private Context mContext;

    public AgentListAdapter( List<User> agents, Context context) {
        this.agents = agents;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agent_list_row, parent, false);

        return new ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public CircleImageView logo;

        public ViewHolder(@NonNull View view) {
            super(view);
            userName = view.findViewById(R.id.account_name);
            logo = view.findViewById(R.id.logo);

        }

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User item = this.agents.get(position);
        holder.userName.setText(item.getUserName());
//        if (item.logoFileId != null){
//            //Todo set logo
//        }


    }

    @Override
    public int getItemCount() {
        return this.agents != null ? this.agents.size() : 0;
    }

}
