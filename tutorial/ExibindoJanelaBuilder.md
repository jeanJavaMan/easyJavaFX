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
Recomendo informa o tipo que a Classe ControlWindow que vai trabalhar, estes tipos é as classe de Controller da Janela.
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
>É o objeto controladorDaJanela que foi criado?


