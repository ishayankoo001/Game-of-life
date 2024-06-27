module com.example.gameoflife {
    requires com.SGS.dependency;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.gameoflife to javafx.fxml;
    exports com.example.gameoflife;
}