package jeanderson.controller.util;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Classe responsável pela formatação de campos em TextField e DatePicker.
 *
 * @author Jeanderson
 */
public class MaskFormatter {
    
    public MaskFormatter() {
    }
    
    public void addComponentes(TextField txtField, MaskType masktype, boolean showMask) {
        this.preparaMascara(txtField, masktype, showMask);
    }
    
    public void addComponentes(DatePicker datePicker, MaskType maskType, boolean showMask) {
        this.preparaMascara(datePicker, maskType, showMask);
    }
    
    private void preparaMascara(TextField txtField, MaskType maskType, boolean showMask) {
        switch (maskType) {
            case TEL_DIG:
                this.setTypeTelDig(txtField);
                break;
            case CPF_DIG:
                this.setTypeCPFdig(txtField);
                break;
            case RG_DIG:
                this.setTypeRGdig(txtField);
                break;
            case NUMBER_ONLY:
                this.setTypeNumberOnly(txtField);
                break;
            default:
                System.out.println("Nenhum tipo de máscara informada! Por favor informa o tipo da máscara usando o enum MaskType");
        }
        this.showMask(txtField, maskType, showMask);
    }
    
    private void preparaMascara(DatePicker datePicker, MaskType maskType, boolean showMask) {
        switch (maskType) {
            case DATA_BARRA_DIG:
                this.setTypeDataBarra(datePicker);
                break;
            case DATA_TRACO_DIG:
                this.setTypeDataTraco(datePicker);
                break;
            default:
                System.out.println("Nenhum tipo de máscara informada! Por favor informa o tipo da máscara usando o enum MaskType");
        }
        this.showMask(datePicker, maskType, showMask);
    }
    
    private void showMask(TextField txtField, MaskType maskType, boolean showMask) {
        if (showMask) {
            switch (maskType) {
                case TEL_DIG:
                    txtField.setPromptText("(__) ____-_____");
                    break;
                case CPF_DIG:
                    txtField.setPromptText("___.___.___-__");
                    break;
                case RG_DIG:
                    txtField.setPromptText("_.___.___-_");
                    break;
			default:
				break;
            }
        }
    }
    
    private void showMask(DatePicker datePicker, MaskType maskType, boolean showMask) {
        if (showMask) {
            switch (maskType) {
                case DATA_BARRA_DIG:
                    datePicker.setPromptText("__/__/____");
                    break;
                case DATA_TRACO_DIG:
                    datePicker.setPromptText("__-__-____");
                    break;
			default:
				break;
            }
        }
    }

