/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import java.util.HashMap;
import jeanderson.controller.control.ControlWindow;
import jeanderson.controller.util.Configuration;
import jeanderson.controller.util.ConfigurationEasyJavaFX;

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
        return WindowBuilder.construct(controller, false);
    }

    /**
     * Faz a construção do controlWindow baseado nas configurações definidas
     * atráves do método defineConfigurationWindow() sobreescrito na classe de
     * Controller.
     *
     * @param <U> Generico, seu tipo será do tipo da classe de controller
     * passada.
     * @param controller Classe de Controller.
     * @param defineHowStatic Se deve ou não definir está classe como estática.
     * @return Classe ControlWindow referente a janela.
     */
    public static <U extends Inicializador> ControlWindow<U> construct(U controller, boolean defineHowStatic) {
        Configurator configuration = controller.defineConfigurationWindow();        
        ControlBuilder<U> controlBuilder = ControlWindow.prepareBuilder()
                .addNameOrUrlFXML(configuration.url_Fxml())
                .addTitle(configuration.title())
                .addUrlFromIcon(configuration.url_Icon())
                .fullScreen(configuration.isFullscreen())
                .maximized(configuration.isMaximized())
                .resizable(configuration.isResizable());
        if (defineHowStatic) {
            controlBuilder.defineHowStaticClass();
        }
        ControlWindow<U> controlWindow = controlBuilder.build();
        return controlWindow;
    }
}
