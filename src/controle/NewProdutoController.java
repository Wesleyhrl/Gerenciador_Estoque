package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.ListaProduto;
import modelo.Produto;

public class NewProdutoController {

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtGrupo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtQtde;

    @FXML
    private TextField txtValor;

    static ListaProduto produtos = new ListaProduto();

    @FXML
    void actionNewSalvar(ActionEvent event) {
        
        Produto p;
        try {
            p = new Produto(txtNome.getText(), txtCodigo.getText(), Integer.parseInt(txtQtde.getText()),
                    txtGrupo.getText(), Double.parseDouble(txtValor.getText()), txtDescricao.getText(), txtData.getText());
                    produtos.gravar(p);
        } catch (NullPointerException e){
            Notifications.create()
            .position(Pos.CENTER)
            .title("Easy Stock")
            .text("Campo NOME é obrigatório o preenchimento.\nDigite um nome.")
            .showError();
        }
        catch (NumberFormatException e) {
            Notifications.create()
            .position(Pos.CENTER)
            .title("Easy Stock")
            .text("Erro no campo VALOR ou QUANTIDADE.\nDigite um número 0 ou maior.")
            .showError();
            
            
        } catch (DataFormatException e) {
            Notifications.create()
            .position(Pos.CENTER)
            .title("Easy Stock")
            .text("Erro no formato da data.\nDigite no formato DD/MM/AA. ")
            .showError();
            
        }
        MainController.preencherTab(produtos.getProdutos());
        
    }
 /* 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            produtos.lerArq();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MainController.preencherTab(produtos.getProdutos());
        
    }
 */
}
