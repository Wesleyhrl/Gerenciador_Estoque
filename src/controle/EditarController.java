package controle;

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
import modelo.NomeRepeatException;
import modelo.Produto;
import visao.App;

public class EditarController implements Initializable {

    @FXML
    private Button btnExcluir;

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

    @FXML
    void actionEditExcluir(ActionEvent event) {
        MainController.produtos.remover(MainController.produtoSelect);
        MainController.preencherTab(MainController.produtos.getProdutos());
        App.closeSecondary();
    }

    @FXML
    void actionEditSalvar(ActionEvent event) {

        Produto pNovo;
        try {
            MainController.produtos.remover(MainController.produtoSelect);
            pNovo = new Produto(txtNome.getText(), this.txtCodigo.getText(), Integer.parseInt(txtQtde.getText()),
                    txtGrupo.getText(), Double.parseDouble(txtValor.getText()), txtDescricao.getText(),
                    txtData.getText());
            

            MainController.produtos.gravar(pNovo);

            MainController.preencherTab(MainController.produtos.getProdutos());
            App.closeSecondary();
        } catch (NullPointerException e) {
            Notifications.create()
                    .position(Pos.CENTER)
                    .title("Easy Stock")
                    .text("Campo NOME é obrigatório o preenchimento.\nDigite um nome.")
                    .showError();
        } catch (NumberFormatException e) {
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

        }catch (NomeRepeatException e){
            Notifications.create()
                    .position(Pos.CENTER)
                    .title("Easy Stock")
                    .text("Campo NOME não pode ser repetido.")
                    .showError();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNome.setText(MainController.produtoSelect.getNome());
        txtCodigo.setText(MainController.produtoSelect.getCodigo());
        txtGrupo.setText(MainController.produtoSelect.getGrupo());
        txtQtde.setText(String.valueOf(MainController.produtoSelect.getQuantidade()));
        txtValor.setText(String.valueOf(MainController.produtoSelect.getValor()));
        txtData.setText(MainController.produtoSelect.getData());
        txtDescricao.setText(MainController.produtoSelect.getDescricao());

    }

}
