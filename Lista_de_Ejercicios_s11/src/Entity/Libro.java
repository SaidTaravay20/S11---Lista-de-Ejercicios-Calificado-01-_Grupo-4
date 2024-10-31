/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

 public class Libro {
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private String genero;
    private int numeroPaginas;
    private String estadoPrestamo; // (OCUPADO,LIBRE,NO EXISTE)
    private int numeroCapitulos;
    private String fechaLiberacion; //(EN CASO ESTE OCUPADO)

    private Libro(Builder builder) { //Construcxtor
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.añoPublicacion = builder.añoPublicacion;
        this.genero = builder.genero;
        this.numeroPaginas = builder.numeroPaginas;
        this.estadoPrestamo = builder.estadoPrestamo;
        this.numeroCapitulos = builder.numeroCapitulos;
        this.fechaLiberacion = builder.fechaLiberacion;
    }

    //getters 
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAñoPublicacion() { return añoPublicacion; }
    public String getGenero() { return genero; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public String getEstadoPrestamo() { return estadoPrestamo; }
    public int getNumeroCapitulos() { return numeroCapitulos; }
    public String getFechaLiberacion() { return fechaLiberacion; }

    @Override
    public String toString() {
        return "Libro:" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", añoPublicacion=" + añoPublicacion +
                ", genero='" + genero + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", estadoPrestamo='" + estadoPrestamo + '\'' +
                ", numeroCapitulos=" + numeroCapitulos +
                ", fechaLiberacion='" + fechaLiberacion;
    }

    // Clase Builder para la creación de instancias de Libro
    public static class Builder {
        private String titulo;
        private String autor;
        private int añoPublicacion;
        private String genero;
        private int numeroPaginas;
        private String estadoPrestamo = "LIBRE"; // Valor por defecto
        private int numeroCapitulos;
        private String fechaLiberacion = "N/A"; // Valor por defecto

        // Métodos para asignar valores a los atributos de Libro
        public Builder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder setAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public Builder setAñoPublicacion(int añoPublicacion) {
            this.añoPublicacion = añoPublicacion;
            return this;
        }

        public Builder setGenero(String genero) {
            this.genero = genero;
            return this;
        }

        public Builder setNumeroPaginas(int numeroPaginas) {
            this.numeroPaginas = numeroPaginas;
            return this;
        }

        public Builder setEstadoPrestamo(String estadoPrestamo) {
            this.estadoPrestamo = estadoPrestamo;
            return this;
        }

        public Builder setNumeroCapitulos(int numeroCapitulos) {
            this.numeroCapitulos = numeroCapitulos;
            return this;
        }

        public Builder setFechaLiberacion(String fechaLiberacion) {
            this.fechaLiberacion = fechaLiberacion;
            return this;
        }

        // Método build que retorna una nueva instancia de Libro
        public Libro build() {
            return new Libro(this);
        }
    }
}