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
**Obs:** _Não esqueça de atribuir sua classe de controller ao seu arquivo FXML._
## 2º Passo:
Agora já podemos fazer a chamada da Tela, utilizaremos uma Classe chamada **ControlWindow** do pacote `jeanderson.controller.control`
 que será responsável pelo controle da Janela, como Titulo, Stage, Scene entre outros.
Recomendo informa o tipo que a Classe ControlWindow  vai trabalhar, estes tipos é as classe de Controller da Janela.
```java
ControlWindow<JanelaController> controladorDaJanela;
```
Utilizando o design patterns Builder vamos construir nosso ControlWindow referente a Janela com as configurações que queremos que a janela tenha. Sabendo disso
vamos ao código que faz a construção é exibição da Janela:
```java
ControlWindow<JanelaController> controladorDaJanela = ControlWindow.prepareBuilder("/view/Janela.fxml")
                .addTitle("Minha primeira Janela")
                .build();
        controladorDaJanela.show();
```
Explicações: Utilizamos o método estático chamado `prepareBuilder()` que retorna um Objeto da classe **ControlBuider** que é responsável pela construção do Objeto da classe
**ControlWindow**, passamos como parâmetro no prepareBuilder a **URL do arquivo FXML** que deve ser carregado, após isto adicionamos um titulo atráves do método
`addTitle()` e para finalizar a construção do Objeto ControlWindow utilizamos o método `build()` que é responsável para finalizar a construção do objeto. 
Com a devida construção feita, chamamos a Tela atráves do método show().
Está é a maneira mais simples de chamar uma Tela existe outros método que podem ser usados na construção exemplo, caso vc queira informa um icone para janela, ou então informa
que a janela deve ser aberta em modo fullscreen, entre outros.
###### E o objeto controladorDaJanela que foi criado?
Este objeto que foi criado da Classe ControlWindow possui todas as funções referente a Janela como Stage,Palco,FXMLLoader entre outros, além disso ele possui vários métodos
de exibição de janela, como em `showModality()` onde a Janela requisita Atenção e não deixa clicar em outra Tela e vários outros métodos de exibição que serão abordados os principais mais a frente.

## Observação importante:
Toda vez que você fazer a exibição da Janela utilizando o método `show()` e semelhantes um novo Stage é instanciado para permitir o dinâmismo da Exibição, como exemplo abrir a Janela
em hora em modo `Modality.APPLICATION_MODAL` em outra hora sem ser Modal ou então abrir tendo uma Janela Pai. Mas caso você não queria que um novo Stage seja instanciado, existe
um método chamado `autoNewStage()` que no parâmetro você informa se deseja ou não deixar que seja instanciado um novo Stage. Este método pertence a Classe **ControlBuilder**
que é usado para construir o Objeto da Classe **ControlWindow**. Veja um exemplo onde desativamos o autoNewStage:
```java
ControlWindow<HomeController> controladorDaJanela = ControlWindow.prepareBuilder("/view/Janela.fxml")
                .addTitle("Minha primeira Janela")
                //aqui desativamos o instanciamento de um novo Stage.
                .autoNewStage(false)
                .build();
```
## Final:
Então é isso galera, espero que tenha gostado desse primeiro tutorial, no próximo vou ensinar o método **construct** que é outra maneira de construir um **ControlWindow**
assim você pode escolher o método que mais lhe agrada. E quero deixar uma Observação, nunca se esquece q sua aplicação tem que ter uma Classe que é estendida a **Application**
pois é uma aplicação JavaFX, esta biblioteca não deixa de fazer necessario o uso de **Application** Ex:
```java
public class Executor extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
```
Então é isso galera, até o próximo tutorial.
