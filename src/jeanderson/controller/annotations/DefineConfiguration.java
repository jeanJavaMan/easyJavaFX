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

/**
 * Interface usada para definir quais são as configurações iniciais da janela.
 * @author jeand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefineConfiguration {
    public String URL_FXML() default "/jeanderson/view/DefaultView.fxml";
    public String title() default "Minha Janela";
    public String URL_ICON() default "/jeanderson/view/img/javafx-logo.png";
    public boolean FULLSCREEN() default false;
    public boolean RESIZABLE() default true;
    public boolean MAXIMIZED() default false;
    public boolean AUTO_NEW_STAGE() default true;
    public boolean STATIC_CLASS() default false;
}
