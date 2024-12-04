package com.example.examen2ejercicio3.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.examen2ejercicio3.Fragments.FragmentoLista;
import com.example.examen2ejercicio3.R;

public class ActividadPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        //Establecemos el fragmento de la lista de farmacias como el principal
        if (savedInstanceState == null) {
            loadFragment(new FragmentoLista());
        }
    }

    //Metodo para cargar fragmentos en el contenedor de fragmentos
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}