package com.example.examen2ejercicio3.GestionFarmacias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen2ejercicio3.R;
import java.util.List;

public class AdaptadorFarmacia extends RecyclerView.Adapter<AdaptadorFarmacia.FarmaciaViewHolder> {
    private List<Farmacia> farmacias;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Farmacia farmacia);
    }

    public AdaptadorFarmacia(List<Farmacia> farmacias, OnItemClickListener listener) {
        this.farmacias = farmacias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FarmaciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_farmacia, parent, false);
        return new FarmaciaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmaciaViewHolder holder, int position) {
        Farmacia farmacia = farmacias.get(position);
        holder.nombreFarmacia.setText(farmacia.getName());
        holder.telefonoFarmacia.setText(farmacia.getPhone());
        holder.logoFarmacia.setImageResource(R.drawable.logo_farmacia); // Ensure you have a default image
        holder.itemView.setOnClickListener(v -> listener.onItemClick(farmacia));
    }

    @Override
    public int getItemCount() {
        return farmacias != null ? farmacias.size() : 0;
    }

    public static class FarmaciaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreFarmacia, telefonoFarmacia;
        ImageView logoFarmacia;

        public FarmaciaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreFarmacia = itemView.findViewById(R.id.nombre_farmacia);
            telefonoFarmacia = itemView.findViewById(R.id.telefono_farmacia);
            logoFarmacia = itemView.findViewById(R.id.logo_farmacia);
        }
    }
}