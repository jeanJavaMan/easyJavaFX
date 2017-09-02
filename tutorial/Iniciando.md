# Iniciando... 1º Passos
Todos os tutoriais serão mostrado de maneira rápida e sem enrolação, apenas para ver como se deve proceder ao utilizar a biblioteca e suas funções.
## Vamos lá..
Antes de tudo, como em toda aplicação JavaFX, sua classe que possui o Main deve estender a **Application** conforme a baixo:
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
### Crie uma Janela FXML, recomendo utilizar o Scene Builder para isto, e depois crie a sua classe de Controller.
Para quem já utiliza EasyJavaFX a sua classe fica assim:

```java
public class JanelaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
```
Para trabalhar com a biblioteca JavaFX, você deve fazer a seguinte modificação:

```java
public class JanelaController extends Inicializador {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
```

Você deve estender a **Inicializador** para que funcione a biblioteca. Então vamos ao 1º dos 3 métodos para construção e exibição da Janela.

## 1º Método Builder

```java
ControlWindow<JanelaController> janela = ControlWindow.prepareBuilder("/Janela.fxml")
                  .addTitle("Titulo da Janela")
                  .addUrlFromIcon("/icons/meuIcone.png")
                  .resizable(false).build();
//faz a exibição da Janela.
janela.show();
```

Classe **ControlWindow** possui todas as funções necessária para você trabalha com sua Janela. Irei explicar um pouco melhor sobre ela mais a frente.

## 2º Método Sobrescrevendo o método defineConfiguration na classe de Controller

A diferença do método 1, e que você vai informar todas as configurações na sua propria classe de controller, sobrescrevendo o método **defineConfiguration** e você deverá retornar
um objeto da Classe **Configurator**, pois está classe é a que contém as configurações da sua Janela. Observer a Baixo:

```java
public class JanelaController extends Inicializador {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public Configurator defineConfigurationWindow() {
        return new Configurator() {
            @Override
            public String url_Fxml() {
                return "/Janela.fxml";
            }

            @Override
            public String title() {
                return "Titulo da Janela";
            }
            
        };
    }
}
```
**Obs: agora o importante** utilizando essa maneira, você deve construir o objeto da Classe **ControlWindow** da seguinte maneira:

```java
ControlWindow<JanelaController> janela = WindowBuilder.construct(new JanelaController());
        janela.show();
```
Você deve utilizar a Classe chamada **WindowBuilder** para construir o objeto, diferente do método 1, e você deve utilizar o método construct e nele instanciar um objeto da **sua classe de controller**

## 3º Método usando anotação @DefineConfiguration (RECOMENDADO)

Este é a maneira mais simples é a mais recomendada que deve se utilizar. Você vai utilizar anotação **@DefineConfiguration** para definir as configurações! veja:

```java
@DefineConfiguration(URL_FXML = "/Janela.fxml", title = "Titulo da Janela")
public class JanelaController extends Inicializador {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
```
Veja que o código fica bem menor e fácil entendimento, é para instanciar um objeto da classe **ControlWindow** você deve fazer desta maneira neste caso:

```java
ControlWindow<JanelaController> janela = WindowBuilder.construct(JanelaController.class);
        janela.show();
```
Diferente do método 2, neste caso aqui, você não vai passar uma Instancia da sua classe de controller, mas sim passar sua Classe como parâmetro.
## Observação: Para fechar uma janela basta fazer a seguinte maneira.

```java
 ControlWindow<JanelaController> janela = WindowBuilder.construct(JanelaController.class);
        janela.show();
        //fecha a janela
        janela.close();
```

[Próximo: A Classe ControlWindow](https://github.com/jeanJavaMan/easyJavaFX/blob/master/tutorial/ClasseControlWindow.md)

