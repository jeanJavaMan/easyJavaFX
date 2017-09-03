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
import jeanderson.controller.componentes.EasyJavaFX;
import jeanderson.controller.componentes.Inicializador;

/**
 * Ao utilizar está anotação um instancia da classe ControlWindow será instanciada automáticamente, baseada na configuração
 * presente na anotação @DefineConfiguration na sua classe de Controller.
 * Para inicializar objetos com essa anotação é necessário utilizar o método inicializarComponentes() da classe EasyJavaFX.
 * @see EasyJavaFX
 * @author jeand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInstance {
    public Class<? extends Inicializador> classController();
}
