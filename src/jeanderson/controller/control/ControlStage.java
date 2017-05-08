/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.AuxIntern;
import jeanderson.controller.util.ConfigStage;

/**
 * Classe responsável por todo o trabalho feito para inicializar uma Tela em
 * JavaFX.
 *
 * @author Jeanderson
 * @param <T> - Informa a Classe do Controller feito pelo usuário.
 */
public class ControlStage<T extends Inicializador> extends AuxIntern {

    private Stage palco;
    private Parent root;
    private Scene cena;
    private FXMLLoader loader;
    private T controller;

    /**
     * Construtor padrão que recebe a Classe de Controller, e a configuração da Janela
     * @param controller - Classe do Controller
     * @param configuracao - Classe de Configuração
     * @see ConfigStage
     * @deprecated Não utilizar mais esse construtor pois a classe ConfigStage se tornou deprecated.
     */
    public ControlStage(T controller, ConfigStage configuracao) {
        super();
        this.controller = controller;
    }
    /**
     * Construtor que recebe a Classe ControlStageBuilder já com configurações prontas.
     * @see ControlStageBuilder
     * @param controlBuilder 
     */
    public ControlStage(ControlStageBuilder<T> controlBuilder){
        super();
        this.palco = controlBuilder.getStage();
        this.root = controlBuilder.getParentRoot();
        this.cena = controlBuilder.getScene();
        this.loader = controlBuilder.getfXMLLoader();
        this.controller = controlBuilder.getController();
        super.setAutoClearCampos(controlBuilder.isAutoClearCampos());
        super.setAutoEnableCampos(controlBuilder.isAutoEnableCampos());
    }

    /**
     * Cria uma nova Builder da Classe ControlStageBuilder. Utilizando 
     * a ideia do Design Pattern Builder
     * @return - Classe ControlStageBuilder
     * @see ControlStageBuilder 
     */
    public static ControlStageBuilder newBuilder() {
        return new ControlStageBuilder();
    }

    /**
     * Cria uma nova Builder da Classe ControlStageBuilder, e recebe um Stage. Utilizando 
     * a ideia do Design Pattern Builder
     * @param palco - Stage
     * @see ControlStageBuilder
     * @return - Classe ControlStageBuilder      
     */
    public static ControlStageBuilder newBuilder(Stage palco) {
        return new ControlStageBuilder(palco);
    }

