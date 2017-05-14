/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import javafx.scene.control.Alert;

/**
 * Interface que obriga a implementação da montagem do dialog do javaFX.
 * @author Jeanderson
 */
public interface DialogAssembly {
    public Alert createDialog(String msg , String title, String header);
}
