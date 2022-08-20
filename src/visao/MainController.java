package visao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modelo.ListaProduto;
import modelo.Produto;

public class MainController implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnImpExp;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnNovoProduto;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnSair;
    @FXML
    private static TableView<Produto> tab = new TableView<>();

    @FXML
    private TableColumn<Produto, String> colCodigo = new TableColumn<>();

    @FXML
    private TableColumn<Produto, String> colData = new TableColumn<>();

    @FXML
    private TableColumn<Produto, String> colDescricao = new TableColumn<>();

    @FXML
    private TableColumn<Produto, String> colGrupo = new TableColumn<>();

    @FXML
    private TableColumn<Produto, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Produto, Integer> colQtde = new TableColumn<>();

    @FXML
    private TableColumn<Produto, Double> colValor = new TableColumn<>();

    @FXML
    private TextField txtBusca;


    @FXML
    void actionBusca(ActionEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {
        App.cenaEditProduto();
    }

    @FXML
    void actionImpExp(ActionEvent event) {

    }

    @FXML
    void actionInicio(ActionEvent event) {

    }

    @FXML
    void actionNovo(ActionEvent event) {
        App.cenaNewProduto();
    }

    @FXML
    void actionRelatorio(ActionEvent event) {

    }

    @FXML
    void actionSair(ActionEvent event) {

    }

    @FXML
    void keyBuscar(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Inicio");
        colNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("codigo"));
        colQtde.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<Produto, String>("grupo"));
        colValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valor"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        colData.setCellValueFactory(new PropertyValueFactory<Produto, String>("data"));
       
    }

    static void preencherTab(List<Produto> ListaProduto) {
        ObservableList<Produto> obsListprodutos = FXCollections.observableArrayList(ListaProduto);
        tab.setItems(obsListprodutos);

        System.out.println("Tabela");
    }

}
