package com.sara.proyectofinal.modelo.entidad;

import java.io.Serializable;
import java.util.List;



public class Usuario implements Serializable {
	private int id;
	private String correo;
	private String nombre;
	private String pwd;
	private String codigo_invernadero;

	public Usuario() {
		super();
	}

	public Usuario(int id, String correo, String nombre, String pwd) {
		super();
		this.id = id;
		this.correo = correo;
		this.nombre = nombre;
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCodigo_invernadero() {
		return codigo_invernadero;
	}

	public void setCodigo_invernadero(String codigo_invernadero) {
		this.codigo_invernadero = codigo_invernadero;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", correo='" + correo + '\'' +
				", nombre='" + nombre + '\'' +
				", pwd='" + pwd + '\'' +
				", codigo_invernadero='" + codigo_invernadero + '\'' +
				'}';
	}
}
