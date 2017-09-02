# A Classe ControlWindow
Basicamente está é a classe que terá as funções referentes ao controle da sua Janela e de sua Classe de Controller.
## Veja alguns métodos que ela oferece.
**getWindow()** Método retorna a Classe **ControlBuilder** que é a responsável pela construção da classe ControlWindow internamente, é atráves deste método que você derá os seguintes métodos:
**getController()** Retorna a **sua** classe de Controller
**getConfigurator()** Retorna a classe **Configurator** aquela que você viu no método 2, mesmo utilizando o método 3, foi utilizada internamente a classe **Configurator** para a configuração
da sua janela.
**getParentRoot()** Retorna o Root que foi utilizado na construção da sua Janela.

#### a vários outros métodos mais estes citados acimas são os principais pois são os mais utilizados
Veja eles em uso:

```java
ControlWindow<JanelaController> janela = WindowBuilder.construct(JanelaController.class);
        janela.show();
        //pegando o builder
        ControlBuilder<JanelaController> builder = janela.getWindow();
        //pegando sua classe de controller
        JanelaController controller = janela.getWindow().getController();
        //pegando seu parent root
        Parent root = janela.getWindow().getParentRoot();
```

