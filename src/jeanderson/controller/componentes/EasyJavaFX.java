/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import jeanderson.controller.annotations.AutoInstance;
import jeanderson.controller.annotations.DefineConfiguration;
import jeanderson.controller.util.FunctionAnnotations;

/**
 *
 * @author jeand
 */
public class EasyJavaFX {
   /**
    * Se na classe do objeto passado como parâmetro tiver um objeto da classe ControlWindow com anotação
    * AutoInstance então será instanciado automáticamente baseado na anotação DefineConfiguration de sua classe de controller.
    * @see AutoInstance
    * @see DefineConfiguration
    * @param classe 
    */ 
   public static void inicializarComponentes(Object classe){
       FunctionAnnotations.inicializarControlWindows(classe);
   }
}
