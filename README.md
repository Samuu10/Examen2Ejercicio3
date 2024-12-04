# APLICACIÓN PARA LISTAR FARMACIAS

## OBJETIVO:
El objetivo de esta aplicación es proporcionar información sobre las farmacias de Zaragoza.
La aplicación mostrará una lista con el nombre, el teléfono y un icono representativo de cada farmacia. 
Al seleccionar una farmacia, el usuario podrá ver su ubicación en el mapa. 
En relación a la base de datos, se han añadido 30 farmacias reales de Zaragoza con sus respectivos datos a una base de datos de Firebase.

## ESTRUCTURA:

### - Clases JAVA:
- **ActividadPrincipal**: Clase principal de la aplicación que carga el fragmento inicial `FragmentoLista`.
- **FragmentoLista**: Fragmento que muestra una lista de farmacias cargadas desde Firebase.
- **FragmentoMapa**: Fragmento que muestra un mapa con la ubicación de la farmacia seleccionada.
- **FirebaseHelper**: Clase que se encarga de cargar las farmacias desde Firebase en segundo plano.
- **AdaptadorFarmacia**: Adaptador para el `RecyclerView` que muestra la lista de farmacias.
- **Farmacia**: Clase modelo que representa una farmacia con atributos como nombre, teléfono, latitud y longitud.

### - Archivos XML:
- **actividad_principal.xml**: Layout principal que contiene un `FrameLayout` para cargar los fragmentos.
- **fragmento_lista.xml**: Layout del fragmento que muestra la lista de farmacias con un `RecyclerView`.
- **fragmento_mapa.xml**: Layout del fragmento que muestra el mapa con un `MapView` y un botón para volver.
- **item_farmacia.xml**: Layout para cada elemento de la lista de farmacias, que incluye un `ImageView` y dos `TextView`.
- **item_background.xml**: Drawable que define el fondo de cada elemento de la lista de farmacias.
## LINK:
https://github.com/Samuu10/Examen2Ejercicio3.git
