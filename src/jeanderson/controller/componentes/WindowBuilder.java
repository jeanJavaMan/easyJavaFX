/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import jeanderson.controller.interfaces.Inicializador;
import jeanderson.controller.control.ControlWindow;

/**
 * Classe responsavel pela construção do ControlWindow baseado nas configuraçõe
 * da Classe de Controller.
 *
 *
 * @author Jeanderson
 */
public class WindowBuilder {

    
    /**
     * Faz a construção do controlWindow baseado nas configurações definidas
     * atráves do método defineConfigurationWindow() sobreescrito na classe de
     * Controller.
     *
     * @param <U> Generico, seu tipo será do tipo da classe de controller
     * passada.
     * @param controller Classe de Controller.
     * @return Classe ControlWindow referente a janela.
     */
    public static <U extends Inicializador> ControlWindow<U> construct(U controller) {
        Configurator configuration = controller.defineConfigurationWindow();                
        return ControlWindow.prepareBuilder(configuration).build();
    }
}
