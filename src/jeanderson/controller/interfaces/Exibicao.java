/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.interfaces;

import jeanderson.controller.control.ControlWindow;

/**
 * Interface de exibição de Telas.
 *
 * @author Jeanderson
 */
public interface Exibicao {

    public void show();

    public void show(ControlWindow windowReference);

    public void showEnableFields(boolean enableFields);

    public void showEnableFields(boolean enableFields, ControlWindow windowReference);

    public void showEditMode(Object data);

    public void showEditMode(Object data, ControlWindow windowReference);

    public void showEditAndEnable(Object data, boolean enableFields);

    public void showEditAndEnable(Object data, boolean enableFields, ControlWindow windowReference);

    public void showSecurityMode(boolean hasAccess, boolean showMessage);

    public void showSecurityMode(boolean hasAccess, boolean showMessage, ControlWindow windowReference);

    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data);

    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data, ControlWindow windowReference);

    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data);

    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, ControlWindow windowReference);

    public void showModality();

    public void showModality(ControlWindow windowReference);

    public void showEnableFieldsModality(boolean enableFields);

    public void showEnableFieldsModality(boolean enableFields, ControlWindow windowReference);

    public void showEditModeModality(Object data);

    public void showEditModeModality(Object data, ControlWindow windowReference);

    public void showEditAndEnableModality(Object data, boolean enableFields);

    public void showEditAndEnableModality(Object data, boolean enableFields, ControlWindow windowReference);

    public void showSecurityModeModality(boolean hasAccess, boolean showMessage);

    public void showSecurityModeModality(boolean hasAccess, boolean showMessage, ControlWindow windowReference);

    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data);

    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data, ControlWindow windowReference);

    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data);

    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, ControlWindow windowReference);

    public void close();
}
