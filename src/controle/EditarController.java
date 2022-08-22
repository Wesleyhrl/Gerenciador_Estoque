package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Produto;
import visao.App;

public class EditarController implements Initializable {

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSalvar;

    @FXML
    private  TextField txtCodigo;

    @FXML
    private  TextField txtData;

    @FXML
    private  TextField txtDescricao;

    @FXML
    private  TextField txtGrupo;

    @FXML
    private  TextField txtNome ;

    @FXML
    private  TextField txtQtde;

    @FXML
    private  TextField txtValor;

    @FXML
    void actionEditExcluir(ActionEvent event) {
        NewProdutoController.produtos.remover(MainController.produtoSelect);
        MainController.preencherTab(NewProdutoController.produtos.getProdutos());
        App.closeSecondary();
    }

    @FXML
    void actionEditSalvar(ActionEvent event) {

        System.out.println(txtCodigo.getText());
        Produto pNovo = new Produto(txtNome.getText(), this.txtCodigo.getText(), Integer.parseInt(txtQtde.getText()),
                txtGrupo.getText(), Double.parseDouble(txtValor.getText()), txtDescricao.getText(), txtData.getText());
        NewProdutoController.produtos.remover(MainController.produtoSelect);
        NewProdutoController.produtos.gravar(pNovo);
        MainController.preencherTab(NewProdutoController.produtos.getProdutos());
        App.closeSecondary();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNome.setText(MainController.produtoSelect.getNome());
        txtCodigo.setText(MainController.produtoSelect.getCodigo());
        txtGrupo.setText(MainController.produtoSelect.getGrupo());
        txtQtde.setText(String.valueOf(MainController.produtoSelect.getQuantidade()) );
        txtValor.setText(String.valueOf(MainController.produtoSelect.getValor()));
        txtData.setText(MainController.produtoSelect.getData());
        txtDescricao.setText(MainController.produtoSelect.getDescricao());
        
    }

    
    

    
     

    

}
