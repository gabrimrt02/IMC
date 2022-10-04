package dad.imc;

import javafx.application.Application;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Aplicacion extends Application {
    
    // Declaración de los contenedores
    private VBox root;
    private HBox pesoBox, alturaBox;

    //Declaración de los diferentes textos y cuadros de entrada
    private Label pesoLabel;
    private Label unidadPesoLabel;
    private Label alturaLabel;
    private Label unidadAlturaLabel;
    private Label imcLabel;
    private Label imcNombreLabel;

    // Declaracion de los cuadros de texto
    private TextField pesoText;
    private TextField alturaText;

    // Declaración de los atriutos que almacenan numeros
    private DoubleProperty valorImc = new SimpleDoubleProperty();
    private DoubleProperty valorPeso = new SimpleDoubleProperty();
    private DoubleProperty valorAltura = new SimpleDoubleProperty();

    // Sobreescribimos el método que heredamos de la clase Application
    @Override
    public void start(Stage primaryStage) throws Exception {

        /*Creacion de la línea de introducción del peso */
        pesoLabel = new Label();
        pesoLabel.setText("Peso: ");
        unidadPesoLabel = new Label();
        unidadPesoLabel.setText(" kg");
        pesoText = new TextField();

        /*Creación de la caja peso */
        pesoBox = new HBox(5, pesoLabel, pesoText, unidadPesoLabel);
        pesoBox.setAlignment(Pos.CENTER);

        /*Creacion de la linea de introducción de la altura */
        alturaLabel = new Label();
        alturaLabel.setText("Altura: ");
        unidadAlturaLabel = new Label();
        unidadAlturaLabel.setText(" cm");
        alturaText = new TextField();

        /*Creación de la caja altura */
        alturaBox = new HBox(5, alturaLabel, alturaText, unidadAlturaLabel);
        alturaBox.setAlignment(Pos.CENTER);

        /*Creación de Labels de IMC */
        imcLabel = new Label();
        imcNombreLabel = new Label();

        imcLabel.setText("IMC: ");
        imcNombreLabel.setText("Bajo Peso | Normal | Sobrepeso | Obeso");

        /*Creación del contenedor vertical y sus características */
        root = new VBox(5, pesoBox, alturaBox, imcLabel, imcNombreLabel);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);

        /*Creación de la ventana */
        Scene scene = new Scene(root, 500, 320);
        primaryStage.setTitle("IMC");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*Bindings */
        pesoText.textProperty().bindBidirectional(valorPeso, new NumberStringConverter());
        
        alturaText.textProperty().bindBidirectional(valorAltura, new NumberStringConverter());
    
        // imcLabel.textProperty().bind(valorImc.asString("IMC: %.2f"));
        

        /*Logica */
        DoubleExpression alturaConvertida = valorAltura.divide(100);
        DoubleExpression cuadradoAltura = alturaConvertida.multiply(alturaConvertida);
        DoubleExpression formulaImc = valorPeso.divide(cuadradoAltura);

        valorImc.bind(formulaImc);
        valorImc.addListener((o, ov, nv) -> {
            imcNombreLabel.setText(nombreImc((Double) nv));
            imcLabel.setText(textoImc((Double) nv));
        });

    }

    private String textoImc(Double valorImc) {
        String s = "IMC: ";
        if(!valorImc.isInfinite() && !valorImc.isNaN())
            s = String.format("IMC: %.2f", valorImc);
        return s;
    }

    private String nombreImc(Double valorImc) {
        String s;
        if(valorImc.isInfinite() || valorImc.isNaN())
            s = "Bajo Peso | Normal | Sobrepeso | Obeso";
        else {
            if(valorImc < 18.5)
                s = "Bajo Peso";
            else if(valorImc >= 18.5 && valorImc < 25)
                s = "Normal";
            else if(valorImc >= 25 && valorImc < 30)
                s = "Sobrepeso";
            else
                s = "Obeso";
        }
        return s;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
