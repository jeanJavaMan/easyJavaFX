/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import jeanderson.controller.util.FunctionAnnotations;

/**
 *
 * @author jeand
 */
public class EasyJavaFX {
   public static void inicializarComponentes(Class classe){
       FunctionAnnotations.inicializarControlWindows(classe);
   }
}
