package com.itziar.androiddsa.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itziar.androiddsa.MainObjectDetails;
import com.itziar.androiddsa.R;
import com.itziar.androiddsa.domain.MyObjects;
import com.itziar.androiddsa.domain.to.ObjectRecycler;

import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    private List<MyObjects> listObjects;


    public AdapterDatos(List<MyObjects> listObjects) {
        this.listObjects = listObjects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }

    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listObjects.get(position).getName());
        holder.description.setText(listObjects.get(position).getDescriptionObject());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), MainObjectDetails.class);
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listObjects.size();
    }


}
