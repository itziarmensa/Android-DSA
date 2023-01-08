package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.MyObjects;

import java.util.List;

public class AdapterEscogerObjeto extends RecyclerView.Adapter<AdapterEscogerObjeto.ViewHolder>{

    private List<MyObjects> listObjects;

    final AdapterEscogerObjeto.OnItemClickListener listener;



    public interface OnItemClickListener{
        void onItemClick(MyObjects object);
    }


    public AdapterEscogerObjeto(List<MyObjects> listObjects, AdapterEscogerObjeto.OnItemClickListener listener) {
        this.listObjects = listObjects;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private TextView coins;
        private TextView type;
        private TextView clica;
        //Button btnComprarObjeto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            coins = itemView.findViewById(R.id.coins);
            type = itemView.findViewById(R.id.typeId);
            clica = itemView.findViewById(R.id.clica);
            /*btnComprarObjeto = itemView.findViewById(R.id.btnComprarObjeto);
            btnComprarObjeto.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Has comprado", Toast.LENGTH_LONG).show();
                }
            });*/
        }

        void bindData(final MyObjects object){
            name.setText(object.getObjectName());
            description.setText(object.getObjectDescription());
            coins.setText("Coins: "+object.getObjectCoins());
            type.setText("Object type: "+object.getObjectTypeId());
            clica.setText("Clica para escoger");
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
    public AdapterEscogerObjeto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new AdapterEscogerObjeto.ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull AdapterEscogerObjeto.ViewHolder holder, int position) {
        holder.bindData(listObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return listObjects.size();
    }
}
