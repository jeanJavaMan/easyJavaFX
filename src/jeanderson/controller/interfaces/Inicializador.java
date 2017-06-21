package jeanderson.controller.interfaces;

import javafx.fxml.Initializable;
import jeanderson.controller.componentes.Configurator;
import jeanderson.controller.control.ControlWindow;
import jeanderson.controller.util.ConfigurationEasyJavaFX;

/**
 * Classe que Ajuda na implementação das classes de Controller
 *
 * @author Jeanderson
 */
public abstract class Inicializador implements Initializable {

    public Inicializador() {
    }

    /**
     * Método que é chamado por padrão, quando abrir a Tela pela segunda vez.
     * Deve ser implementado pela classe herdeira para que todos os campos sejam
     * limpos da tela.
     */
    public void clearFields() {
        //deve ser implementado.        
    }

     /**
     * Método que ativa campos de uma Tela. O usuario ao abrir a Tela deve
     * informa se este método deve ser chamado ou não. Por padrão ele não é
     * chamado automaticamente.
     *
     * @param enable - Boolean
     */
    public void enableFields(boolean enable) {
        //deve ser implementado.
    }

    /**
     * Método é chamado quando a Tela é aberta em modo de edição.
     *
     * @param data - Objeto por parâmetro.
     */
    public void editMode(Object data) {
        //deve ser implementado.
    }

    /**
     * Contém as configurações necessárias para construção da Tela.
     * @return Configuraçõe da Janela.
     */
    public Configurator defineConfigurationWindow(){
        //este método só é utilizado para uma construção baseada na classe de controller.
        System.err.println("Não foi sobreescrito o método defineConfigurationWindow() em sua classe de controller.");
        return new Configurator() {
            @Override
            public String url_Fxml() {
                return ConfigurationEasyJavaFX.getDEFAULT_FXML();
            }            
        };
    }
    /**
     * Apos a construcao de um ControlWindow, ele é passado como parâmetro para sua classe
     * de controller.
     * @param control ControlWindow referente a classe de controller.
     */
    public void afterConstruct(ControlWindow control){
        //deve ser implementado.
    }
}
