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

public class FragmentoLista extends Fragment {
    private RecyclerView recyclerView;
    private AdaptadorFarmacia adaptadorFarmacia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_lista, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_clases);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
                        FragmentoMapa fragmentoMapa = FragmentoMapa.newInstance(farmacia.getLatitude(), farmacia.getLongitude());
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, fragmentoMapa)
                                .addToBackStack(null)
                                .commit();
                    }
                });
                recyclerView.setAdapter(adaptadorFarmacia);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        return view;
    }
}