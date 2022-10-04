package dad.imc;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{
    
    private VBox root;
    private HBox pesoBox, alturaBox;

    private Label pesoLabel;
    private Label unidadPesoLabel;
    private Label alturaLabel;
    private Label unidadAlturaLabel;
    private Label imcLabel;
    private Label imcNombreLabel;

    private TextField pesoText;
    private TextField alturaText;

    private DoubleProperty valorImc;
    private DoubleProperty valorPeso;
    private DoubleProperty valorAltura;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /*LINEA DE INTRODUCCION DE PESO */
        pesoLabel = new Label();
        pesoLabel.setText("Peso: ");
        unidadPesoLabel = new Label();
        unidadPesoLabel.setText(" kg");
        pesoText = new TextField();

        pesoBox = new HBox(5, pesoLabel, pesoText, unidadPesoLabel);
        pesoBox.setAlignment(Pos.CENTER);

        /*LINEA DE INTRODUCCION DE ALTURA */
        alturaLabel = new Label();
        alturaLabel.setText("Altura: ");
        unidadAlturaLabel = new Label();
        unidadAlturaLabel.setText(" cm");
        alturaText = new TextField();

        alturaBox = new HBox(5, alturaLabel, alturaText, unidadAlturaLabel);
        alturaBox.setAlignment(Pos.CENTER);

        /*LINEA DE MOSTRADO DE IMC */
        imcLabel = new Label();
        imcLabel.setText("IMC: " + valorImc);
        
        /*LINEA DE MOSTRADO DEL NOMBRE DEL IMC */
        imcNombreLabel = new Label();
        // imcNombreLabel.setText(nombreImc(valorImc));

        /* TODO
         * IMPLEMENTAR EL BINDING DE valorImc PARA PODER REALIZAR LOS CALCULOS NECESARIOS
         */

        /*CREACION DE CONTENEDOR VERTICAL Y CARACTERISTICAS */
        root = new VBox(5, pesoBox, alturaBox, imcLabel, imcNombreLabel);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);

        /*CREACION Y DISPLAY DE LA VENTANA */
        Scene scene = new Scene(root, 500, 320);
        primaryStage.setTitle("IMC");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private String nombreImc(DoubleProperty dp) {
        // TODO Implementar el retorno de los nombres para los valores de IMC
        String s = "";
        double valor = dp.get();
        if(valor <= 0)
            s = "Bajo Peso | Normal | Sobrepeso | Obeso";
        else if(valor < 18.5)
            s = "Bajo Peso";
        else if(valor >= 18.5 && valor < 25)
            s = "Normal";
        else if(valor >= 25 && valor < 30)
            s = "Sobrepeso";
        else
            s = "Obeso";
        return s;
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
