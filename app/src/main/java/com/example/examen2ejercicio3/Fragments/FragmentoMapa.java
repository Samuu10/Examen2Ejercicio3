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
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;

import com.example.examen2ejercicio3.R;

public class FragmentoMapa extends Fragment {
    private static final String ARG_LATITUDE = "latitude";
    private static final String ARG_LONGITUDE = "longitude";
    private static final String ARG_NAME = "name";
    private double latitude;
    private double longitude;
    private MapView map;
    private String name;

    public static FragmentoMapa newInstance(double latitude, double longitude, String name) {
        FragmentoMapa fragment = new FragmentoMapa();
        Bundle args = new Bundle();
        args.putDouble(ARG_LATITUDE, latitude);
        args.putDouble(ARG_LONGITUDE, longitude);
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            latitude = getArguments().getDouble(ARG_LATITUDE);
            longitude = getArguments().getDouble(ARG_LONGITUDE);
            name = getArguments().getString(ARG_NAME);
        }
        // Configuración de osmdroid
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_mapa, container, false);
        map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);

        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setTitle(name);
        map.getOverlays().add(marker);

        // Enable zoom with mouse wheel
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

        Button btnVolver = view.findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume(); // Resume the map
        Configuration.getInstance().load(getContext(), getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause(); // Pause the map
        Configuration.getInstance().save(getContext(), getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDetach(); // Detach the map
    }
}