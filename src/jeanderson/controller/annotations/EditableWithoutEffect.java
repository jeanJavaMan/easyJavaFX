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
import jeanderson.controller.util.FunctionAnnotations;

/**
 * Informa que a função EditableFields não tem efeito sobre o componente.
 * @author jeanderson
 * @see FunctionAnnotations
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EditableWithoutEffect {
    
}
