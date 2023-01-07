package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.User;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {
    private List<User> listUsers;
    final AdapterUser.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(User user);
    }

    public AdapterUser(List<User> listUsers,AdapterUser.OnItemClickListener listener){
        this.listUsers=listUsers;
        this.listener=listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }
        void binData(final User user){
            name.setText(user.getUserName());
            description.setText("Clica para ver más detalles");
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClick(user);
                }
            });
        }
    }
    @NonNull
    @Override
    public AdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterUser.ViewHolder holder, int position) {
        holder.binData(listUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }
}

