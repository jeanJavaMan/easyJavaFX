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
 * Anotação para informa que em uma classe deve ser feita limpeza nos componentes da janela.
 * @author Jeanderson
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClearFields {
    /**
     * Qual a maneira de fazer a limpeza.
     * @return Informa qual a maneira de fazer a limpeza dos componentes.
     */
    Clear limpar();
}
