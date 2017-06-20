# Exibindo Janela, 2º Método: Construct
Como havia falando existe dois tipos de métodos para a construção de um Objeto da Classe **ControlWindow** referente a Janela, já apresentei o método builder, agora
vamos para a segunda forma de fazer essa construção chamada de **Construct**
##### Diferença do Builder:
No builder nós configuramos nossa janela atráves dos métodos existentes na Classe **ControlBuilder** que é responsável pela construção do objeto da Classe **ControlWindow**,
no builder fazemos da seguinte maneira:
```java
ControlWindow<JanelaController> controladorDaJanela = ControlWindow.prepareBuilder("/view/Janela.fxml")
                .addTitle("Minha primeira Janela")
                .addUrlFromIcon("/icons/meuIcone.png")
                .fullScreen(true)
                .resizable(false)
                .build();
```
Veja que utilizando o método builder para construção, utilizamos os métodos para definir o objeto `controladorDaJanela`. Agora utilizando o método **Construct** vamos fazer básicamente
a mesma coisa, só que de uma maneira diferente. Utilizaremos a Classe de **Controller** referente a Janela para definir estas configurações.

Utilizando o método **Builder** para a construção do nosso objeto da classe **ControlWindow** a nossa classe de controller da Janela fica desta forma:
```java
public class JanelaController extends Inicializador {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }    
}
```
Veja que não é necessário implementar nenhuma outra coisa para que ela funcione no método **builder**, pois definimos as configurações utilizando os métodos das classe **ControlBuilder**
,mas agora que vamos utilizar o método **Construct** para fazer a construção do objeto da classe **ControlWindow** será nossa classe de controller que vai definir as configurações. 

## 1º Passo:
Devemos sobreescrever um método da **Classe Pai Inicializador** este método se chama `defineConfigurationWindow()`
```java
public class JanelaController extends Inicializador {
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @Override
    public Configurator defineConfigurationWindow() {
        Configurator configuracao = new Configurator() {
            @Override
            public String url_Fxml() {
                return "/view/Janela.fxml";
            }
        };
        configuracao.setTitle("Minha primeira Janela");
        configuracao.setUrl_icon("/icons/meuIcone.png");
        configuracao.setFullscreen(true);
        configuracao.setResizable(false);
        
        return configuracao;
    }
    
}

```

**Explicações:** o método `defineConfigurationWindow()` retorna um objeto da Classe **Configurator** do pacote `jeanderson.controller.componentes`, ao utilizar o método **builder** para
a construção do objeto da Classe **ControlWindow** o objeto da classe **Configurator** vocês não tem acesso direto a ele, mas utilizando o método **Construct** já é necessário
vocês utilizarem diretamente. O código por si só já é auto explicativo, estamos fazendo a mesma coisa que faziamos no **Builder** só que agora as configurações ficam na classe de 
controller referente a Janela.
## 2º Passo:

##### Mas ai você me pergunta, cadê o objeto do ControlWindow?
Bom como agora a configuração fica na nossa classe de controller referente a Janela, para construimos um objeto da classe **ControlWindow** fica mais fácil. Faremos isso da
seguinte maneira:
```java
ControlWindow<JanelaController> controladorDaJanela = WindowBuilder.construct(new JanelaController());
```
**Explicações:** vamos então as explicações para você entender o que aconteceu. Agora que as configurações estão na classe de controller referente a sua Janela, vamos utilizar
uma classe chamada **WindowBuilder** do pacote `jeanderson.controller.componentes` que é responsável por fazer a construção do objeto da classe **ControlWindow** utilizando
as configurações da Classe de controller referente a Janela. O método estático `construct()` recebe uma instancia da classe de controller que tem as configurações, e com base nessas
configurações será construido o objeto da classe **ControlWindow**. 

**Obs:** está objeto que é passado como parâmetro só é utilizado para pegar as configurações, a classe de controller propriamente dita estara toda pronta no objeto construido da 
classe **ControlWindow**, para pegar-lá é necessário fazer desta maneira:
```java
ControlWindow<JanelaController> controladorDaJanela = WindowBuilder.construct(new JanelaController());
        //pegando a classe de controller referente a janela.
        JanelaController classeDeController = controladorDaJanela.getWindow().getController();
```
Mas isto será explicado mais para frente.
## Final:
Bom galera terminamos o tutorial referente a construção do objeto da classe **ControlWindow**, veja que tem as duas forma de construir o **builder** é o **construct**, 
você escolhe a maneira que mais curtir para construir um objeto. No próximo tutorial irei falar sobre o método `show()` e outros semelhantes a ele. Então até a próxima.

[Anterior: Exibindo Janela, 1º método: Builder](https://github.com/jeanJavaMan/easyJavaFX/blob/master/tutorial/ExibindoJanelaBuilder.md) [Próximo:]()