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

//Clase AdaptadorFarmacia que extiende RecyclerView.Adapter y se encarga de adaptar las farmacias a la vista
public class AdaptadorFarmacia extends RecyclerView.Adapter<AdaptadorFarmacia.FarmaciaViewHolder> {

    //Variables
    private List<Farmacia> farmacias;
    private OnItemClickListener listener;

    //Interfaz OnItemClickListener para gestionar los eventos de click en las farmacias
    public interface OnItemClickListener {
        void onItemClick(Farmacia farmacia);
    }

    //Constructor
    public AdaptadorFarmacia(List<Farmacia> farmacias, OnItemClickListener listener) {
        this.farmacias = farmacias;
        this.listener = listener;
    }

    //Metodo onCreateViewHolder para crear un nuevo ViewHolder
    @NonNull
    @Override
    public FarmaciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_farmacia, parent, false);
        return new FarmaciaViewHolder(view);
    }

    //Metodo onBindViewHolder para enlazar los datos de la lista con los elementos de la vista
    @Override
    public void onBindViewHolder(@NonNull FarmaciaViewHolder holder, int position) {
        Farmacia farmacia = farmacias.get(position);
        holder.nombreFarmacia.setText(farmacia.getName());
        holder.telefonoFarmacia.setText(farmacia.getPhone());
        holder.logoFarmacia.setImageResource(R.drawable.logo_farmacia); // Ensure you have a default image
        holder.itemView.setOnClickListener(v -> listener.onItemClick(farmacia));
    }

    //Metodo getItemCount para obtener el tamaño de la lista de farmacias
    @Override
    public int getItemCount() {
        return farmacias != null ? farmacias.size() : 0;
    }

    //Clase interna FarmaciaViewHolder que extiende RecyclerView.ViewHolder y se encarga de gestionar los elementos de la vista
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