package enric.examen.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Es la clase que gestiona todos los libros, usuarios y prestamos de la biblioteca
 *
 * @author Enric Gonzalez
 * @version 1.0
 */

public class Biblioteca {

    private List<Libro> biblioteca;
    private List<Prestamo> prestamos;
    private List<Usuario> usuarios;

    /**
     * Constructor base que crea la clase biblioteca, con List vacios de Usuarios, Libros y Prestamos
     *
     * @see Usuario
     * @see Libro
     * @see Prestamo
     */
    public Biblioteca(){
        biblioteca = new ArrayList<>();
        prestamos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    /**
     * Metodo para dar de alta dentro de la biblioteca un nuevo libro
     * Revisa que no haya ningun libro con la misma ID y/o ISBN dentro de la biblioteca y no los repite
     * Devuelve TRUE si se ha añadido tras comprobar que no tenia ID/ISBN repetido
     * Devuelve FALSE si habia ID/ISBN repetido en la biblioteca
     *
     * @param nuevaId La nueva ID del libro en la bilioteca
     * @param nuevoTitulo El titulo del libro
     * @param nuevoIsbn El ISBN del libro
     * @param nuevoAutor El autor del libro
     * @param nuevaTematica La tematica del libro
     * @param nuevaFechaEdicion La fecha de edicion del libro
     * @return Booleano, TRUE si se añadio el libro, FALSE si no se añadio
     * @see Libro
     */
    public boolean altaLibro(int nuevaId, String nuevoTitulo, String nuevoIsbn, String nuevoAutor, String nuevaTematica,
                             LocalDate nuevaFechaEdicion){
        List<Libro> librosConMismaId = new ArrayList<>();
        librosConMismaId.addAll(biblioteca.stream().filter(Libro -> Libro.getIdLibro() == nuevaId).toList());
        librosConMismaId.addAll(biblioteca.stream().filter(Libro -> Libro.getIsbn() == nuevoIsbn).toList());
        if(librosConMismaId.isEmpty()){
            biblioteca.add(new Libro(nuevaId, nuevoTitulo, nuevoIsbn, nuevoAutor, nuevaTematica, nuevaFechaEdicion));
            return true;
        } else{
            return false;
        }
    }

    /**
     * Da de baja un libro en la biblioteca
     * Busca el libro segun su ID en la bilbioteca y lo da de baja
     * Devuelve TRUE si se ha dado de baja, o si no existia previamente
     * Devuelve FALSE si existia, y por algún error no se dio de baja
     *
     * @param idLibro La ID del libro en la biblioteca a borrar
     * @return Boolean. TRUE si el libro ya no existe, FALSE si, pese a la accion, sigue existiendo
     * @see Libro
     */
    public boolean bajaLibro(int idLibro){
        List<Libro> libroABorrar = biblioteca.stream().filter(Libro -> Libro.getIdLibro() == idLibro).toList();
        if(libroABorrar.isEmpty()){
            return true;
        } else{
            try{
                biblioteca.remove(libroABorrar.stream().findFirst());
                return true;
            } catch(Exception e){
                return false;
            }
        }
    }

    /**
     * Da de baja un libro en la biblioteca
     * Busca el libro segun su ISBN en la bilbioteca y lo da de baja
     * Devuelve TRUE si se ha dado de baja, o si no existia previamente
     * Devuelve FALSE si existia, y por algún error no se dio de baja
     *
     * @param isbnLibro La ISBN del libro en la biblioteca a borrar
     * @return Boolean. TRUE si el libro ya no existe, FALSE si, pese a la accion, sigue existiendo
     * @see Libro
     */
    public boolean bajaLibro(String isbnLibro){
        List<Libro> libroABorrar = biblioteca.stream().filter(Libro -> Libro.getIsbn().equals(isbnLibro)).toList();
        if(libroABorrar.isEmpty()){
            return true;
        } else{
            try{
                biblioteca.remove(libroABorrar.stream().findFirst());
                return true;
            } catch(Exception e){
                return false;
            }
        }
    }

    /**
     * Da de alta a un usuario en la biblioteca según su DNI
     * No se pueden dar de baja los usuarios posteriormente
     * Revisa previamente si en la biblioteca habia un usuario con su DNI, y si lo habia, no lo vuelve a añadir
     * Devuelve TRUE si no existia un usuario con su DNI y se ha añadido satisfactoriamente
     * Devuelve FALSE si ya existia un usuario con su DNI, y no se ha añadido
     *
     * @param nuevoDni DNI del nuevo usuario a añadir
     * @return Boolean. TRUE si se añadio el usuario, FALSE si ya existia y no se añadio
     */
    public boolean altaUsuario(String nuevoDni){
        List<Usuario> usuariosConMismoDni = usuarios.stream().filter(Usuario -> Usuario.getDni().equals(nuevoDni)).toList();
        if(usuariosConMismoDni.isEmpty()){
            usuarios.add(new Usuario(nuevoDni));
            return true;
        } else{
            return false;
        }
    }

    /**
     * Crea un nuevo prestamo relacionando un Libro con su Usuario en cuestion
     * Revisa previamente que existen tanto el Libro como el Usuario del prestamo
     * Devuelve TRUE si existe tanto Libro como Usuario y se ha hecho el prestamo
     * Devuelve FALSE si no existia el Libro o Usuario, y por ende, no se pudo hacer el prestamo
     *
     * @param idLibro ID del Libro a hacer el prestamo en la biblioteca
     * @param dniUsuario DNI del Usuario a hacer el prestamo en la biblioteca
     * @return Boolean. TRUE si se hizo el prestamo satisfactoriamente, FALSE si no
     * @see Libro
     * @see Usuario
     */
    public boolean prestar(int idLibro, String dniUsuario){
        List<Libro> libroAPrestar = biblioteca.stream().filter(Libro -> Libro.getIdLibro() == idLibro).toList();
        List<Usuario> usuarioQuePide = usuarios.stream().filter(Usuario -> Usuario.getDni().equals(dniUsuario)).toList();
        if((!(usuarioQuePide.isEmpty())) && (!(libroAPrestar.isEmpty()))){
            Libro libro = libroAPrestar.get(0);
            Usuario usuario = usuarioQuePide.get(0);
            LocalDate fechaADevolver = LocalDate.now();
            fechaADevolver = fechaADevolver.plusDays(14);
            prestamos.add(new Prestamo(libro, usuario, fechaADevolver));
            return true;
        } else{
            return false;
        }
    }

    /**
     * Gestiona la devulucion del libro
     * Primero revisa si en la biblioteca existia un prestamo con el Libro y Usuario en cuestion
     * Si lo habia, revisa su fecha esperada de retorno del libro con la fecha actual
     * Devuelve TRUE si se ha podido devolver el Libro pero el Usuario lo ha devuelto con retraso y por ende esta
     * sancionado
     * Devuelve FALSE si el usuario no ha sido sancionado al devolver el libro por retraso, o no se encontro el prestamo
     * en la biblioteca
     *
     * @param idLibro ID del Libro de la biblioteca prestado
     * @param dniUsuario DNI del Usuario de la biblioteca que hizo el prestamo
     * @param fechaDevuelta LocalDate de la fecha en la que se devolvio el libro
     * @return Boolean. TRUE si el usuario ha sido sancionado, FALSE si no ha sido sancionado, o no existia el prestamo
     * @see Libro
     * @see Usuario
     * @see Prestamo
     */
    public boolean devolver(int idLibro, String dniUsuario, LocalDate fechaDevuelta){
        List<Prestamo> prestamosRealizados = prestamos.stream().filter(Prestamo ->
            (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
        ).toList();
        if(prestamosRealizados.isEmpty()){
            return false;
        } else{
            Prestamo prestamo = prestamosRealizados.get(0);
            LocalDate fechaDeDevolucion = prestamo.getFechaPrevistaDevolucion();
            if(fechaDeDevolucion.getYear() - fechaDevuelta.getYear() < 0){
                usuarios.stream().filter(Usuario -> Usuario.getDni().equals(dniUsuario)).findFirst().get().sancionar();
                prestamos.stream().filter(Prestamo ->
                        (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
                ).findFirst().get().setFechaDevolucion(LocalDate.now());
                return true;
            } else if(fechaDeDevolucion.getMonthValue() - fechaDevuelta.getMonthValue() < 0){
                usuarios.stream().filter(Usuario -> Usuario.getDni().equals(dniUsuario)).findFirst().get().sancionar();
                prestamos.stream().filter(Prestamo ->
                        (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
                ).findFirst().get().setFechaDevolucion(LocalDate.now());
                return true;
            } else if(fechaDeDevolucion.getDayOfMonth() - fechaDevuelta.getDayOfMonth() < 0){
                usuarios.stream().filter(Usuario -> Usuario.getDni().equals(dniUsuario)).findFirst().get().sancionar();
                prestamos.stream().filter(Prestamo ->
                        (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
                ).findFirst().get().setFechaDevolucion(LocalDate.now());
                return true;
            } else{
                prestamos.stream().filter(Prestamo ->
                        (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
                ).findFirst().get().setFechaDevolucion(LocalDate.now());
                return false;
            }
        }
    }
}
