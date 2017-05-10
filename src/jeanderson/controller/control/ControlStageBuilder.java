package jeanderson.controller.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jeanderson.controller.componentes.AllSee;
import jeanderson.controller.componentes.Inicializador;

/**
 * Classe complementar de ControlStage. Utilizando o design pattern builder
 *
 * @param <T> - Classe de Controller
 * @author Jeanderson
 */
public class ControlStageBuilder<T extends Inicializador> {

    private T controller;
    private Stage stage;
    private Parent parentRoot;
    private Scene scene;
    private FXMLLoader fXMLLoader;
    private String urlOrNameFromFXML;
    private String urlFromIcon;
    private String stageTitle;
    private boolean autoClearCampos = true;
    private boolean autoEnableCampos = false;
    private boolean showFullScreen = false;
    private boolean showMaximized = false;
    private boolean isResizable = true;
    /**
     * Variável que informa se foi usado o método defineAllSee
     */
    private boolean useAllSee = false;
    private int indexForAllSee;

    /**
     * Contrutor Padrão.
     */
    public ControlStageBuilder() {

    }

    /**
     * Contrutor padrão que recebe um Stage.
     *
     * @see Stage
     * @param primaryStage - Stage
     */
    public ControlStageBuilder(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Adiciona o Nome ou a URL do arquivo FXML. Obs: na urlOrName pode ser
     * passado só o nome do arquivo FXML, pois ele vai colocar que o arquivo
     * está em /view/ e sua extensão. Ex: passado Home - montara a seguinte URL
     * /view/Home.fxml Caso preferir mudar a URL é só passar URL.
     *
     * @param urlOrName - Nome ou a URL do arquivo FXML.
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder addNameFromFXML(String urlOrName) {
        this.verificaUrlFromFXML(urlOrName);
        return this;
    }

    /**
     * Adiciona a Classe de Controller.
     *
     * @param controller - classe de Controller.
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder addClassController(T controller) {
        this.controller = controller;
        return this;
    }

    /**
     * Adiciona a URL de um Icon para a Tela.
     *
     * @param urlFromIcon - String
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder addUrlFromIcon(String urlFromIcon) {
        this.urlFromIcon = urlFromIcon;
        return this;
    }

    /**
     * Adiciona um titulo para a Tela.
     *
     * @param title - String
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder addTitleStage(String title) {
        this.stageTitle = title;
        return this;
    }

    /**
     * Informa se deve ativar o ClearCampos. Obs: por padrão o ClearCampos já é
     * ativado.
     *
     * @see Inicializador
     * @param autoClear - Boolean
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder setAutoEnableClear(boolean autoClear) {
        this.autoClearCampos = autoClear;
        return this;
    }

    /**
     * Informa se deve ativar o EnableCampos. Obs: Por padrão é desativado.
     *
     * @param autoEnablecampos - Boolean
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder setAutoEnableCampos(boolean autoEnablecampos) {
        this.autoEnableCampos = autoEnablecampos;
        return this;
    }

    /**
     * Informa se deve abrir a Tela em modo Fullscreen.
     *
     * @param fullscreenMod - Boolean
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder setFullScreen(boolean fullscreenMod) {
        this.showFullScreen = fullscreenMod;
        return this;
    }

    /**
     * Informa se deve abrir a Tela Maximizada.
     *
     * @param maximizedMod - Boolean
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder setMaximized(boolean maximizedMod) {
        this.showMaximized = maximizedMod;
        return this;
    }

    /**
     * Informa se a Tela é redimensionavel.
     *
     * @param isResizable - Boolean
     * @return - Classe ControlStageBuilder
     */
    public ControlStageBuilder setResizable(boolean isResizable) {
        this.isResizable = isResizable;
        return this;
    }

    /**
     * Executas todas as ações necessárias para iniciar a Tela. Obs: se não for
     * passado a nome ou a URL do arquivo FXML será exibido uma Tela Default. Se
     * foi utilizado o método defineAllSee, então a classe será colocada em um
     * enum.
     *
     * @return Instancia da Classe ControlStage
     * @see ControlStage
     * @see AllSee
     * @throws java.lang.Exception - Várias camadas de exceções.
     */
    public ControlStage build() throws Exception {
        this.configuracoesIniciais();
        if (this.useAllSee) {
            ControlStage<T> control = new ControlStage(this);
            AllSee.CONTROLADORES.addControlador(this.indexForAllSee, control);
            return control;
        } else {
            return new ControlStage(this);
        }
    }

    /**
     * Retorna a classe de controller utilizada.
     *
     * @return Classe de Controller
     */
    public T getController() {
        return controller;
    }

    /**
     * Retorna o Stage, se o Stage é null é criado uma nova instancia.
     *
     * @return Objeto da Classe Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Retorna o Parent utilizado na construção.
     *
     * @return Um Objeto da Classe Parent.
     */
    public Parent getParentRoot() {
        return parentRoot;
    }

    /**
     * Retorna um Scene usado na construção.
     *
     * @return Um objeto da Classe Scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retorna um objeto da Classe FXMLLoader.
     *
     * @return Um objeto da Classe FXMLLoader.
     */
    public FXMLLoader getfXMLLoader() {
        return fXMLLoader;
    }

    /**
     * Retorna a URL do arquivo FXML.
     *
     * @return Uma String informando a URL do FXML.
     */
    public String getUrlFromFXML() {
        return urlOrNameFromFXML;
    }

    /**
     * Retorna a URL do icon utilizado.
     *
     * @return Uma String informando a URL do Icone.
     */
    public String getUrlFromIcon() {
        return urlFromIcon;
    }

    /**
     * Retorna o titulo do Stage.
     *
     * @return Uma String com o titulo da Stage.
     */
    public String getStageTitle() {
        return stageTitle;
    }

    /**
     * Informa se o AutoClearCampos está ativado. Obs: é ativado por padrão.
     *
     * @return Um Boolean informando situação.
     */
    public boolean isAutoClearCampos() {
        return autoClearCampos;
    }

    /**
     * Informa se o AutoEnableCampos está ativado Obs: ele é desativado por
     * padrão.
     *
     * @return Um boolean informando a situação.
     */
    public boolean isAutoEnableCampos() {
        return autoEnableCampos;
    }

    /**
     * Informa se é para exibir em FULLScreen.
     *
     * @return Um boolean informando a situação.
     */
    public boolean isShowFullScreen() {
        return showFullScreen;
    }

    /**
     * Informa se é para exibir em modo maximizado.
     *
     * @return Um boolean informando a situação.
     */
    public boolean isShowMaximized() {
        return showMaximized;
    }

    /**
     * Informa se a Tela é redimensionável.
     *
     * @return Um boolean informando a situação.
     */
    public boolean isIsResizable() {
        return isResizable;
    }

    /**
     * Adiciona uma instancia da Classe FXMLLoader.
     *
     * @param fXMLLoader
     * @return retorna a Classe ControlStageBuilder.
     */
    public ControlStageBuilder addClassFXMLLoader(FXMLLoader fXMLLoader) {
        this.fXMLLoader = fXMLLoader;
        return this;
    }

    /**
     * Adiciona uma instancia da Classe Scene.
     *
     * @param scene
     * @return retorna a Classe ControlStageBuilder.
     */
    public ControlStageBuilder addClassScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    /**
     * Adiciona uma instancia da Classe Parent.
     *
     * @param parent
     * @return retorna a Classe ControlStageBuilder.
     */
    public ControlStageBuilder addClassParent(Parent parent) {
        this.parentRoot = parent;
        return this;
    }

    /**
     * Método responsável para verificar se foi digitado somente o nome do
     * arquivo FXML ou se foi passado a URL do arquivo FXML.
     *
     * @param urlOrName
     */
    private void verificaUrlFromFXML(String urlOrName) {
        if (urlOrName.isEmpty()) {
            this.urlOrNameFromFXML = null;
        } else if (urlOrName.contains("/")) {
            this.urlOrNameFromFXML = urlOrName;
        } else if (urlOrName.contains(".")) {
            this.urlOrNameFromFXML = "/view/" + urlOrName;
        } else {
            this.urlOrNameFromFXML = "/view/" + urlOrName + ".fxml";
        }
    }

    /**
     * Faz as verificações inicias antes de começar a contrução da Classe
     * ControlStage.
     *
     * @throws Exception Caso não seja passado uma classe de Controller, ou
     * problemas relacionados com o FXML.
     */
    private void verificacaoInicial() throws Exception {
        if (this.controller == null) {
            throw new Exception("Não foi encontrada a Classe de Controller! Por Favor use a função addClassController() e adicione uma instancia da sua classe de controller");
        }
        if (this.stage == null) {
            this.stage = new Stage();
        }
        if (this.urlOrNameFromFXML == null && this.fXMLLoader == null) {
            this.fXMLLoader = new FXMLLoader(getClass().getResource("/jeanderson/view/DefaultView.fxml"));
        } else if (this.fXMLLoader == null) {
            this.fXMLLoader = new FXMLLoader(getClass().getResource(this.urlOrNameFromFXML));
        }
        if (this.parentRoot == null) {
            this.parentRoot = this.fXMLLoader.load();
        }
        if (this.scene == null) {
            this.scene = new Scene(this.parentRoot);
        }
        if (this.stageTitle == null) {
            this.stageTitle = "Sem Titulo";
        }
        if (this.urlFromIcon == null) {
            this.urlFromIcon = "/jeanderson/view/img/java-icon-default.png";
        }
    }

    /**
     * Faz todas as configurações do Stage e carrega a classe de Controller,
     * ultima etapa antes da contrução da Classe ControlStage.
     *
     * @see ControlStage
     * @throws Exception Várias exceções.
     */
    private void configuracoesIniciais() throws Exception {
        this.verificacaoInicial();

        this.stage.setTitle(this.stageTitle);
        this.stage.setFullScreen(this.showFullScreen);
        this.stage.setMaximized(this.showMaximized);
        this.stage.setResizable(this.isResizable);
        this.stage.setScene(this.scene);
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream(this.urlFromIcon)));

        this.controller = this.fXMLLoader.getController();

    }

    /**
     * Define que a classe construida será mantida atraves de enum (estatico),
     * possibilitando chamar a classe construida de qualquer Classe em tempo de
     * execução (runtime). É necessário informa o index em que a classe ficará
     * mantida para eventuais consultas, recomendado começar pelo index 0,1...
     *
     * @param index Posição em que ficará armazenada a classe. recomendado
     * começar do index 0.
     * @return retorna um ControlStageBuilder
     * @see AllSee
     */
    public ControlStageBuilder defineAllSee(int index) {
        this.useAllSee = true;
        this.indexForAllSee = index;
        return this;
    }

}
