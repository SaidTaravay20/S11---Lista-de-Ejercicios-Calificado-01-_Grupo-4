/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;


import java.util.ArrayList;
import java.util.List;

public class Usuario implements Observer {
    private int id;
    private String nombre;
    private String apellidos;
    private  int edad;
    private List<Libro> librosPreferidos;

    public Usuario(int id, String nombre, String apellidos, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.librosPreferidos = new ArrayList<>();
    }

    public void agregarLibroPreferido(Libro libro) {
        librosPreferidos.add(libro);
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación para " + nombre + ": " + mensaje);
    }

}
