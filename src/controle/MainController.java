package controle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import modelo.ArqIncorrectException;
import modelo.ListaProduto;
import modelo.NomeRepeatException;
import modelo.Produto;
import visao.App;

public class MainController implements Initializable {
    @FXML
    private Button btnExpSalvar;

    @FXML
    private Button btnImpLer;

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
    private TextField txtExp;

    @FXML
    private TextField txtImp;

    @FXML
    private GridPane pnProdutos;

    @FXML
    private GridPane pnRelatorio;

    @FXML
    private GridPane pnExpImp;

    @FXML
    private ImageView folderExp;

    @FXML
    private ImageView folderImport;

    @FXML
    private PieChart chartTotalProdutos;

    @FXML
    private CategoryAxis categoryValor = new CategoryAxis();

    @FXML
    private NumberAxis numValor = new NumberAxis();

    @FXML
    private BarChart<String, Number> chartValorProduto = new BarChart<String, Number>(categoryValor, numValor);

    //
    public static ListaProduto produtos = new ListaProduto();

    private static ObservableList<Produto> obsListprodutos = FXCollections.observableArrayList();

    static Produto produtoSelect;

    FilteredList<Produto> filteredList = new FilteredList<>(obsListprodutos, p -> true);

    private static XYChart.Series<String, Number> series = new XYChart.Series<>();

    private static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    //

    @FXML
    void actionImpExp(ActionEvent event) {
        pnExpImp.toFront();
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
        Platform.runLater(() -> {
            txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Produto>) (Produto p) -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    } else if (p.getNome().contains(newValue)) {
                        return true;
                    } else if (p.getCodigo().contains(newValue)) {
                        return true;
                    } else if (p.getGrupo().contains(newValue)) {
                        return true;
                    } else if (p.getData().contains(newValue)) {
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

    @FXML
    void actionExpSalvar(ActionEvent event) {
        if (!txtExp.getText().isBlank()) {

            try {
                produtos.salvarArq(txtExp.getText());
                Notifications.create()
                        .position(Pos.CENTER)
                        .title("Easy Stock")
                        .text("O arquivo "+ txtExp.getText()+" foi salvo no seu computador!!!")
                        .showInformation();
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @FXML
    void actionImpLer(ActionEvent event) {
        

        if (!txtImp.getText().isBlank()) {

            try {
                produtos.lerArq(txtImp.getText());
                produtos.ordenar();
                produtos.salvarArq("memory.txt");
                Notifications.create()
                        .position(Pos.CENTER)
                        .title("Easy Stock")
                        .text("O arquivo "+ txtImp.getText()+" externo foi salvo no programa!!!")
                        .showInformation();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ArqIncorrectException | NullPointerException | NumberFormatException | DataFormatException e) {
                Notifications.create()
                        .position(Pos.CENTER)
                        .title("Easy Stock")
                        .text("Arquivo Incorreto.\nSelecione um arquivo nos padrões do programa.")
                        .showError();
            }catch (NomeRepeatException e){
                produtos.ordenar();
                try {
                    produtos.salvarArq("memory.txt");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Notifications.create()
                        .position(Pos.CENTER)
                        .title("Easy Stock")
                        .text("Campo NOME não pode ser repetido.\nProdutos salvos apenas até nome não repetido")
                        .showError();
            }

            MainController.preencherTab(produtos.getProdutos());
        }
    }

    @FXML
    void onFolderExp(MouseEvent event) {

        FileChooser fc = new FileChooser();
        fc.setTitle("Escolha o Local para Salvar");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fc.setInitialFileName("ListaProdutos");
        File file = fc.showSaveDialog(null);
        if (file != null)
            txtExp.setText(file.getAbsolutePath());
    }

    @FXML
    void onFolderImport(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecione o Arquivo");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fc.showOpenDialog(null);
        if (file != null)
            txtImp.setText(file.getAbsolutePath());
    }
    @FXML
    void actionHelp(ActionEvent event) throws IOException {
        App.cenaHelpImp();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            produtos.lerArq("memory.txt");
        } catch (IOException | DataFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }catch (NomeRepeatException e){
            Notifications.create()
                    .position(Pos.CENTER)
                    .title("Easy Stock")
                    .text("Campo NOME não pode ser repetido.")
                    .showError();
        }

        MainController.preencherTab(produtos.getProdutos());

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

        // Gráficos
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
            // Gráfico Barras
            series.getData().add(
                    new XYChart.Data<>(produto.getNome(), (Double) (produto.getValor() * produto.getQuantidade())));
            // Gráfico Setores
            String nomeProduto = (produto.getNome() + " : " + produto.getQuantidade());
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
