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

    public AuxIntern() {
    }

    public boolean isShowStage() {
        return showStage;
    }

    public void setShowStage(boolean showStage) {
        this.showStage = showStage;
    }

    public boolean isEnableCampos() {
        return enableCampos;
    }

    public void setEnableCampos(boolean enableCampos) {
        this.enableCampos = enableCampos;
    }

    public boolean isCorrectShowForEnableCampos() {
        return correctShowForEnableCampos;
    }

    public void setCorrectShowForEnableCampos(boolean correctShowForEnableCampos) {
        this.correctShowForEnableCampos = correctShowForEnableCampos;
    }    
    
}
