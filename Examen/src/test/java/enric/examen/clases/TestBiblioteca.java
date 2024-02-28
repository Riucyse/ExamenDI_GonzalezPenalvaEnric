package enric.examen.clases;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestBiblioteca {

    private Biblioteca obtenerBiblioteca(){
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.altaLibro(100, "Titulo100", "ISBN100", "Autor100", "Tematica100",
                LocalDate.now());
        biblioteca.altaLibro(200, "Titulo200", "ISBN200", "Autor200", "Tematica200",
                LocalDate.now());
        biblioteca.altaLibro(300, "Titulo300", "ISBN300", "Autor300", "Tematica300",
                LocalDate.now());
        biblioteca.altaUsuario("10101010A");
        biblioteca.altaUsuario("20202020B");
        biblioteca.altaUsuario("30303030C");
        return biblioteca;
    }


    @Test
    public void testPrestar(){
        Biblioteca biblioteca = obtenerBiblioteca();

        assertFalse(biblioteca.prestar(404, "10101010A"));
        assertFalse(biblioteca.prestar(100, "40404040D"));
        assertTrue(biblioteca.prestar(200, "30303030C"));
    }

    @Test
    public void testFechas(){
        Biblioteca biblioteca = obtenerBiblioteca();

        assertTrue(biblioteca.prestar(100, "10101010A"));
        assertTrue(biblioteca.prestar(200, "20202020B"));
        assertFalse(biblioteca.prestar(404, "30303030C"));
        assertFalse(biblioteca.prestar(300, "40404040D"));

        LocalDate fechaATiempo = LocalDate.now();
        fechaATiempo = fechaATiempo.plusDays(3);
        LocalDate fechaRetrasada = LocalDate.now();
        fechaRetrasada = fechaRetrasada.plusDays(23);

        // Es false en el caso en el que NO le hayan sancionado, y como tiene 3 dias desde que que lo pidio, no tiene restraso
        assertFalse(biblioteca.devolver(100, "10101010A", fechaATiempo));
        // Es true en el caso en el que SI le hayan sancionado, y como tiene varios dias de retraso, esta sancionado
        assertTrue(biblioteca.devolver(200, "20202020B", fechaRetrasada));
    }

    @Test
    public void testAltaLibro(){
        Biblioteca biblioteca = obtenerBiblioteca();

        // Es true porque, si el libro no estaba en la base de datos y se añadio satisfactoriamente, devuelve true
        assertTrue(biblioteca.altaLibro(400, "Titulo400", "ISBN400", "Autor400", "Tematica400",
                LocalDate.now()));
        /* En el resto de casos es false, porque revisa individualmente si esta la id o el isbn, y en el caso de que haya
        uno de ambos, o ambos, deuvelve false, indicando que ya habia un libro con el mismo id y/o isbn, y que no sera
        añadido */
        assertFalse(biblioteca.altaLibro(100, "Titulo500", "ISBN500", "Autor500", "Tematica500",
                LocalDate.now()));
        assertFalse(biblioteca.altaLibro(600, "Titulo600", "ISBN200", "Autor600", "Tematica600",
                LocalDate.now()));
        assertFalse(biblioteca.altaLibro(300, "Titulo700", "ISBN400", "Autor700", "Tematica700",
                LocalDate.now()));
    }
}