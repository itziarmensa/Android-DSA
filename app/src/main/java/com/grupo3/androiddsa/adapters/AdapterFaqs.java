package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Faqs;

import java.util.List;

public class AdapterFaqs extends RecyclerView.Adapter<AdapterFaqs.ViewHolder> {

    private List<Faqs> listFaqs;

    public AdapterFaqs(List<Faqs> listFaqs) {
        this.listFaqs = listFaqs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private TextView answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.name);
            answer = itemView.findViewById(R.id.description);
        }

        void bindData(final Faqs faqs){
            question.setText(faqs.getQuestion());
            answer.setText(faqs.getAnswer());
        }

    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public AdapterFaqs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new AdapterFaqs.ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull AdapterFaqs.ViewHolder holder, int position) {
        holder.bindData(listFaqs.get(position));
    }

    @Override
    public int getItemCount() {
        return listFaqs.size();
    }
}