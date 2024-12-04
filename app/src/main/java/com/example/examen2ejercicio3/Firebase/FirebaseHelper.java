package com.example.examen2ejercicio3.Firebase;

import android.os.AsyncTask;
import com.example.examen2ejercicio3.GestionFarmacias.Farmacia;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

//Clase FirebaseHelper que se encarga de cargar las farmacias de Firebase en segundo plano
public class FirebaseHelper {

    //Variables
    private final FirebaseDatabase database;
    private final List<Farmacia> farmacias;

    //Constructor
    public FirebaseHelper() {
        database = FirebaseDatabase.getInstance();
        this.farmacias = new ArrayList<>();
    }

    //Metodo para cargar las farmacias
    public void cargarFarmacias(ValueEventListener listener) {
        new CargarFarmaciasTask(listener).execute();
    }

    //Clase interna CargarFarmaciasTask que extiende AsyncTask y carga las farmacias de Firebase en segundo plano
    private class CargarFarmaciasTask extends AsyncTask<Void, Void, Void> {

        //Variables
        private ValueEventListener listener;

        //Constructor
        public CargarFarmaciasTask(ValueEventListener listener) {
            this.listener = listener;
        }

        //Metodo doInBackground para cargar las farmacias de Firebase
        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseReference databaseReference = database.getReference("farmacias");
            databaseReference.addListenerForSingleValueEvent(listener);
            return null;
        }
    }
}