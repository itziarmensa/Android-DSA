package com.grupo3.androiddsa.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.MainObjectDetails;
import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.domain.to.ObjectRecycler;

import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    private List<MyObjects> listObjects;

    final AdapterDatos.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(MyObjects object);
    }


    public AdapterDatos(List<MyObjects> listObjects, AdapterDatos.OnItemClickListener listener) {
        this.listObjects = listObjects;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }

        void binData(final MyObjects object){
            name.setText(object.getName());
            description.setText("Clica para ver más detalles");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(object);
                }
            });
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
        holder.binData(listObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return listObjects.size();
    }


}
