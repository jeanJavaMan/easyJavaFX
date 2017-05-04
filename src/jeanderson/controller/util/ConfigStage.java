package jeanderson.controller.util;

import javafx.stage.Stage;

/**
 * Classe que prepara as configurações básicas do Stage.
 *
 * @author Jeanderson
 */
public class ConfigStage {

    private Stage palco;
    private String urlFromFXML;
    private String urlFromIcon;
    private String titleStage;
    private boolean isMaximized = false;
    private boolean isResizable = true;

    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param palco - Stage
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     */
    public ConfigStage(Stage palco, String urlOrName) {
        this.palco = palco;
        this.verificaUrlFromFXML(urlOrName);
    }

    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param palco - Stage
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     * @param titleStage - Titulo da Janela.
     */
    public ConfigStage(Stage palco, String urlOrName, String titleStage) {
        this.palco = palco;
        this.verificaUrlFromFXML(urlOrName);
        this.titleStage = titleStage;
    }

    /**
     * Construtor que recebe as configurações básicas.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param palco - Stage
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     * @param titleStage - Titulo da Janela.
     * @param urlFromIcon - URL do icone da Janela.
     */
    public ConfigStage(Stage palco, String urlOrName, String titleStage, String urlFromIcon) {
        this.palco = palco;
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
     * @param palco - Stage
     * @param urlOrName - Nome do arquivo FXML ou a URL dele.
     * @param titleStage - Titulo da Janela.
     * @param urlFromIcon - URL do icone da Janela.
     * @param maximized - Se inicia Maximizado.
     * @param resizable - Se é redimensionável.
     */
    public ConfigStage(Stage palco, String urlOrName, String titleStage, String urlFromIcon, boolean maximized, boolean resizable) {
        this.palco = palco;
        this.verificaUrlFromFXML(urlOrName);
        this.titleStage = titleStage;
        this.urlFromIcon = urlFromIcon;
        this.isMaximized = maximized;
        this.isResizable = resizable;
    }

    /**
     * Retorna o Stage Padrão
     * @return 
     */
    public Stage getPalco() {
        return palco;
    }

    /**
     * Altera o Stage passado.
     * @param palco 
     */
    public void setPalco(Stage palco) {
        this.palco = palco;
    }

    /**
     * Retorna a URL passada.
     * @return 
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
     * @param urlOrName 
     */
    public void setUrlFromFXML(String urlOrName) {
        this.verificaUrlFromFXML(urlOrName);
    }

    /**
     *  Retorna a URL do Icone.
     * @return 
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
     * @return 
     */
    public String getTitleStage() {
        return titleStage;
    }

    /**
     * Altera o Titulo.
     * @param titleStage 
     */
    public void setTitleStage(String titleStage) {
        this.titleStage = titleStage;
    }

    /**
     * Informa se é maximizavel.
     * @return 
     */
    public boolean isIsMaximized() {
        return isMaximized;
    }

    /**
     * informa se é para maximizar ou não.
     * @param isMaximized 
     */
    public void setIsMaximized(boolean isMaximized) {
        this.isMaximized = isMaximized;
    }

    /**
     * Informa se é redimensionavel.
     * @return 
     */
    public boolean isIsResizable() {
        return isResizable;
    }

    /**
     * Altera se é redimensional sim ou não.
     * @param isResizable 
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
        if (urlOrName.contains("/") && urlOrName.contains(".")) {
            this.urlFromFXML = urlOrName;
        } else if (urlOrName.contains(".") && !urlOrName.contains("/")) {
            this.urlFromFXML = "/view/" + urlOrName;
        } else {
            this.urlFromFXML = "/view/" + urlOrName + ".fxml";
        }
    }

}
