package jeanderson.controller.componentes;

import javafx.fxml.Initializable;
import jeanderson.controller.control.ControlWindow;

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
     * Deve ser implementado pela classe herdeira para que todos os campos sejam limpos da tela.
     * @deprecated Utilize a nova classe para controle de janela, chamada ControlWindow, e utilize no lugar deste método o clearFields.
     * @see ControlWindow
     */
    public void clearCampos() {
        //deve ser implementado.
    }
    /**
     * Método que é chamado por padrão, quando abrir a Tela pela segunda vez.
     * Deve ser implementado pela classe herdeira para que todos os campos sejam limpos da tela.
     */
    public void clearFields(){
        //deve ser implementado.
    }
    
    /**
     *  Método que ativa campos de uma Tela.
     * O usuario ao abrir a Tela deve informa se este método deve ser chamado ou não. Por padrão ele não
     * é chamado automaticamente.
     * @param enable - Boolean 
     * @deprecated Utilize a nova classe para controle de janela, chamada ControlWindow, e utilize no lugar deste método o enableFields.
     * @see ControlWindow
     */
    public void enableCampos(boolean enable){
        //deve ser implementado.
    }
    
     /**
     *  Método que ativa campos de uma Tela.
     * O usuario ao abrir a Tela deve informa se este método deve ser chamado ou não. Por padrão ele não
     * é chamado automaticamente.
     * @param enable - Boolean 
     */
    public void enableFields(boolean enable){
        //deve ser implementado.
    }
    
    /**
     * Método é chamado quando a Tela é aberta em modo de edição.
     * @param data - Objeto por parâmetro.
     */
    public void editMode(Object data){
        //deve ser implementado.
    }

}
