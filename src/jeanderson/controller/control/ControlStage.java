/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jeanderson.controller.componentes.AllSee;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.AuxIntern;

/**
 * Classe responsável por todo o trabalho feito para inicializar uma Tela em
 * JavaFX.
 *
 * @author Jeanderson
 * @param <T> - Informa a Classe do Controller feito pelo usuário.
 */
public class ControlStage<T extends Inicializador> extends AuxIntern {

    private Stage palco;
    private Parent root;
    private Scene cena;
    private FXMLLoader loader;
    private T controller;

    /**
     * Construtor que recebe a Classe ControlStageBuilder já com configurações
     * prontas.
     *
     * @see ControlStageBuilder
     * @param controlBuilder
     */
    public ControlStage(ControlStageBuilder<T> controlBuilder) {
        super();
        this.palco = controlBuilder.getStage();
        this.root = controlBuilder.getParentRoot();
        this.cena = controlBuilder.getScene();
        this.loader = controlBuilder.getfXMLLoader();
        this.controller = controlBuilder.getController();
        super.setAutoClearCampos(controlBuilder.isAutoClearCampos());
        super.setAutoEnableCampos(controlBuilder.isAutoEnableCampos());
    }

    /**
     * Cria uma nova Builder da Classe ControlStageBuilder. Utilizando a ideia
     * do Design Pattern Builder
     *
     * @return - Classe ControlStageBuilder
     * @see ControlStageBuilder
     */
    public static ControlStageBuilder newBuilder() {
        return new ControlStageBuilder();
    }

    /**
     * Cria uma nova Builder da Classe ControlStageBuilder, e recebe um Stage.
     * Utilizando a ideia do Design Pattern Builder
     *
     * @param palco - Stage
     * @see ControlStageBuilder
     * @return - Classe ControlStageBuilder
     */
    public static ControlStageBuilder newBuilder(Stage palco) {
        return new ControlStageBuilder(palco);
    }

