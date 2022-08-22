package controle;

import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import modelo.ListaProduto;
import modelo.Produto;
import visao.App;

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
    private TableView<Produto> tabProdutos = new TableView<>();

    @FXML
    private TableColumn<Produto, String> colCodigo;

    @FXML
    private TableColumn<Produto, String> colData;

    @FXML
    private TableColumn<Produto, String> colDescricao;

    @FXML
    private TableColumn<Produto, String> colGrupo;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private TableColumn<Produto, Integer> colQtde;

    @FXML
    private TableColumn<Produto, Double> colValor;

    @FXML
    private TextField txtBusca;

    private static ObservableList<Produto> obsListprodutos = FXCollections.observableArrayList();

    static Produto produtoSelect; 

    @FXML
    void actionBusca(ActionEvent event) {

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
        tabProdutos.setItems(obsListprodutos);
        tabProdutos.setOnMouseClicked((MouseEvent click) -> {
            if(click.getClickCount() == 2){
                System.out.println("clico");
                try {
                    selectingProduto();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        
                
            
            
        });
        colNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("codigo"));
        colQtde.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<Produto, String>("grupo"));
        colValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valor"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        colData.setCellValueFactory(new PropertyValueFactory<Produto, String>("data"));
        
        
    }
     
    static void preencherTab(List<Produto> ListaProduto) {
        obsListprodutos.clear();
        
        for (Produto produto : ListaProduto) {
            obsListprodutos.addAll(produto);
        }

    }
    private void selectingProduto() throws IOException{
        Produto p = tabProdutos.getSelectionModel().getSelectedItem();
        if(p != null){
            produtoSelect = p;
            App.cenaEditProduto();
            
        }
        
    }
}
