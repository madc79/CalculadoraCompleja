package dad.calculadora;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.util.converter.NumberStringConverter;
import javafx.stage.Stage;

public class CalculadoraCompleja extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cuadros de texto a rellenar
        TextField Real1 = new TextField ();//Aquí el usuario rellena
        TextField Real2 = new TextField ();//Aquí el usuario rellena
        TextField Img1 = new TextField ();//Aquí el usuario rellena  
        TextField Img2 = new TextField ();//Aquí el usuario rellena
        
        //Mostrar Resultado
        TextField ResultadoReal = new TextField ();
        TextField ResultadoImaginario = new TextField ();
        
        Separator separator = new Separator (); //Linea que separa el resultado del resto
             
        
        //Diseño HBox
        HBox root = new HBox(); //Creo la ventana
        root.setStyle("-fx-background-color: orange;");//Color de fondo
        root.setAlignment(Pos.CENTER);  //La alineo para que salga en el centrp
        
        //Hago el ComboBox con las operacioens
        ComboBox <String> OperadorCombo = new ComboBox <String> ();
        OperadorCombo.getItems().addAll("+", "-", "/", "*"); 
        //Y lo muestro
        VBox operatorVBox = new VBox(OperadorCombo);
        
        
        
        //Contenedor vertical entrada y resultado
        VBox entradaVBOX = new VBox();
        VBox resultadoVBox = new VBox();

        //Mostrar
        HBox entrada1 = new HBox(Real1, Img1, new Label("i"));
        HBox entrada2 = new HBox(Real2, Img2, new Label("i"));
        HBox ResultadoBox = new HBox(ResultadoReal, ResultadoImaginario, new Label("i"));
        
        //Tamaño del cuadro de texto
        Real1.setPrefWidth(80);
        Real2.setPrefWidth(80);
        
        Img1.setPrefWidth(80); 
        Img2.setPrefWidth(80);
        
        ResultadoReal.setPrefWidth(80);
        ResultadoImaginario.setPrefWidth(80);
        
        //Tamaño Ventana
        Scene scene = new Scene(root, 250, 100);
       
        

        // Binding
        DoubleProperty real1 = new SimpleDoubleProperty();
        DoubleProperty real2 = new SimpleDoubleProperty();
        
        DoubleProperty imag1 = new SimpleDoubleProperty();
        DoubleProperty imag2 = new SimpleDoubleProperty();
        
        DoubleProperty resultReal = new SimpleDoubleProperty();
        DoubleProperty resultImag = new SimpleDoubleProperty();
        
        //Listener
        resultReal.addListener((obs, oldVal, newVal) -> 
        	System.out.print("resultado = " + newVal));
        resultImag.addListener((obs, oldVal, newVal) -> 
        	System.out.print("resultado = " + newVal));
        
        OperadorCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        	{System.out.print("resultado = " + newVal);

        // Bind textos
        Real1.textProperty().bindBidirectional(real1, new NumberStringConverter());
        Real2.textProperty().bindBidirectional(real2, new NumberStringConverter());
        
        Img1.textProperty().bindBidirectional(imag1, new NumberStringConverter());
        Img2.textProperty().bindBidirectional(imag2, new NumberStringConverter());
        
        ResultadoReal.textProperty().bind(resultReal.asString());
        ResultadoImaginario.textProperty().bind(resultImag.asString());

            
            //Switch para el operador
            switch (newVal) {           
                case "+":                
                    resultReal.bind(real1.add(real2));
                    resultImag.bind(imag1.add(imag2));
                    break;                    
                case "-":                   
                    resultReal.bind(real1.subtract(real2));
                    resultImag.bind(imag1.subtract(imag2));
                    break;                    
                case "/":                   
                    resultReal.bind((real1.multiply(real2).add(imag1.multiply(imag2)).divide(real2.multiply(real2).add(imag2.multiply(imag2)))));
                    resultImag.bind((imag1.multiply(real2).subtract(real1.multiply(imag2)).divide(real2.multiply(real2).add(imag2.multiply(imag2)))));
                    break;          
                case "*":                  
                    resultReal.bind(real1.multiply(real2).subtract(imag1.multiply(imag2)));
                    resultImag.bind(real1.multiply(imag2).add(imag1.multiply(real2)));
                    break;               
            }
        }
        );
        
        //Agrego todo
        entradaVBOX.getChildren().addAll(entrada1, entrada2, separator, ResultadoBox);
        root.getChildren().addAll(operatorVBox, entradaVBOX, resultadoVBox);
        
        //Para ejecutarlo
        primaryStage.setTitle("Calculadora Compleja"); //Título que se verá en la venetana
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Asigno el primer valor como deafult del combo
        OperadorCombo.getSelectionModel().selectFirst();
        
    }
}









