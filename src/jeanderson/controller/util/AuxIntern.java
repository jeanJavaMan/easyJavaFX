/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

/**
 * Classe para auxilo interno da classes filhas.
 * @author Jeanderson
 */
public class AuxIntern {
    private boolean showStage;
    private boolean enableCampos;
    private boolean correctShowForEnableCampos;

    /**
     * Construtor padrão.
     */
    public AuxIntern() {
    }

    /**
     * Informa se a Tela já foi carregada alguma vez.
     * @return - Boolean
     */
    public boolean isShowStage() {
        return showStage;
    }

    /**
     * Altera informando se a Tela foi aberta ou não.
     * @param showStage - Boolean
     */
    public void setShowStage(boolean showStage) {
        this.showStage = showStage;
    }

    /**
     * Informa o que o usuario passou no método enableCampos.
     * @return - Boolean
     */
    public boolean isEnableCampos() {
        return enableCampos;
    }

    /**
     * Faz alteração de acordo com o que o usuário passou.
     * @param enableCampos - Boolean
     */
    public void setEnableCampos(boolean enableCampos) {
        this.enableCampos = enableCampos;
    }

    /**
     * Verifica se o método Show é correto para o funcionando do EnableCampos.
     * @return - Boolean 
     */
    public boolean isCorrectShowForEnableCampos() {
        return correctShowForEnableCampos;
    }

    /**
     * Informa se o método show é ideal para enableCampos ou não.
     * @param correctShowForEnableCampos - Boolean
     */
    public void setCorrectShowForEnableCampos(boolean correctShowForEnableCampos) {
        this.correctShowForEnableCampos = correctShowForEnableCampos;
    }    
    
}
