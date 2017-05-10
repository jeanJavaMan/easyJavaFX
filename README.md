# EasyJavaFX
Biblioteca em desenvolvimento que busca facilitar a vida de desenvolvedores iniciantes, ou até mesmo aqueles experientes que
queira trabalhar com JavaFX de maneira mais rápida e eficiente. No inicio já tive vários problemas com a organização e além do mais
não havia percebido que toda a vez chamava o FXMLLoader para carregar o arquivo fxml toda a vez que abria a Tela, até que o dia o
sistema ficou lento.
###### Resumindo:
Essa biblioteca vai te ajudar muito no desenvolvimento e vai evitar algumas dores de cabeça, e também vai agilizar mais o desenvolvimento
mas, chega de falar! Vamos mostrar como dar o start.

## 1º Passo
Recomendo muito que siga o padrão MVC, se não conhece ainda [Clique aqui e veja um tutorial da Caelum](https://www.caelum.com.br/apostila-java-web/mvc-model-view-controller/#9-12-model-view-controller)
Para cada **view** temos uma classe que é o seu controlador(controller), essa classe deve estender a classe **Inicializador** do pacote:
`jeanderson.controller.componentes`
```java
public class TelaHomeController extends Inicializador{}
```
