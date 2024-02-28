module enric.examen {
    requires javafx.controls;
    requires javafx.fxml;


    opens enric.examen to javafx.fxml;
    exports enric.examen;
}