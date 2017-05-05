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

    private ConfigStage configuracao;
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
     */
    public ControlStage(T controller, ConfigStage configuracao) {
        super();
        this.configuracao = configuracao;
        this.controller = controller;
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
            this.prerapaTela();
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
            this.prerapaTela();
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
            this.prerapaTela();
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
            this.prerapaTela();
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
     */
    private void configTela() {
        this.palco = this.configuracao.getPalco();
        this.palco.setTitle(this.configuracao.getTitleStage() == null ? "Sem titulo" : this.configuracao.getTitleStage());
        this.palco.setResizable(this.configuracao.isResizable());
        this.palco.setMaximized(this.configuracao.isShowMaximized());
        this.palco.setFullScreen(this.configuracao.isShowFullScreen());
        if (!this.configuracao.getUrlFromIcon().isEmpty()) {
            this.palco.getIcons().add(new Image(getClass().getResourceAsStream(this.configuracao.getUrlFromIcon())));
        }
        this.cena = new Scene(this.root);
        this.palco.setScene(this.cena);
    }

    /**
     * Faz todas as preparações da Tela em sequencia correta.
     * @throws IOException
     * @throws Exception 
     */
    private void prerapaTela() throws IOException, Exception {
        this.loaderFXML();
        this.configTela();
        this.verificaConfiguracoes();
    }

    /**
     * Carrega os arquivo FXML para exibição.
     * @throws IOException 
     */
    private void loaderFXML() throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(this.configuracao.getUrlFromFXML()));
        this.root = this.loader.load();
        this.controller = this.loader.getController();
    }

    /**
     * Verifica as configurações de Clearcampos e EnableCampos e faz a execução dos mesmo.
     */
    private void verificaConfiguracoes() {
        if (this.configuracao.isAutoClearCampos() && super.isShowStage()) {
            ((Inicializador) this.controller).clearCampos();
        }
        if (this.configuracao.isAutoEnableCampos()) {
            ((Inicializador) this.controller).enableCampos(super.isEnableCampos());
            if (!super.isCorrectShowForEnableCampos()) {
                System.out.println("Você ativou o EnableCampos, mas está chamando a tela"
                        + " utilizando o método show() que não tem como parâmetro enablecampos, por favor utilize um método"
                        + " show que tenha como parâmetro EnableCampos para informar atráves dos parâmetros se os campos"
                        + " devem ser ativados ou não!");
            }
        }
    }

    /**
     * Carrega novamente o FXML.
     * @throws IOException - Uma Excecao referente ao carregamento do FXML. 
     */
    public void reloaderFXML() throws IOException {
        if (super.isShowStage()) {
            this.loaderFXML();
        }
    }

    /**
     * Carrega novamente as configurações.
     */
    public void reloaderConfigStage() {
        if (super.isShowStage()) {
            this.configTela();
        }
    }

    /**
     * Carrega novamente todos os processos de exibição da tela.
     */
    public void reloaderAll() {
        super.setShowStage(false);
    }

    /**
     *  Retorna a classe de Configuração Inicial.
     * @see ConfigStage
     * @return - Classe ConfigStage 
     */
    public ConfigStage getConfiguracao() {
        return configuracao;
    }

    /**
     *  Troca a Classe de Configuração por outra.
     * @see ConfigStage
     * @param configuracao - ConfigStage
     */
    public void setConfiguracao(ConfigStage configuracao) {
        this.configuracao = configuracao;
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
        return cena;
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
        return loader;
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
        return controller;
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
