package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Partida;

import java.util.List;

public class AdapterPartida extends RecyclerView.Adapter<AdapterPartida.ViewHolder>{

    private List<Partida> listPartida;

    final AdapterPartida.OnItemClickListener listener;



    public interface OnItemClickListener{
        void onItemClick(Partida partida);
    }


    public AdapterPartida(List<Partida> listPartida, AdapterPartida.OnItemClickListener listener) {
        this.listPartida = listPartida;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView partidaId;
        private TextView objectId;
        private TextView characterId;
        private TextView mapa;
        private TextView level;
        private TextView points;
        private TextView finished;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            partidaId = itemView.findViewById(R.id.name);
            objectId = itemView.findViewById(R.id.description);
            characterId = itemView.findViewById(R.id.coins);
            mapa = itemView.findViewById(R.id.typeId);
            level = itemView.findViewById(R.id.clica);
            points = itemView.findViewById(R.id.points);
            finished = itemView.findViewById(R.id.finished);
        }

        void bindData(final Partida partida){
            partidaId.setText(partida.getPartidaId());
            objectId.setText("Object: " + partida.getObjectId());
            characterId.setText("Character: " + partida.getCharacterId());
            mapa.setText("Map: " + partida.getMapa());
            level.setText("Level: " + partida.getLevel());
            points.setText("Points: " + partida.getPoints());
            if (partida.getFinished())
                finished.setText("This game has been completed");
            else
                finished.setText("Press to continue the game");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(partida);
                }
            });
        }

    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public AdapterPartida.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new AdapterPartida.ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull AdapterPartida.ViewHolder holder, int position) {
        holder.bindData(listPartida.get(position));
    }

    @Override
    public int getItemCount() {
        return listPartida.size();
    }
}