    /**
     * Exibi a Tela já pronta.
     * Obs: ao chamar esse método pela segunda vez, ele não recarregara o arquivo FXML
     * por motivo de desempenho, apenas exibira a Tela novamente.
     * Recomendo que classes de controle implementem os métodos clearCampos entre outros.
     * @see Inicializador
     * @throws Exception - Uma cadeia de Excecoes.
     */
    public void show() throws Exception {
        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(false);
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Exibi a Tela já pronta e chama o método EnableCampos da Classe de controller.
     * Obs: ao chamar esse método pela segunda vez, ele não recarregara o arquivo FXML
     * por motivo de desempenho, apenas exibira a Tela novamente.
     * Recomendo que classes de controle implementem os métodos clearCampos entre outros.
     * @see Inicializador
     * @param enableCampos - o usuario informa se ativa ou não os campos
     * @throws Exception - Uma cadeia de Excecoes.
     */
    public void show(boolean enableCampos) throws Exception {
        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(true);
            super.setEnableCampos(enableCampos);
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Exibi a Tela já pronta e chama o método editMode da classe de Controller. Para exibir como tela de edição.
     * Obs: ao chamar esse método pela segunda vez, ele não recarregara o arquivo FXML
     * por motivo de desempenho, apenas exibira a Tela novamente.
     * Recomendo que classes de controle implementem os métodos clearCampos entre outros.
     * @see Inicializador
     * @param data - dado que será usado pela classe de Controller implemetado pelo usuario.
     * @throws Exception - Uma cadeia de Excecoes. 
     */
    public void showEditMode(Object data) throws Exception {

        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(false);
            ((Inicializador) this.controller).editMode(data);
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            ((Inicializador) this.controller).editMode(data);
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Exibi a Tela já pronta e chama o método editMode da classe de Controller e chama o método EnableCampos da Classe de controller
     * . Para exibir a tela em modo de edição.
     * Obs: ao chamar esse método pela segunda vez, ele não recarregara o arquivo FXML
     * por motivo de desempenho, apenas exibira a Tela novamente.
     * Recomendo que classes de controle implementem os métodos clearCampos entre outros.
     * @see Inicializador
     * @param data - dado que será usado pela classe de Controller implemetado pelo usuario.
     * @param enablecampos - o usuario informa se ativa ou não os campos
     * @throws Exception - Uma cadeia de Excecoes. 
     */
    public void showEditMode(Object data, boolean enablecampos) throws Exception {
        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(false);
            super.setEnableCampos(enablecampos);
            ((Inicializador) this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            ((Inicializador) this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Faz as configurações do palco.
     * @deprecated Não é mais necessário devido a nova implementação da Classe ControlStageBuilder.
     */
    private void configTela() {
        //removido códigos não mais necessários.
    }

    /**
     * Faz todas as preparações da Tela em sequencia correta.
     * @deprecated Não é mais necessária devido a nova implementação da Classe ControlStageBuilder
     * @throws IOException
     * @throws Exception 
     */
    private void prerapaTela() throws IOException, Exception {
        //removido códigos não mais necessários.
    }

    /**
     * Carrega os arquivo FXML para exibição.
     * @deprecated Não é mais necessária devido a nova implementação da Classe ControlStageBuilder.
     * @throws IOException 
     */
    private void loaderFXML() throws IOException {
        //removido códigos não mais necessários.
    }

    /**
     * Verifica as configurações de Clearcampos e EnableCampos e faz a execução dos mesmo.
     */
    private void verificaConfiguracoes() {
        if (super.isAutoClearCampos() && super.isShowStage()) {
            ((Inicializador) this.controller).clearCampos();
        }
        if (super.isAutoEnableCampos()) {
            ((Inicializador) this.controller).enableCampos(super.isEnableCampos());
            if (!super.isCorrectShowForEnableCampos()) {
                System.out.println("Você ativou o EnableCampos, mas está chamando a tela"
                        + " utilizando o método show() que não tem como parâmetro enablecampos, por favor utilize um método"
                        + " show que tenha como parâmetro EnableCampos\n para informar atráves dos parâmetros se os campos"
                        + " devem ser ativados ou não!");
            }
        }
    }

    /**
     *  Retorna a classe de Configuração Inicial.
     * @see ConfigStage
     * @deprecated Não é mais utilizado a Classe de configuração
     * @return - Devido ao se torna deprecated pode retorna null.
     */
    public ConfigStage getConfiguracao() {
        return null;
    }

    /**
     *  Troca a Classe de Configuração por outra.
     * @see ConfigStage
     * @deprecated Não é mais necessário o uso da Classe ConfigStage.
     * @param configuracao - ConfigStage
     */
    public void setConfiguracao(ConfigStage configuracao) {
        //removido códigos não mais necessários.
    }

    /**
     * Retorna o Stage usado.
     * @return - Classe Stage.
     */
    public Stage getStage() {
        return this.palco;
    }

    /**
     * Troca o Stage por outro.
     * @param palco - Stage.
     */
    public void setStage(Stage palco) {
        this.palco = palco;
    }

    /**
     * Retorna o Parent.
     * @see Parent
     * @return - Parent
     */
    public Parent getParent() {
        return root;
    }

    /**
     * Troca o Parent por outro.
     * @see Parent
     * @param root - Parent
     */
    public void setParent(Parent root) {
        this.root = root;
    }

    /**
     * Retorna a Scene utilizada.
     * @return - Scene
     */
    public Scene getScene() {
        return this.cena;
    }

    /**
     * Troca a Scene por outra.
     * @see Scene
     * @param cena - Scene
     */
    public void setScene(Scene cena) {
        this.cena = cena;
    }

    /**
     * Retorna a Classe do FXMLLoader usado para carregar os arquivos FXML.
     * @see FXMLLoader
     * @return - FXMLLoader
     */
    public FXMLLoader getFXMLLoader() {
        return this.loader;
    }

    /**
     * Troca a Classe FXMLLoader por outra.
     * @see FXMLLoader
     * @param loader - FXMLLoader
     */
    public void setFXMLLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    /**
     * Retorna a Classe de Controller da tela.
     * @see T
     * @return - Uma Classe de Controller
     */
    public T getController() {
        return this.controller;
    }

    /**
     * Troca a Classe de Controller por outra.
     * @see T
     * @param controller - Uma Classe de Controller.
     */
    public void setController(T controller) {
        this.controller = controller;
    }

}
