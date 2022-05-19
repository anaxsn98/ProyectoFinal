package com.sara.proyectofinal.singleton;

import com.sara.proyectofinal.modelo.entidad.Planta;

import java.util.ArrayList;
import java.util.List;

//El objetivo es guaradr la lista de usuarios

public class ListaPlantaSingleton {

    private static ListaPlantaSingleton instance;
    private List<Planta> listaPlanta;
    private int contador = 1;

    //Lo hacemos privado para que nadie entre en la clase,
    //porque si lo hago publico se pueden hacer news del objeto
    private ListaPlantaSingleton() {

        super();
    }

    //getInstance es como los getBeans
    //queremos que nos devuelva la instancia del objeto, no puede ser dinamico
    //tiene que ser estático porque es el método que te devuelve el objeto que es la instancia
    //La primera vez que llamamos al objeto no existe dede el principio del programa
    //Para no sobrecargar la memoria
    public static ListaPlantaSingleton getInstance() {
        if (instance == null) {
            instance = new ListaPlantaSingleton();
        }
        return instance;
    }

    public void inicializar() {
        listaPlanta = new ArrayList<>();
        addPlanta("Mona", "Gato", 2);
        addPlanta("Pascal", "Gato", 2);
        addPlanta("Rosa Melano", "Planta", 2);
        addPlanta("Susana Horia", "Planta", 2);
        addPlanta("Rosa Melpito", "Planta", 2);


    }

    public List<Planta> getListaPlanta() {
        return listaPlanta;
    }


    public void addPlanta(String nombre, String tipo, int edad) {
        Planta planta = new Planta();
        planta.setId(contador++);
        planta.setNombre(nombre);
        listaPlanta.add(planta);
    }
}
