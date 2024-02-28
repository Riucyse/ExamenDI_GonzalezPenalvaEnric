package enric.examen.clases;

import java.time.LocalDate;

/**
 * Es la clase que representa un prestamo individual
 *
 * @author Enric Gonzalez
 * @version 1.0
 */
public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaDevolucion;
    private LocalDate fechaPrevistaDevolucion;

    /**
     * Constructor para crear un nuevo Prestamo con todos sus campos
     * La fecha de devolucion se deja en NULL, pues sera rellenado mas adelante en la Biblioteca
     *
     * @param nuevoIdLibro La ID del Libro en la biblioteca el cual sera prestado
     * @param nuevoDniUsuario EL DNI del Usuario que pide el prestamo del Libro
     * @param nuevaFechaPrevistaDevolucion LocalDate que indica que fecha se previene que se deberia devolver, suele ser 14 dias
     * @see Biblioteca
     */
    public Prestamo(Libro nuevoIdLibro, Usuario nuevoDniUsuario, LocalDate nuevaFechaPrevistaDevolucion){
        this.libro = nuevoIdLibro;
        this.usuario = nuevoDniUsuario;
        this.fechaDevolucion = null;
        this.fechaPrevistaDevolucion = nuevaFechaPrevistaDevolucion;
    }

    /**
     * Metodo para obtener el objeto Libro perteneciente al prestamo
     *
     * @return Object Libro perteneciente al prestamo
     * @see Libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Metodo para obtener el objeto Usuario perteneciente al prestamo
     *
     * @return Object Usuario perteneciente al prestamo
     * @see Usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Metodo para fijar la fecha en la que se devolvio el Libro
     *
     * @param fechaDevolucion LocalDate indicando la fecha de cuando se hizo la devolucion
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Metodo para obtener la fecha en la que se espera que se devuelva el Libro
     *
     * @return LocalDate con la fecha esperada
     */
    public LocalDate getFechaPrevistaDevolucion() {
        return fechaPrevistaDevolucion;
    }
}