    /**
     * Exibi a Tela já pronta. Obs: ao chamar esse método pela segunda vez, ele
     * não recarregara o arquivo FXML por motivo de desempenho, apenas exibira a
     * Tela novamente. Recomendo que classes de controle implementem os métodos
     * clearCampos entre outros. Atenção: Se for passar um Classe de Controle
     * Pai, a mesma deve está definada como Allsee(Todos ver). Caso não utilize
     * o método na contrução do ControlStage chamado defineAllSee. Obs: Se
     * houver alguma Tela Pai, que vc queria que está tela filha tenha
     * dependencia(InitOwner), passa como parâmetro a Classe de Controller da
     * Tela Pai. Caso não houve passe null.
     *
     * @param classControlParent Classe de Controller pai, se não houver passar
     * null no parâmetro.
     * @see Inicializador
     * @throws Exception - Uma cadeia de Excecoes.
     */
    public void show(Class<? extends Inicializador> classControlParent) throws Exception {

        if (!super.isShowStage()) {
            if (classControlParent != null) {
                Stage primaryStage = AllSee.CONTROLADORES.getControlador(classControlParent).getStage();
                this.palco.initOwner(primaryStage);
            }
            super.setCorrectShowForEnableCampos(false);
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Exibi a Tela já pronta e chama o método EnableCampos da Classe de
     * controller. Obs: ao chamar esse método pela segunda vez, ele não
     * recarregara o arquivo FXML por motivo de desempenho, apenas exibira a
     * Tela novamente. Recomendo que classes de controle implementem os métodos
     * clearCampos entre outros. Atenção: Se for passar um Classe de Controle
     * Pai, a mesma deve está definada como Allsee(Todos ver). Caso não utilize
     * o método na contrução do ControlStage chamado defineAllSee. Obs: Se
     * houver alguma Tela Pai, que vc queria que está tela filha tenha
     * dependencia(InitOwner), passa como parâmetro a Classe de Controller da
     * Tela Pai. Caso não houve passe null.
     *
     * @param classControlParent Classe de Controller pai, se não houver passar
     * null no parâmetro.
     * @see Inicializador
     * @param enableCampos - o usuario informa se ativa ou não os campos
     * @throws Exception - Uma cadeia de Excecoes.
     */
    public void show(Class<? extends Inicializador> classControlParent, boolean enableCampos) throws Exception {
        if (!super.isShowStage()) {
            if (classControlParent != null) {
                Stage primaryStage = AllSee.CONTROLADORES.getControlador(classControlParent).getStage();
                this.palco.initOwner(primaryStage);
            }
            super.setCorrectShowForEnableCampos(true);
            super.setEnableCampos(enableCampos);
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.verificaConfiguracoes();
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Exibi a Tela já pronta e chama o método editMode da classe de Controller.
     * Para exibir como tela de edição. Obs: ao chamar esse método pela segunda
     * vez, ele não recarregara o arquivo FXML por motivo de desempenho, apenas
     * exibira a Tela novamente. Recomendo que classes de controle implementem
     * os métodos clearCampos entre outros. Atenção: Se for passar um Classe de
     * Controle Pai, a mesma deve está definada como Allsee(Todos ver). Caso não,
     * utilize o método na contrução do ControlStage chamado defineAllSee. Obs:
     * Se houver alguma Tela Pai, que vc queria que está tela filha tenha
     * dependencia(InitOwner), passar como parâmetro a Classe de Controller da
     * Tela Pai. Caso não houve passe null.
     *
     * @param classControlParent Classe de Controller pai, se não houver passar
     * null no parâmetro.
     * @see Inicializador
     * @param data - dado que será usado pela classe de Controller implemetado
     * pelo usuario.
     * @throws Exception - Uma cadeia de Excecoes.
     */
    public void showEditMode(Class<? extends Inicializador> classControlParent, Object data) throws Exception {

        if (!super.isShowStage()) {
            if (classControlParent != null) {
                Stage primaryStage = AllSee.CONTROLADORES.getControlador(classControlParent).getStage();
                this.palco.initOwner(primaryStage);
            }
            super.setCorrectShowForEnableCampos(false);
            this.verificaConfiguracoes();
            ((Inicializador) this.controller).editMode(data);            
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.verificaConfiguracoes();
            ((Inicializador) this.controller).editMode(data);            
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Exibi a Tela já pronta e chama o método editMode da classe de Controller
     * e chama o método EnableCampos da Classe de controller . Para exibir a
     * tela em modo de edição. Obs: ao chamar esse método pela segunda vez, ele
     * não recarregara o arquivo FXML por motivo de desempenho, apenas exibira a
     * Tela novamente. Recomendo que classes de controle implementem os métodos
     * clearCampos entre outros. Atenção: Se for passar um Classe de Controle
     * Pai, a mesma deve está definada como Allsee(Todos ver). Caso não utilize
     * o método na contrução do ControlStage chamado defineAllSee. Obs: Se
     * houver alguma Tela Pai, que vc queria que está tela filha tenha
     * dependencia(InitOwner), passa como parâmetro a Classe de Controller da
     * Tela Pai. Caso não houve passe null.
     *
     * @param classControlParent Classe de Controller pai, se não houver passar
     * null no parâmetro.
     * @see Inicializador
     * @param data - dado que será usado pela classe de Controller implemetado
     * pelo usuario.
     * @param enablecampos - o usuario informa se ativa ou não os campos
     * @throws Exception - Uma cadeia de Excecoes.
     */
    public void showEditMode(Class<? extends Inicializador> classControlParent, Object data, boolean enablecampos) throws Exception {
        if (!super.isShowStage()) {
            if (classControlParent != null) {
                Stage primaryStage = AllSee.CONTROLADORES.getControlador(classControlParent).getStage();
                this.palco.initOwner(primaryStage);
            }
            super.setCorrectShowForEnableCampos(false);
            this.verificaConfiguracoes();
            super.setEnableCampos(enablecampos);
            ((Inicializador) this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.verificaConfiguracoes();
            ((Inicializador) this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    /**
     * Verifica as configurações de Clearcampos e EnableCampos e faz a execução
     * dos mesmo.
     */
    private void verificaConfiguracoes() {
        if (super.isAutoClearCampos() && super.isShowStage()) {
            ((Inicializador) this.controller).clearCampos();
        }
        if (super.isAutoEnableCampos()) {
            ((Inicializador) this.controller).enableCampos(super.isEnableCampos());
            if (!super.isCorrectShowForEnableCampos()) {
                System.out.println("Você ativou o EnableCampos, mas está chamando a tela"
                        + " utilizando o método show() que não tem como parâmetro enablecampos, por favor utilize um método"
                        + " show que tenha como parâmetro EnableCampos\n para informar atráves dos parâmetros se os campos"
                        + " devem ser ativados ou não!");
            }
        }
    }

    /**
     * Retorna o Stage usado.
     *
     * @return - Classe Stage.
     */
    public Stage getStage() {
        return this.palco;
    }

    /**
     * Troca o Stage por outro.
     *
     * @param palco - Stage.
     */
    public void setStage(Stage palco) {
        this.palco = palco;
    }

    /**
     * Retorna o Parent.
     *
     * @see Parent
     * @return - Parent
     */
    public Parent getParent() {
        return root;
    }

    /**
     * Troca o Parent por outro.
     *
     * @see Parent
     * @param root - Parent
     */
    public void setParent(Parent root) {
        this.root = root;
    }

    /**
     * Retorna a Scene utilizada.
     *
     * @return - Scene
     */
    public Scene getScene() {
        return this.cena;
    }

    /**
     * Troca a Scene por outra.
     *
     * @see Scene
     * @param cena - Scene
     */
    public void setScene(Scene cena) {
        this.cena = cena;
    }

    /**
     * Retorna a Classe do FXMLLoader usado para carregar os arquivos FXML.
     *
     * @see FXMLLoader
     * @return - FXMLLoader
     */
    public FXMLLoader getFXMLLoader() {
        return this.loader;
    }

    /**
     * Troca a Classe FXMLLoader por outra.
     *
     * @see FXMLLoader
     * @param loader - FXMLLoader
     */
    public void setFXMLLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    /**
     * Retorna a Classe de Controller da tela.
     *
     * @see T
     * @return - Uma Classe de Controller
     */
    public T getController() {
        return this.controller;
    }

    /**
     * Troca a Classe de Controller por outra.
     *
     * @see T
     * @param controller - Uma Classe de Controller.
     */
    public void setController(T controller) {
        this.controller = controller;
    }

    /**
     * Define que está classe será mantida atraves de enum (estatico),
     * possibilitando chamar está Classe em qualquer outra Classe em tempo de
     * execução (runtime). É necessário informa o index em que a classe ficará
     * mantida para eventuais consultas, recomendado começar pelo index 0,1...
     *
     * @param classNameIdentity Chave de Identificação: Classe de Controller que
     * será usada como Identificação. Ex: TelaHomeController.class
     * @see AllSee
     */
    public void defineAllSee(Class<? extends Inicializador> classNameIdentity) {
        AllSee.CONTROLADORES.addControlador(classNameIdentity, this);
    }

    /**
     * Define que está classe será mantida atraves de enum (estatico),
     * possibilitando chamar está Classe em qualquer outra Classe em tempo de
     * execução (runtime). É necessário informa o index em que a classe ficará
     * mantida para eventuais consultas, recomendado começar pelo index 0,1...
     * Obs: Será usada a Classe de Controller passada como Parâmetro na Classe,
     * como chave de identificação para usar o método getAllSeeControl. Atenção:
     * Se você alterar a Classe de Controller, este método deverá ser usado após
     * alteração da Classe. E se vc utilizou este método e posteriomente alterou
     * a Classe de Controller, Você perdera a referência para a pesquisa no
     * método getAllSeeControl. Pois é utilizado como chave de identificação a
     * Classe de Controller usada como parâmetro na Classe.
     *
     * @see AllSee
     *
     */
    public void defineAllSee() {
        AllSee.CONTROLADORES.addControlador(this.controller.getClass(), this);
    }

    /**
     * Retorna uma Classe ControlStage salva em um enum (estático). Informa um
     * index onde está armazenada a Classe. Obs: cuidado para não informa um
     * index inválido, pois será lançada uma exceção.
     *
     * @param classNameIdentity Chave de Identificação: Informe a Classe de
     * Controller para pesquisa. Ex: TelaHomeController.
     * @return Uma classe ControlStage armezanada na memoria de forma estática.
     * @throws java.lang.Exception Caso não seja encontrado a Classe Armazenada
     * é lançada uma Exception.
     * @see AllSee
     */
    public static ControlStage getAllSeeControl(Class<? extends Inicializador> classNameIdentity) throws Exception {
        return AllSee.CONTROLADORES.getControlador(classNameIdentity);
    }
}
