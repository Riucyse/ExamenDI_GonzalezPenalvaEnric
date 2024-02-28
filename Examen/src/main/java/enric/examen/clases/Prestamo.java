package enric.examen.clases;

import java.time.LocalDate;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaDevolucion;
    private LocalDate fechaPrevistaDevolucion;

    public Prestamo(Libro nuevoIdLibro, Usuario nuevoDniUsuario, LocalDate nuevaFechaPrevistaDevolucion){
        this.libro = nuevoIdLibro;
        this.usuario = nuevoDniUsuario;
        this.fechaDevolucion = null;
        this.fechaPrevistaDevolucion = nuevaFechaPrevistaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaPrevistaDevolucion() {
        return fechaPrevistaDevolucion;
    }

    public void setFechaPrevistaDevolucion(LocalDate fechaPrevistaDevolucion) {
        this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
    }
}
