package com.example.examen2ejercicio3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen2ejercicio3.Firebase.FirebaseHelper;
import com.example.examen2ejercicio3.GestionFarmacias.AdaptadorFarmacia;
import com.example.examen2ejercicio3.GestionFarmacias.Farmacia;
import com.example.examen2ejercicio3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

//Clase FragmentoLista que extiende Fragment y se encarga de mostrar la lista de farmacias cargadas desde Firebase
public class FragmentoLista extends Fragment {

    //Variables
    private RecyclerView recyclerView;
    private AdaptadorFarmacia adaptadorFarmacia;

    //Metodo onCreateView para crear la vista del fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflamos la vista del fragmento
        View view = inflater.inflate(R.layout.fragmento_lista, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_farmacias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Cargamos las farmacias desde Firebase y las mostramos en el RecyclerView
        new FirebaseHelper().cargarFarmacias(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Farmacia> farmacias = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Farmacia farmacia = keyNode.getValue(Farmacia.class);
                    farmacias.add(farmacia);
                }
                adaptadorFarmacia = new AdaptadorFarmacia(farmacias, new AdaptadorFarmacia.OnItemClickListener() {
                    @Override
                    public void onItemClick(Farmacia farmacia) {
                        FragmentoMapa fragmentoMapa = FragmentoMapa.newInstance(farmacia.getLatitude(), farmacia.getLongitude(), farmacia.getName());
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, fragmentoMapa)
                                .addToBackStack(null)
                                .commit();
                    }
                });
                recyclerView.setAdapter(adaptadorFarmacia);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return view;
    }
}