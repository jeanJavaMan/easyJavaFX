# Exibindo Janela 1º Método: Builder

Neste tutorial vou mostrar como fazer a exibição da Janela, existe duas formas de montar um código para esta exibição que são **Builder** e **Construct.**
 Neste eu irei mostrar o Buider, então sem mais delongas vamos nessa.
 
## 1º Passo:
Este é o passo mais importante de todos para que a biblioteca funcione corretamente. A sua classe de controller referente as Janela devem ser todas estendidas a classe Inicializador do pacote
 `jeanderson.controller.componentes` da seguinte maneira:
```java
public class JanelaController extends Inicializador{}
```
Ao fazer isto sua classe deve implementar o método abstrato Initialize, pois Inicializador implementa a Interface **Initializable** do JavaFX.
**Obs:** _Não esqueça de atribuir sua classe de controller ao seu arquivo FXML_
