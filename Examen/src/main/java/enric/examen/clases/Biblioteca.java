package enric.examen.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private List<Libro> biblioteca;
    private List<Prestamo> prestamos;
    private List<Usuario> usuarios;

    public Biblioteca(){
        biblioteca = new ArrayList<>();
        prestamos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

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

    public boolean altaUsuario(String nuevoDni){
        List<Usuario> usuariosConMismoDni = usuarios.stream().filter(Usuario -> Usuario.getDni().equals(nuevoDni)).toList();
        if(usuariosConMismoDni.isEmpty()){
            usuarios.add(new Usuario(nuevoDni));
            return true;
        } else{
            return false;
        }
    }

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

    public boolean devolver(int idLibro, String dniUsuario){
        List<Prestamo> prestamosRealizados = prestamos.stream().filter(Prestamo ->
            (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
        ).toList();
        if(prestamosRealizados.isEmpty()){
            return false;
        } else{
            Prestamo prestamo = prestamosRealizados.get(0);
            LocalDate fechaDeDevolucion = prestamo.getFechaPrevistaDevolucion();
            if(fechaDeDevolucion.getYear() - LocalDate.EPOCH.getYear() < 0){
                usuarios.stream().filter(Usuario -> Usuario.getDni().equals(dniUsuario)).findFirst().get().sancionar();
                prestamos.stream().filter(Prestamo ->
                        (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
                ).findFirst().get().setFechaDevolucion(LocalDate.now());
                return true;
            } else if(fechaDeDevolucion.getMonthValue() - LocalDate.EPOCH.getMonthValue() < 0){
                usuarios.stream().filter(Usuario -> Usuario.getDni().equals(dniUsuario)).findFirst().get().sancionar();
                prestamos.stream().filter(Prestamo ->
                        (Prestamo.getLibro().getIdLibro() == idLibro && Prestamo.getUsuario().getDni().equals(dniUsuario))
                ).findFirst().get().setFechaDevolucion(LocalDate.now());
                return true;
            } else if(fechaDeDevolucion.getDayOfMonth() - LocalDate.now().getDayOfMonth() < 0){
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
