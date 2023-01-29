package com.grupo3.androiddsa.adapters;

import android.icu.text.IDNA;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Information;
import com.grupo3.androiddsa.domain.User;

import java.util.List;

public class AdapterInformation extends RecyclerView.Adapter<AdapterInformation.ViewHolder>{

    private List<Information> listInformation;

    public AdapterInformation(List<Information> listInformation) {
        this.listInformation = listInformation;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView sender;
        private TextView message;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            sender = itemView.findViewById(R.id.description);
            message = itemView.findViewById(R.id.coins);
            date = itemView.findViewById(R.id.typeId);
        }

        void bindData(final Information information){
            title.setText(information.getTitle());
            sender.setText("By: " + information.getSender());
            message.setText(information.getMessage());
            date.setText("Date: " + information.getDate());
        }
    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public AdapterInformation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new AdapterInformation.ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull AdapterInformation.ViewHolder holder, int position) {
        holder.bindData(listInformation.get(position));
    }

    @Override
    public int getItemCount() {
        return listInformation.size();
    }
}

