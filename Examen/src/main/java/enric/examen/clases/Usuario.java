package enric.examen.clases;

import java.time.LocalDate;

public class Usuario {

    private String dni;
    private LocalDate fechaAltaSancion;

    public Usuario(){
        this.dni= "00000000A";
        this.fechaAltaSancion = null;
    }

    public Usuario(String nuevoDni){
        this.dni = nuevoDni;
        this.fechaAltaSancion = null;
    }

    public void sancionar(){
        LocalDate fechaHastaSancion = LocalDate.now();
        fechaHastaSancion = fechaHastaSancion.plusDays(10);
        this.fechaAltaSancion = fechaHastaSancion;
    }

    public void quitarSancion(){
        if(fechaAltaSancion != null){
            fechaAltaSancion = null;
        }
    }

    public boolean estaSancionado(){
        if(fechaAltaSancion == null){
            return false;
        } else{
            return true;
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaAltaSancion() {
        return fechaAltaSancion;
    }

    public void setFechaAltaSancion(LocalDate fechaAltaSancion) {
        this.fechaAltaSancion = fechaAltaSancion;
    }
}
