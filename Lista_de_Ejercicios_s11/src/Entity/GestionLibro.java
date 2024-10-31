/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionLibro implements Observado {

    private List<Libro> listaLibros;
    private LibroRepositorio repositorio;
    private List<Observador> observadores;


    public GestionLibro() {
        this.repositorio = new LibroRepositorio();
        this.listaLibros = repositorio.cargarLibros();
        this.observadores = new ArrayList<>();
    }


    public void agregarLibro(Libro libro) {
        listaLibros.add(libro);
        repositorio.guardarLibros(listaLibros);
        System.out.println("Libro agregado: " + libro.getTitulo());
        if ("LIBRE".equalsIgnoreCase(libro.getEstadoPrestamo())) {
            notificarObservadores("El libro '" + libro.getTitulo() + "' está disponible.");
        }
    }


    public void listarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca");
        } else {
            System.out.println("Lista de libros en la biblioteca:");
            listaLibros.forEach(System.out::println);
        }
    }
    

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        return listaLibros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }


    public List<Libro> buscarLibrosPorAutor(String autor) {
        return listaLibros.stream()
                .filter(libro -> libro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarLibrosPorGenero(String genero) {
        return listaLibros.stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }


    public boolean eliminarLibro(String titulo) {
        Optional<Libro> libroAEliminar = buscarLibroPorTitulo(titulo);
        if (libroAEliminar.isPresent()) {
            listaLibros.remove(libroAEliminar.get());
            repositorio.guardarLibros(listaLibros);
            System.out.println("Libro eliminado: " + titulo);
            return true;
        } else {
            System.out.println("Libro no existe: " + titulo);
            return false;
        }
    }


    public boolean actualizarEstadoPrestamo(String titulo, String nuevoEstado, String fechaLiberacion) {
        Optional<Libro> libroAActualizar = buscarLibroPorTitulo(titulo);
        if (libroAActualizar.isPresent()) {
            Libro libro = libroAActualizar.get();           
            Libro libroActualizado = new Libro.Builder()
                    .setTitulo(libro.getTitulo())
                    .setAutor(libro.getAutor())
                    .setAñoPublicacion(libro.getAñoPublicacion())
                    .setGenero(libro.getGenero())
                    .setNumeroPaginas(libro.getNumeroPaginas())
                    .setNumeroCapitulos(libro.getNumeroCapitulos())
                    .setEstadoPrestamo(nuevoEstado)
                    .setFechaLiberacion(fechaLiberacion)
                    .build();
            listaLibros.set(listaLibros.indexOf(libro), libroActualizado);
            repositorio.guardarLibros(listaLibros);
            System.out.println("Se actualizó el préstamo para el libro: " + titulo);


            if ("LIBRE".equalsIgnoreCase(nuevoEstado)) {
                notificarObservadores("El libro '" + titulo + "' está ahora disponible.");
            }
            return true;
        } else {
            System.out.println("Libro no encontrado: " + titulo);
            return false;
        }
    }


    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
}
