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
    
    public void showEnableFields(boolean enableFields);
    
    public void showEnableFields(boolean enableFields,Class<? extends Inicializador> windowReference);
    
    public void showEditMode(Object data);
    
    public void showEditMode(Object data, Class<? extends Inicializador> windowReference);
    
    public void showEditAndEnable(Object data, boolean enableFields);
    
    public void showEditAndEnable(Object data, boolean enableFields, Class<? extends Inicializador> windowReference);
    
    public void showSecurityMode(boolean hasAccess, boolean showMessage);
    
    public void showSecurityMode(boolean hasAccess, boolean showMessage,Class<? extends Inicializador> windowReference);
    
    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data);
    
    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data, Class<? extends Inicializador> windowReference);
    
    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data);
    
    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Class<? extends Inicializador> windowReference);
}
