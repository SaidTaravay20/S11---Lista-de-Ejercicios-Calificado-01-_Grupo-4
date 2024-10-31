/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionLibro implements Subject {
    //lista de libro
    private List<Libro> listaLibros;
    private List<Observer> suscriptores;

    //inicializadoress
    public GestionLibro() {
        this.listaLibros = new ArrayList<>();
        this.suscriptores = new ArrayList<>();
    }

    // Método para agregar un libro a la lista
    public void agregarLibro(Libro libro) {
        listaLibros.add(libro);
        System.out.println("Libro agregado exitosamente: " + libro.getTitulo());
    }

    // Método para listar todos los libros en la biblioteca
    public void listarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            System.out.println("Lista de libros en la biblioteca:");
            listaLibros.forEach(System.out::println);
        }
    }

    // Método para buscar un libro por título
    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        return listaLibros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    // Método para buscar libros por autor o género usando expresiones lambda
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

    // Método para eliminar un libro por título
    public boolean eliminarLibro(String titulo) {
        Optional<Libro> libroAEliminar = buscarLibroPorTitulo(titulo);
        if (libroAEliminar.isPresent()) {
            listaLibros.remove(libroAEliminar.get());
            System.out.println("Libro eliminado exitosamente: " + titulo);
            return true;
        } else {
            System.out.println("Libro no encontrado: " + titulo);
            return false;
        }
    }

    // Método para actualizar el estado de préstamo de un libro
    public boolean actualizarEstadoPrestamo(String titulo, String nuevoEstado, String fechaLiberacion) {
        Optional<Libro> libroAActualizar = buscarLibroPorTitulo(titulo);
        if (libroAActualizar.isPresent()) {
            Libro libro = libroAActualizar.get();
            // Crear una nueva instancia de Libro usando el patrón Builder
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
            // Reemplazar el libro en la lista
            listaLibros.set(listaLibros.indexOf(libro), libroActualizado);
            System.out.println("Estado de préstamo actualizado para el libro: " + titulo);
            return true;
        } else {
            System.out.println("Libro no encontrado: " + titulo);
            return false;
        }
    }
    @Override
    public void agregarSuscriptor(Observer observer) {
        suscriptores.add(observer);
        System.out.println("Suscriptor añadido exitosamente.");
    }

    @Override
    public void eliminarSuscriptor(Observer observer) {
        suscriptores.remove(observer);
        System.out.println("Suscriptor eliminado exitosamente.");
    }

    @Override
    public void notificarSuscriptores(String mensaje) {
        for (Observer suscriptor : suscriptores) {
            suscriptor.update(mensaje);
        }
    }

    // Método para notificar cuando un libro esté disponible
    public void notificarDisponibilidadLibro(String tituloLibro) {
        Optional<Libro> libro = buscarLibroPorTitulo(tituloLibro);
        if (libro.isPresent() && libro.get().getEstadoPrestamo().equals("LIBRE")) {
            notificarSuscriptores("El libro '" + tituloLibro + "' ahora está disponible.");
        } 
    }
}