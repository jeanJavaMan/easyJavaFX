/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import jeanderson.controller.annotations.DefineConfiguration;
import jeanderson.controller.control.ControlWindow;
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
        Configurator configuration = controller.defineConfigurationWindow();
        return ControlWindow.prepareBuilder(configuration).build();
    }

    /**
     * Faz a construção do ControlWindow baseado nas configurações definidas
     * atráves da anotação presente na classe passada.
     *
     * @param <U> Generico, seu tipo será do tipo da classe de controller
     * passada.
     * @param classe Classe de Controller referente a Janela
     * @return objeto da classe ControlWindow referente a janela.
     */
    public static <U extends Inicializador> ControlWindow<U> construct(Class<U> classe) {
        if (classe.isAnnotationPresent(DefineConfiguration.class)) {
            DefineConfiguration defineConfiguration = classe.getAnnotation(DefineConfiguration.class);
            Configurator configurator = new Configurator() {
                @Override
                public String url_Fxml() {
                    return defineConfiguration.URL_FXML();
                }
            };
            configurator.setTitle(defineConfiguration.title());
            configurator.setFullscreen(defineConfiguration.FULLSCREEN());
            configurator.setResizable(defineConfiguration.RESIZABLE());
            configurator.setMaximized(defineConfiguration.MAXIMIZED());
            configurator.setAutoNewStage(defineConfiguration.AUTO_NEW_STAGE());
            configurator.setStaticClass(defineConfiguration.STATIC_CLASS());
            return ControlWindow.prepareBuilder(configurator).build();
        } else {
            System.err.println("Anotação @DefineConfiguration não está presente na classe passada como parâmetro! Classe: " + classe.getName());
            return ControlWindow.prepareBuilder(new Configurator() {
                @Override
                public String url_Fxml() {
                    return ConfigurationEasyJavaFX.getDEFAULT_FXML();
                }
            }).build();
        }
    }
}
