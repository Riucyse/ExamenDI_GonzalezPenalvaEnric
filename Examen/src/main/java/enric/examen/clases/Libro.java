package enric.examen.clases;

import java.time.LocalDate;

public class Libro {

    private int idLibro;
    private String titulo;
    private String isbn;
    private String autor;
    private String tematica;
    private LocalDate fechaEdicion;

    public Libro(){
        this.idLibro = 0;
        this.titulo = "";
        this.isbn = "";
        this.autor = "";
        this.tematica = "";
        this.fechaEdicion = LocalDate.now();
    }

    public Libro(int nuevaId, String nuevoTitulo, String nuevoIsbn, String nuevoAutor, String nuevaTematica,
                 LocalDate nuevaFechaEdicion){
        this.idLibro = nuevaId;
        this.titulo = nuevoTitulo;
        this.isbn = nuevoIsbn;
        this.autor = nuevoAutor;
        this.tematica = nuevaTematica;
        this.fechaEdicion = nuevaFechaEdicion;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public LocalDate getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(LocalDate fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }
}
