/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

/**
 * Classe para auxilo interno da classes filhas.
 * @deprecated Seu uso não é mais necessário.
 * @author Jeanderson
 */
public class AuxIntern {
    private boolean showStage;
    private boolean enableCampos;
    private boolean correctShowForEnableCampos;
    
    private boolean autoClearCampos = true;
    private boolean autoEnableCampos = false;

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

    /**
     * Verifica de o ClearCampos está ativado.
     * Obs: por padrão é ativado
     * @return boolean informando se está ativado ou não.
     */
    public boolean isAutoClearCampos() {
        return autoClearCampos;
    }

    /**
     * Ativa e Desativa a função ClearCampos.     
     * @param autoClearCampos boolean informando se ativa ou não.
     */
    public void setAutoClearCampos(boolean autoClearCampos) {
        this.autoClearCampos = autoClearCampos;
    }

    /**
     * Verifica se o EnableCampos está ativado.
     * Obs: é desativado por padrão.
     * @return boolean informando situação (Ativado ou Desativado).
     */
    public boolean isAutoEnableCampos() {
        return autoEnableCampos;
    }

    /**
     * Ativa ou Desativa a chamada da função EnableCampos.
     * @param autoEnableCampos boolean informando se ativa ou não.
     */
    public void setAutoEnableCampos(boolean autoEnableCampos) {
        this.autoEnableCampos = autoEnableCampos;
    }
    
    
}
