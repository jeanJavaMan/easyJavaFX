package jeanderson.controller.util;

import javafx.stage.Stage;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStageBuilder;

/**
 * Classe que prepara as configurações básicas do Stage.
 *@deprecated Classe não será mais usada para configurações. Será Substituida
 * pela Classe ControlStageBuilder
 * @see ControlStageBuilder
 * @author Jeanderson
 */
public class ConfigStage {

    private Stage palco;
    private String urlFromFXML;
    private String urlFromIcon;
    private String titleStage;
    private boolean autoClearCampos = true;
    private boolean autoEnableCampos = false;
    private boolean showFullScreen  = false;
    private boolean showMaximized = false;
    private boolean isResizable = true;
    
    public static final boolean YES_AUTO_CLEAR = true;
    public static final boolean NO_AUTO_CLEAR = false;
    public static final boolean YES_AUTO_ENABLE = true;
    public static final boolean NO_AUTO_ENABLE = false;
    public static final boolean YES_SHOW_FULLSCREEN = true;
    public static final boolean NO_SHOW_FULLSCREEN = false;
    public static final boolean YES_SHOW_MAXIMIZED = true;
    public static final boolean NO_SHOW_MAXIMIZED = false;
    public static final boolean YES_SHOW_RESIZABLE = true;
    public static final boolean NO_SHOW_RESIZABLE = false;
    /**
     * Construtor Padrão.
     */
    public ConfigStage() {
        this.urlFromIcon = "";
        this.titleStage = "Sem Titulo";
    }    
    
    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     *@param urlOrName - Nome do arquivo FXML ou a URL dele.
     */
    public ConfigStage(String urlOrName) {        
        this.urlFromIcon = "";
        this.titleStage = "Sem Titulo";
        this.verificaUrlFromFXML(urlOrName);
    }

    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     * @param titleStage - Titulo da Janela.
     */
    public ConfigStage(String urlOrName, String titleStage) {
        this.urlFromIcon = "";
        this.titleStage = "Sem Titulo";
        this.verificaUrlFromFXML(urlOrName);
        this.titleStage = titleStage;
    }

    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     * @param titleStage - Titulo da Janela.
     * @param urlFromIcon - URL do icone da Janela.
     */
    public ConfigStage(String urlOrName, String titleStage, String urlFromIcon) {       
        this.urlFromIcon = "";
        this.titleStage = "Sem Titulo";
        this.verificaUrlFromFXML(urlOrName);
        this.titleStage = titleStage;
        this.urlFromIcon = urlFromIcon;
    }
    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     * @param titleStage - Titulo da Janela.
     * @param urlFromIcon - URL do icone da Janela.
     * @param autoClearCampos - Se ao abrir a Tela segunda vez, deve chamar o método clearCampos
     * @param autoEnableCampos - Se deve chamar o método EnableCampos.
     */
    public ConfigStage(String urlOrName, String titleStage, String urlFromIcon, boolean autoClearCampos, boolean autoEnableCampos) {       
        this.urlFromIcon = "";
        this.titleStage = "Sem Titulo";
        this.verificaUrlFromFXML(urlOrName);
        this.titleStage = titleStage;
        this.urlFromIcon = urlFromIcon;
        this.autoClearCampos = autoClearCampos;
        this.autoEnableCampos = autoEnableCampos;
    }

    /**
     * Retorna o Stage, se o Stage é null é criado uma nova instancia.
     * @return - Stage 
     */
    public Stage getPalco() {
        if(this.palco == null) this.palco = new Stage();
        return this.palco;
    }

    /**
     * Altera o Stage passado.
     * @param palco - Stage.
     */
    public void setPalco(Stage palco) {
        this.palco = palco;
    }

    /**
     * Retorna a URL passada.
     * @return - URL do arquivo FXML
     */
    public String getUrlFromFXML() {
        return urlFromFXML;
    }

    /**
     * Altera a URL do arquivo FXML.
     * Na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param urlOrName - URL or Name.
     */
    public void setUrlFromFXML(String urlOrName) {
        this.verificaUrlFromFXML(urlOrName);
    }

    /**
     *  Retorna a URL do Icone.
     * @return - String
     */
    public String getUrlFromIcon() {
        return urlFromIcon;
    }

    /**
     * Altera a URL do Icone.
     * @param urlFromIcon 
     */
    public void setUrlFromIcon(String urlFromIcon) {
        this.urlFromIcon = urlFromIcon;
    }

    /**
     * Retorna o Titulo passado.
     * @return - String
     */
    public String getTitleStage() {
        return titleStage;
    }

    /**
     * Altera o Titulo.
     * @param titleStage - String 
     */
    public void setTitleStage(String titleStage) {
        this.titleStage = titleStage;
    }

    /**
     * Informa se o autoClearCampos está ativado.
     * @see Inicializador
     * @return - Boolean
     */
    public boolean isAutoClearCampos() {
        return autoClearCampos;
    }

    /**
     * Desliga ou liga a chamada do método clearCampos.
     * @see Inicializador
     * @param autoClearCampos - Boolean
     */
    public void setAutoClearCampos(boolean autoClearCampos) {
        this.autoClearCampos = autoClearCampos;
    }

    /**
     * Informa se está ativo a chamada do método enableCampos.
     * @see Inicializador
     * @return - Boolean 
     */
    public boolean isAutoEnableCampos() {
        return autoEnableCampos;
    }

    /**
     * Desliga ou liga a chamada do método enableCampos.
     * @see Inicializador
     * @param autoEnableCampos - Boolean 
     */
    public void setAutoEnableCampos(boolean autoEnableCampos) {
        this.autoEnableCampos = autoEnableCampos;
    }

    /**
     * Informa se é para abrir a tela em FullScreen.
     * @return - Boolean
     */
    public boolean isShowFullScreen() {
        return showFullScreen;
    }

    /**
     * Alterar para ser exibido em fullscreen ou nao.
     * @param showFullScreen - Boolean 
     */
    public void setShowFullScreen(boolean showFullScreen) {
        this.showFullScreen = showFullScreen;
    }

    /**
     * Informa se é para abrir a maximizada.
     * @return - Boolean
     */
    public boolean isShowMaximized() {
        return showMaximized;
    }

    /**
     * Alterar para ser exibido maximizado ou não.
     * @param showMaximized - Boolean
     */
    public void setShowMaximized(boolean showMaximized) {
        this.showMaximized = showMaximized;
    }

    /**
     * Informa se é redimensionável.
     * @return - Boolean 
     */
    public boolean isResizable() {
        return isResizable;
    }

    /**
     * Alterar para ser redimensionável ou não.
     * @param isResizable - Boolean 
     */
    public void setIsResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }
        
    /**
     * Método responsável para verificar se foi digitado somente o nome do
     * arquivo FXML ou se foi passado a URL do arquivo FXML.
     *
     * @param urlOrName
     */
    private void verificaUrlFromFXML(String urlOrName) {
        if (urlOrName.contains("/")) {
            this.urlFromFXML = urlOrName;
        } else if (urlOrName.contains(".")) {
            this.urlFromFXML = "/view/" + urlOrName;
        } else {
            this.urlFromFXML = "/view/" + urlOrName + ".fxml";
        }
    }

}
