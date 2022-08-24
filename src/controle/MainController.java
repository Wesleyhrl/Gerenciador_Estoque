package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.zip.DataFormatException;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    
    @FXML
    private GridPane pnProdutos;

    @FXML
    private GridPane pnRelatorio;
    
    @FXML
    private PieChart chartTotalProdutos;
    
    
    
    @FXML
    private CategoryAxis categoryValor = new CategoryAxis();

    @FXML
    private NumberAxis numValor = new NumberAxis();
    @FXML
    private BarChart<String, Number> chartValorProduto = new BarChart<String,Number>(categoryValor, numValor);

    private static ObservableList<Produto> obsListprodutos = FXCollections.observableArrayList();

    static Produto produtoSelect;

    FilteredList<Produto> filteredList = new FilteredList<>(obsListprodutos, p -> true);

    private static XYChart.Series<String,Number> series = new XYChart.Series<>();

    private static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    
    
    @FXML
    void actionImpExp(ActionEvent event) {

    }

    @FXML
    void actionInicio(ActionEvent event) {
        pnProdutos.toFront();
    }

    @FXML
    void actionNovo(ActionEvent event) {
        
        App.cenaNewProduto();
        
    }
    @FXML
    void actionRelatorio(ActionEvent event) {
        
        
        
        
        
        
        pnRelatorio.toFront();   
    }

    @FXML
    void actionSair(ActionEvent event) {
        App.cenaLogin();
        Notifications.create()
        .position(Pos.TOP_CENTER)
        .title("Easy Stock")
        .text("Você Saiu!!!")
        .showInformation();
    
    }

    @FXML
    void keyBuscar(KeyEvent event) {
        Platform.runLater(()->{
            txtBusca.textProperty().addListener((observable,oldValue,newValue) ->{ 
                filteredList.setPredicate((Predicate<? super Produto>)(Produto p) ->{ 
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }else if(p.getNome().contains(newValue)){
                        return true;
                    }else if(p.getCodigo().contains(newValue)){
                        return true;
                    }else if(p.getGrupo().contains(newValue)){
                        return true;
                    }else if(p.getData().contains(newValue)){
                        return true;
                    }
            return false;
             });

            });
            
            SortedList<Produto> sort = new SortedList<>(filteredList);
            sort.comparatorProperty().bind(tabProdutos.comparatorProperty());
            tabProdutos.setItems(sort);

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            NewProdutoController.produtos.lerArq();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        MainController.preencherTab(NewProdutoController.produtos.getProdutos());
        

        tabProdutos.setItems(obsListprodutos);
        tabProdutos.setOnMouseClicked((MouseEvent click) -> {
            if (click.getClickCount() == 2) {
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

        //Gráficos
        chartValorProduto.getData().add(series);

        chartTotalProdutos.setData(pieChartData);
        
        
        
    }

    static void preencherTab(List<Produto> ListaProduto) {
        obsListprodutos.clear();
        series.getData().clear();
        pieChartData.clear();

        series.setName("Valor");
        for (Produto produto : ListaProduto) {
            obsListprodutos.addAll(produto);
            //Gráfico Barras
            series.getData().add(new XYChart.Data<>(produto.getNome(), (Double)(produto.getValor()* produto.getQuantidade())));
            //Gráfico Setores
            String nomeProduto = (produto.getNome() + " : " + produto.getQuantidade());
            System.out.println(nomeProduto);
            pieChartData.add(new PieChart.Data(nomeProduto, produto.getQuantidade()));
            
        }
        
        
        

    }

    private void selectingProduto() throws IOException {
        Produto p = tabProdutos.getSelectionModel().getSelectedItem();
        if (p != null) {
            produtoSelect = p;
            App.cenaEditProduto();

        }

    }
}
