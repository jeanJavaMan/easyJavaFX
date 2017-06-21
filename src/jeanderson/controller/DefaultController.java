/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller;

import java.net.URL;
import java.util.ResourceBundle;
import jeanderson.controller.componentes.Inicializador;

/**
 * Classe de controller sem implementação. Somente é utilizada quando o usuário não passa uma classe de controller proprio.
 * @author Jeanderson
 */
public class DefaultController extends Inicializador{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.err.println("Não foi passado uma classe de Controller! Utilizando uma classe de Controller Default.");
    }
    
}
