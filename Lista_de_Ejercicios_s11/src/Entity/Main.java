package Entity;

public class Main {
    public static void main(String[] args) {
        GestionLibro gestionLibro = new GestionLibro();

        // Crear usuarios
        Usuario usuario1 = new Usuario(1, "Said", "Taravay", 30);
        Usuario usuario2 = new Usuario(2, "Jesus", "Perez", 25);

        // Suscribir los usuarios como observadores
        gestionLibro.agregarObservador(usuario1);
        gestionLibro.agregarObservador(usuario2);

        // Crear libros
        Libro libro1 = new Libro.Builder()
            .setTitulo("Cien años de soledad")
            .setAutor("Gabriel Garcia Marquez")
            .setAñoPublicacion(1967)
            .setGenero("Ficción")
            .setNumeroPaginas(400)
            .setNumeroCapitulos(20)
            .setEstadoPrestamo("LIBRE") 
            .build();

        Libro libro2 = new Libro.Builder()
            .setTitulo("El Principito")
            .setAutor("Antoine de Saint-Exupéry")
            .setAñoPublicacion(1943)
            .setGenero("Ficción")
            .setNumeroPaginas(96)
            .setNumeroCapitulos(27)
            .setEstadoPrestamo("PRESTADO") 
            .setFechaLiberacion("2024-12-01") 
            .build();

        // Agregar libros a la gestión
        gestionLibro.agregarLibro(libro1);
        gestionLibro.agregarLibro(libro2);

        // Configurar el libro favorito del usuario1
        usuario1.agregarLibroPreferido(libro1);

        // Actualizar la versión de "Cien años de soledad" y notificar a los observadores
        System.out.println("\n--- Actualizando versión del libro preferido ---");
        gestionLibro.actualizarVersionLibro("Cien años de soledad", "Nueva Edición 2024");

        // Probar otras funcionalidades
        gestionLibro.actualizarEstadoPrestamo("El Principito", "LIBRE", "2024-12-01");
        gestionLibro.listarLibros();
        gestionLibro.eliminarLibro("Cien años de soledad"); 
    }
}