    /**
     * Chama um evento que é executado quando usuário digita alguma coisa no
     * campo, mudando caracteres de lugar para formar o padrão do tipo Telefone.
     *
     * @see MaskType
     * @param textField
     */
    private void setTypeTelDig(TextField textField) {
        textField.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
            if (evento.getCharacter().trim().length() == 0) {
                switch (textField.getText().length()) {
                    case 1:
                        textField.setText("");
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 3:
                        textField.setText(textField.getText().substring(0, 2));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 9:
                        textField.setText(textField.getText().substring(0, 8));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 14:
                        String semTraco = textField.getText().replace("-", "");
                        String novoTraco = semTraco.substring(0, 9) + "-" + semTraco.substring(9, 13);
                        textField.setText(novoTraco);
                        textField.positionCaret(textField.getText().length());
                        break;
                }
            } else if (textField.getText().length() == 15) {
                evento.consume();
            } else {
                switch (textField.getText().length()) {
                    case 1:
                        textField.setText("(" + textField.getText());
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 3:
                        textField.setText(textField.getText() + ") ");
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 9:
                        textField.setText(textField.getText() + "-");
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 14:
                        String semTraco = textField.getText().replace("-", "") + evento.getCharacter();
                        String novoTraco = semTraco.substring(0, 10) + "-" + semTraco.substring(10, 14);
                        textField.setText(novoTraco.substring(0, 14));
                        textField.positionCaret(textField.getText().length());
                        break;
                }
            }
            
        });
        
    }

    /**
     * Chama um evento que é executado quando usuário digita alguma coisa no
     * campo, mudando caracteres de lugar para formar o padrão do tipo CPF.
     *
     * @see MaskType
     * @param textField
     */
    private void setTypeCPFdig(TextField textField) {
        textField.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
            
            if (evento.getCharacter().trim().length() == 0) {
                
                switch (textField.getText().length()) {
                    case 11:
                        textField.setText(textField.getText().substring(0, 9));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 7:
                        textField.setText(textField.getText().substring(0, 6));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 3:
                        textField.setText(textField.getText().substring(0, 2));
                        textField.positionCaret(textField.getText().length());
                        break;
                }
                
            } else if (textField.getText().length() == 14) {
                evento.consume();
            }
            switch (textField.getText().length()) {
                case 3:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 7:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 11:
                    textField.setText(textField.getText() + "-");
                    textField.positionCaret(textField.getText().length());
                    break;
            }
            
        });
        
    }

    /**
     * Chama um evento que é executado quando usuário digita alguma coisa no
     * campo, mudando caracteres de lugar para formar o padrão do tipo RG.
     *
     * @see MaskType
     * @param textField
     */
    private void setTypeRGdig(TextField textField) {
        textField.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
            
            if (evento.getCharacter().trim().length() == 0) {
                
                switch (textField.getText().length()) {
                    case 2:
                        textField.setText(textField.getText().substring(0, 1));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 6:
                        textField.setText(textField.getText().substring(0, 5));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 10:
                        textField.setText(textField.getText().substring(0, 9));
                        textField.positionCaret(textField.getText().length());
                        break;
                }
                
            } else if (textField.getText().length() == 12) {
                evento.consume();
            }
            switch (textField.getText().length()) {
                case 2:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 6:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 10:
                    textField.setText(textField.getText() + "-");
                    textField.positionCaret(textField.getText().length());
                    break;
            }
            
        });
        
    }

    /**
     * Chama um evento que é executado quando usuário digita alguma coisa no
     * campo, mudando caracteres de lugar para formar o padrão de Data com
     * barra.
     *
     * @see MaskType
     * @param datePicker
     */
    private void setTypeDataBarra(DatePicker datePicker) {
        datePicker.getEditor().setOnKeyTyped((KeyEvent evento) -> {
            
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
            
            if (evento.getCharacter().trim().length() == 0) {
                switch (datePicker.getEditor().getText().length()) {
                    case 2:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 1));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                    case 5:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 4));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                }
            } else if (datePicker.getEditor().getText().length() == 10) {
                evento.consume();
            }
            switch (datePicker.getEditor().getText().length()) {
                case 2:
                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
                case 5:
                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
            }
            
        });
        
    }

    /**
     * Chama um evento que é executado quando usuário digita alguma coisa no
     * campo, mudando caracteres de lugar para formar o padrão de Data com
     * traco.
     *
     * @see MaskType
     * @param datePicker
     */
    private void setTypeDataTraco(DatePicker datePicker) {
        datePicker.getEditor().setOnKeyTyped((KeyEvent evento) -> {
            
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
            
            if (evento.getCharacter().trim().length() == 0) {
                switch (datePicker.getEditor().getText().length()) {
                    case 2:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 1));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                    case 5:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 4));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                }
            } else if (datePicker.getEditor().getText().length() == 10) {
                evento.consume();
            }
            switch (datePicker.getEditor().getText().length()) {
                case 2:
                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "-");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
                case 5:
                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "-");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
            }
            
        });
    }
    /**
     * Chama um evento que permite somente a entrada de números.
     * @param textField 
     */
    private void setTypeNumberOnly(TextField textField){
        textField.setOnKeyTyped(evento ->{
            if(!"0123456789".contains(evento.getCharacter())){
                evento.consume();
            }
        
        });
    }
}
