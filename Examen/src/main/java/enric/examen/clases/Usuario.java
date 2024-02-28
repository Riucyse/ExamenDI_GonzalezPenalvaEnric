package enric.examen.clases;

import java.time.LocalDate;

/**
 * Es la clase que representa un usuario individual
 *
 * @author Enric Gonzalez
 * @version 1.0
 */

public class Usuario {

    private String dni;
    private LocalDate fechaAltaSancion;

    /**
     * Constructor para crear un nuevo Usuario con su DNI
     * La fecha de alta de la sancion se pondra a NULL, y se indicara mas tarde, si procede
     *
     * @param nuevoDni El DNI del nuevo Usuario
     * @see Usuario#sancionar()
     */
    public Usuario(String nuevoDni){
        this.dni = nuevoDni;
        this.fechaAltaSancion = null;
    }

    /**
     * El metodo que se usara desde la biblioteca para sancionar al Usuario en caso de que haya devuelto un libro
     * con retraso
     *
     * @see Biblioteca#devolver(int, String, LocalDate)
     */
    public void sancionar(){
        LocalDate fechaHastaSancion = LocalDate.now();
        fechaHastaSancion = fechaHastaSancion.plusDays(10);
        this.fechaAltaSancion = fechaHastaSancion;
    }

    /**
     * El metodo que se usara desde la biblioteca para que, cuando haya pasado el tiempo necesario, se retire la sancion
     * al ususario
     *
     * @see Biblioteca
     */
    public void quitarSancion(){
        if(fechaAltaSancion != null){
            fechaAltaSancion = null;
        }
    }


    /**
     * El metodo que se usara desde la biblioteca para revisar si ya estaba sancionado previamente
     *
     * @return TRUE si ya estaba sancionado, FALSE si no lo estaba
     * @see Biblioteca
     */
    public boolean estaSancionado(){
        if(fechaAltaSancion == null){
            return false;
        } else{
            return true;
        }
    }

    /**
     * Metodo para obtener el DNI del Usuario
     *
     * @return String del DNI del usuario
     */
    public String getDni() {
        return dni;
    }
}
