package visao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.ListaProduto;
import modelo.Produto;

public class NewProdutoController{

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

    ListaProduto produtos = new ListaProduto();

    @FXML
    void actionNewSalvar(ActionEvent event) {
        
        Produto p = new Produto(txtNome.getText(), txtCodigo.getText(), Integer.parseInt(txtQtde.getText()),
                txtGrupo.getText(), Double.parseDouble(txtValor.getText()), txtDescricao.getText(), txtData.getText());
        System.out.println(p.getNome());
        for (Produto elemento : produtos.getProdutos()) {
            System.out.println(elemento.getNome());
        }
        produtos.gravar(p);
        MainController.preencherTab(produtos.getProdutos());

    }

}
