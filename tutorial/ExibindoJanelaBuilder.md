#Exibindo Janela 1º Método: Builder

Neste tutorial vou mostrar como fazer a exibição da Janela, existe duas formas de montar um código para esta exibição que são **Builder** e **Construct.**
 Neste eu irei mostrar o Buider, então sem mais delongas vamos nessa.
 
##1º Passo:
Este é o passo mais importante de todos para que a biblioteca funcione corretamente. A sua classe de controller referente as Janela devem ser todas estendidas a classe Inicializador do pacote
 Jeanderson.controller.componentes da seguinte maneira:
```java
public class JanelaController extends Inicializador{}
```
