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

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.ViewHolder>{

    private List<User> listUser;

    public AdapterRanking(List<User> listUser) {
        this.listUser = listUser;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameSurname;
        private TextView email;
        private TextView points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSurname = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.description);
            points = itemView.findViewById(R.id.coins);
        }

        void bindData(final User user){
            nameSurname.setText(user.getUserName() + " " + user.getUserSurname());
            email.setText(user.getEmail());
            points.setText("Points: " + user.getPoints());
        }
    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public AdapterRanking.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new AdapterRanking.ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull AdapterRanking.ViewHolder holder, int position) {
        holder.bindData(listUser.get(position));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }
}

