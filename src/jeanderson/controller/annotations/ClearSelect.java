/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jeanderson.controller.enums.Clear;

/**
 * Anotação para informar quais componentes do pacote Control do JavaFX devem ser limpos.
 * @author Jeanderson
 * @deprecated Não será mais utilizado.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Deprecated
public @interface ClearSelect {
    /**
     * Informa se deve limpar o campo ou não.
     * @return Se deve limpar o campo.
     */
    public Clear clear();
}
