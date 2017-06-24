/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.enums;

/**
 *
 * @author Jeanderson
 * @deprecated Não será mais utilizado.
 */
@Deprecated
public enum ClearType {
    /**
     * Limpa todos os componentes que vem do pacote control do JavaFX como TextField, ComboBox, DatePicker...
     */
    CLEAR_ALL,
    /**
     * Informa quais componentes devem ser limpos atráves da Anotação @ClearField.
     * @see ClearField.
     */
    CHOOSE_FIELD;
}
