package com.example.examen2ejercicio3.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import com.example.examen2ejercicio3.R;

//Clase FragmentoMapa que extiende Fragment y se encarga de mostrar el mapa con la ubicación de cada farmacia
public class FragmentoMapa extends Fragment implements MapListener {

    //Variables
    private static final String ARG_LATITUDE = "latitude";
    private static final String ARG_LONGITUDE = "longitude";
    private static final String ARG_NAME = "name";
    private double latitude;
    private double longitude;
    private MapView map;
    private String name;

    //Metodo newInstance para crear una nueva instancia del fragmento
    public static FragmentoMapa newInstance(double latitude, double longitude, String name) {
        FragmentoMapa fragment = new FragmentoMapa();
        Bundle args = new Bundle();
        args.putDouble(ARG_LATITUDE, latitude);
        args.putDouble(ARG_LONGITUDE, longitude);
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    //Metodo onCreate para inicializar el fragmento
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            latitude = getArguments().getDouble(ARG_LATITUDE);
            longitude = getArguments().getDouble(ARG_LONGITUDE);
            name = getArguments().getString(ARG_NAME);
        }

        //Configuramos el mapa
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    //Metodo onCreateView para crear la vista del fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflamos la vista del fragmento
        View view = inflater.inflate(R.layout.fragmento_mapa, container, false);
        map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        //Configuramos el mapa
        IMapController mapController = map.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);

        //Añadimos un marcador en la ubicación de la farmacia que mostrará su nombre
        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setTitle(name);
        map.getOverlays().add(marker);

        //Manejar eventos de zoom y scroll con el ratón
        map.setOnGenericMotionListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_SCROLL && event.isFromSource(android.view.InputDevice.SOURCE_CLASS_POINTER)) {
                if (event.getAxisValue(android.view.MotionEvent.AXIS_VSCROLL) < 0.0f) {
                    mapController.zoomOut();
                } else {
                    mapController.zoomIn();
                }
                return true;
            }
            return false;
        });

        map.addMapListener(this);

        //Configuramos el botón de volver a la lista de farmacias
        Button btnVolver = view.findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());

        return view;
    }

    //Metodo onResume para reanudar el mapa
    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
        Configuration.getInstance().load(getContext(), getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    //Metodo onPause para pausar el mapa
    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
        Configuration.getInstance().save(getContext(), getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    //Metodo onDestroy para destruir el mapa
    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDetach();
    }

    @Override
    public boolean onScroll(ScrollEvent event) {
        return false;
    }

    @Override
    public boolean onZoom(ZoomEvent event) {
        return false;
    }
}