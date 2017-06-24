/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.interfaces;

import javafx.stage.Stage;

/**
 * Interface de exibição de Telas.
 *
 * @author Jeanderson
 */
public interface Exibicao {

    public void show();

    public void show(Stage windowReference);

    public void showEnableFields(boolean enableFields);

    public void showEnableFields(boolean enableFields, Stage windowReference);

    public void showEditMode(Object data);

    public void showEditMode(Object data, Stage windowReference);

    public void showEditAndEnable(Object data, boolean enableFields);

    public void showEditAndEnable(Object data, boolean enableFields, Stage windowReference);

    public void showSecurityMode(boolean hasAccess, boolean showMessage);

    public void showSecurityMode(boolean hasAccess, boolean showMessage, Stage windowReference);

    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data);

    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data, Stage windowReference);

    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data);

    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Stage windowReference);

    public void showModality();

    public void showModality(Stage windowReference);

    public void showEnableFieldsModality(boolean enableFields);

    public void showEnableFieldsModality(boolean enableFields, Stage windowReference);

    public void showEditModeModality(Object data);

    public void showEditModeModality(Object data, Stage windowReference);

    public void showEditAndEnableModality(Object data, boolean enableFields);

    public void showEditAndEnableModality(Object data, boolean enableFields, Stage windowReference);

    public void showSecurityModeModality(boolean hasAccess, boolean showMessage);

    public void showSecurityModeModality(boolean hasAccess, boolean showMessage, Stage windowReference);

    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data);

    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data, Stage windowReference);

    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data);

    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Stage windowReference);

    public void close();
}
