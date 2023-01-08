package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Characters;
import com.grupo3.androiddsa.domain.MyObjects;

import java.util.List;

public class AdapterEscogerPersonaje extends RecyclerView.Adapter<AdapterEscogerPersonaje.ViewHolder>{

    private List<Characters> listCharacters;

    final AdapterEscogerPersonaje.OnItemClickListener listener;



    public interface OnItemClickListener{
        void onItemClick(Characters character);
    }


    public AdapterEscogerPersonaje(List<Characters> listCharacters, AdapterEscogerPersonaje.OnItemClickListener listener) {
        this.listCharacters = listCharacters;
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

        void bindData(final Characters character){
            name.setText(character.getCharacterName());
            description.setText(character.getCharacterDescription());
            coins.setText("Coins: "+character.getCharacterCoins());
            type.setText("");
            clica.setText("Clica para escoger");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(character);
                }
            });
        }

    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public AdapterEscogerPersonaje.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new AdapterEscogerPersonaje.ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull AdapterEscogerPersonaje.ViewHolder holder, int position) {
        holder.bindData(listCharacters.get(position));
    }

    @Override
    public int getItemCount() {
        return listCharacters.size();
    }
}
