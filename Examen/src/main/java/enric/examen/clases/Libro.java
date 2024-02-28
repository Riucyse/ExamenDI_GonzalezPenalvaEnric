package enric.examen.clases;

import java.time.LocalDate;

/**
 * Es la clase que representa un libro individual
 *
 * @author Enric Gonzalez
 * @version 1.0
 */
public class Libro {

    private int idLibro;
    private String titulo;
    private String isbn;
    private String autor;
    private String tematica;
    private LocalDate fechaEdicion;

    /**
     * Constructor con parametros para rellenar todos los campos del libro
     *
     * @param nuevaId int. ID del nuevo libro en la biblioteca
     * @param nuevoTitulo String. Titulo del nuevo libro
     * @param nuevoIsbn String. ISBN del nuevo libro
     * @param nuevoAutor String. Autor del nuevo libro
     * @param nuevaTematica String. Tematica del nuevo libro
     * @param nuevaFechaEdicion LocalDate. Fecha de edicion del nuevo libro
     */
    public Libro(int nuevaId, String nuevoTitulo, String nuevoIsbn, String nuevoAutor, String nuevaTematica,
                 LocalDate nuevaFechaEdicion){
        this.idLibro = nuevaId;
        this.titulo = nuevoTitulo;
        this.isbn = nuevoIsbn;
        this.autor = nuevoAutor;
        this.tematica = nuevaTematica;
        this.fechaEdicion = nuevaFechaEdicion;
    }

    /**
     * Metodo para obtener la ID del Libro
     *
     * @return int con la ID del Libro
     */
    public int getIdLibro() {
        return idLibro;
    }

    /**
     * Metodo para obtener el ISBN del Libro
     *
     * @return String con el ISBN del Libro
     */
    public String getIsbn() {
        return isbn;
    }
}
