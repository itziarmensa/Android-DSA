package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Characters;

import java.util.List;

public class AdapterCharacters extends RecyclerView.Adapter<AdapterCharacters.ViewHolder> {

    private List<Characters> listCharacters;

    final AdapterCharacters.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Characters character);
    }


    public AdapterCharacters(List<Characters> listCharacters, AdapterCharacters.OnItemClickListener listener) {
        this.listCharacters = listCharacters;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameCharacter;
        private TextView descriptionCharacter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //nameCharacter = itemView.findViewById(R.id.nameCharacter);
            //descriptionCharacter = itemView.findViewById(R.id.descriptionCharacter);
        }

        void binData(final Characters character){
            nameCharacter.setText(character.getNameCharacter());
            descriptionCharacter.setText("Clica para ver más detalles");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(character);
                }
            });
        }

    }

    @NonNull
    @Override
    public AdapterCharacters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binData(listCharacters.get(position));

    }

    @Override
    public int getItemCount() {
        return listCharacters.size();
    }


}
