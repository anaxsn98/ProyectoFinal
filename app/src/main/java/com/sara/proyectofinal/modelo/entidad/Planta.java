package com.sara.proyectofinal.modelo.entidad;

import java.io.Serializable;

public class Planta implements Serializable {

    private int id;
    private String nombre;
    private String fechaIni;
    private String fechaFin;
    private int regar;// 0 sí 1 no
    private int intervaloTiempoRiego;// 0 una vez al dia, 1 una vez a la semana, 2 una vez al mes
    private int ml;
    private int luz; // 0 sí 1 no
    private int intervaloTiempoLuz;// 0 una vez al dia, 1 una vez a la semana, 2 una vez al mes
    private int minLuz;
    private int ventilador; // 0 sí 1 no
    private int intervaloTiempoVentilador;// 0 una vez al dia, 1 una vez a la semana, 2 una vez al mes
    private int minVentilador;
    private String amor;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Planta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getRegar() {
        return regar;
    }

    public void setRegar(int regar) {
        this.regar = regar;
    }

    public int getIntervaloTiempoRiego() {
        return intervaloTiempoRiego;
    }

    public void setIntervaloTiempoRiego(int intervaloTiempoRiego) {
        this.intervaloTiempoRiego = intervaloTiempoRiego;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public int getLuz() {
        return luz;
    }

    public void setLuz(int luz) {
        this.luz = luz;
    }

    public int getIntervaloTiempoLuz() {
        return intervaloTiempoLuz;
    }

    public void setIntervaloTiempoLuz(int intervaloTiempoLuz) {
        this.intervaloTiempoLuz = intervaloTiempoLuz;
    }

    public int getMinLuz() {
        return minLuz;
    }

    public void setMinLuz(int minLuz) {
        this.minLuz = minLuz;
    }

    public int getVentilador() {
        return ventilador;
    }

    public void setVentilador(int ventilador) {
        this.ventilador = ventilador;
    }

    public int getIntervaloTiempoVentilador() {
        return intervaloTiempoVentilador;
    }

    public void setIntervaloTiempoVentilador(int intervaloTiempoVentilador) {
        this.intervaloTiempoVentilador = intervaloTiempoVentilador;
    }

    public int getMinVentilador() {
        return minVentilador;
    }

    public void setMinVentilador(int minVentilador) {
        this.minVentilador = minVentilador;
    }

    public String getAmor() {
        return amor;
    }

    public void setAmor(String amor) {
        this.amor = amor;
    }
}
