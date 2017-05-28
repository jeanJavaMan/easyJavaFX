/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import java.util.HashMap;
import jeanderson.controller.control.ControlWindow;

/**
 *
 * @author Jeanderson
 */
public enum StaticMod {
    CONTROLADOR();

    private final HashMap<String, ControlWindow> controladores;
    StaticMod() {
        this.controladores = new HashMap<>();
    }
    
    public void addControlador(ControlWindow controlador){
        this.controladores.put(controlador.getWindow().getController().getClass().getName(), controlador);
    }
    
    public ControlWindow getControlador(Class<? extends Inicializador> classController) throws Exception{
        ControlWindow controlador =  this.controladores.get(classController.getName());
        if(controlador == null){
            throw new Exception("Classe não definida como static: Não foi encontrado a seguinte classe de controller: " + classController.getName()
            +" verifique se a classe que você está requisitando foi definida como static utilizando o método defineHowStaticClass()."
            );
        }
        return controlador;
    }
}
