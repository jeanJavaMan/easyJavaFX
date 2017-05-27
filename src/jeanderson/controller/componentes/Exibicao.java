/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

/**
 * Interface de exibição de Telas.
 * @author Jeanderson
 */
public interface Exibicao {
    
    public void show();
    
    public void show(Class<? extends Inicializador> windowReference);
}
