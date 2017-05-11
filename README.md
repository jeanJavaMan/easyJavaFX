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
E você deve implementar o método que é pedido, por que a Classe Inicializador implementa a Interface Initialize do JavaFX.
Após isto, sua classe de controle estará apta para trabalhar, pois ela será passada por parâmetro, para que quando o arquivo fxml seja carregado, esta classe de Controle sejá também carrega.
## 2º Passo
Preparando a exibição da Janela: Utilizaremos uma Classe chamada ControlStage do pacote `jeanderson.controller.control` pois ela é responsável para preparar tudo, como carregar a classe de controle, carregar o arquivo FXML, fazer as configurações básicas do Stage entre outras coisas. Para instanciamos um objeto da Classe ControlStage, vamos utilizar um **design pattern builder**,então utilizando esse design, vamos usar uma Classe auxiliadora no instanciamento do nosso objeto, Veja:
```java
//utilizamos para o instanciamento a Classe ControlStageBuilder
//Informamos que a Classe ControlStage tem como parametro o controller da Tela que é o TelaHomeController
 ControlStage<TelaHomeController> controlHome = new ControlStageBuilder<>()
     //Aqui passamos a nossa Classe de Controle da Tela
     .addClassController(new TelaHomeController())
     //aqui passamos o nome do arquivo FXML que será carregado, podemos passar também sua URL
     .addNameFromFXML("TelaHome")
     //adicionamos um titulo a nossa Tela
     .addTitleStage("Controle de Escola")
     //o build constroi a instancia , retornando um objeto todo configurado da Classe ControlStage.
     .build();
        
     //este método faz a chamada da tela.
     controlHome.show();
```
