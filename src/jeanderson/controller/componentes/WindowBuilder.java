/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import java.util.HashMap;
import jeanderson.controller.control.ControlWindow;
import jeanderson.controller.util.Configuration;

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
        HashMap<Configuration, Object> configuration = controller.defineConfigurationWindow();
        String title = configuration.containsKey(Configuration.TITLE) ? (String) configuration.get(Configuration.TITLE) : "Janela";
        String icon = configuration.containsKey(Configuration.ICON_URL) ? (String) configuration.get(Configuration.TITLE) : "/jeanderson/view/img/easyJavaFX.png";
        String fxml = configuration.containsKey(Configuration.FXML_URL) ? (String) configuration.get(Configuration.FXML_URL) : "/jeanderson/view/DefaultView.fxml";
        boolean fullscreen = configuration.containsKey(Configuration.FULLSCREEN) ? (Boolean) configuration.get(Configuration.FULLSCREEN) : false;
        boolean maximized = configuration.containsKey(Configuration.MAXIMIZED) ? (Boolean) configuration.get(Configuration.MAXIMIZED) : false;
        boolean resizable = configuration.containsKey(Configuration.RESIZABLE) ? (Boolean) configuration.get(Configuration.RESIZABLE) : true;
        ControlBuilder<U> controlBuilder = ControlWindow.prepareBuilder()
                .addNameOrUrlFXML(fxml)
                .addTitle(title)
                .addUrlFromIcon(icon)
                .fullScreen(fullscreen)
                .maximized(maximized)
                .resizable(resizable);
        if (defineHowStatic) {
            controlBuilder.defineHowStaticClass();
        }
        ControlWindow<U> controlWindow = controlBuilder.build();
        return controlWindow;
    }
}
