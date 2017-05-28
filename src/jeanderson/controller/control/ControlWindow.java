/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import javafx.stage.Stage;
import jeanderson.controller.componentes.Exibicao;
import jeanderson.controller.componentes.Inicializador;

/**
 *
 * @author Jeanderson
 * @param <T>
 */
public class ControlWindow<T extends Inicializador> implements Exibicao{    

    private final ControlBuilder<T> controlBuilder;
    
    public ControlWindow(ControlBuilder<T> controlBuilder) {
        this.controlBuilder = controlBuilder;
    }

    //métodos estático para inicializar.
    public static ControlBuilder prepareBuilder() {
        return new ControlBuilder<>();
    }
    
    public static ControlBuilder prepareBuilder(Stage primaryStage) {
        return new ControlBuilder<>(primaryStage);
    }

    @Override
    public void show() {
       if(this.controlBuilder.getStage().isShowing()){
           T controller = this.controlBuilder.getController();
           controller.clearFields();
           this.controlBuilder.getStage().show();
           this.controlBuilder.getStage().requestFocus();
       }else{
           this.controlBuilder.getStage().show();
           this.controlBuilder.getStage().requestFocus();
       }
    }

    @Override
    public void show(Class<? extends Inicializador> windowReference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
