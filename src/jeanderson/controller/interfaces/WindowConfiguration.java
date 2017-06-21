/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.interfaces;

/**
 * Interface para uso de classes que precisam destas configurações para
 * construir uma Janela.
 *
 * @author Jeanderson
 */
public interface WindowConfiguration {

    /**
     * Titulo da Janela.
     *
     * @return Titulo da Janela.
     */
    public String title();

    /**
     * URL do arquivo FXML referente a Janela.
     *
     * @return URL do FXML.
     */
    public String url_Fxml();

    /**
     * URL do icone referente a Janela.
     *
     * @return URL do Icone.
     */
    public String url_Icon();

    /**
     * Se a Janela é rendimensionavel.
     *
     * @return é rendimensionavel.
     */
    public boolean isResizable();

    /**
     * Se é exibida em FULLSCREEN.
     *
     * @return exibida em fullscreen.
     */
    public boolean isFullscreen();

    /**
     * Se é exibida maximizada.
     *
     * @return exibida maximizada.
     */
    public boolean isMaximized();
}
