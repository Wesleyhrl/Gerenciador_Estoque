package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Login;
import visao.App;

public class LoginController {

    @FXML
    private PasswordField inputSenha;

    @FXML
    private TextField inputUser;

    @FXML
    void fazerLogin(ActionEvent event) {
        Login login = new Login();
        boolean entrada = login.validarLogin(inputUser.getText(), inputSenha.getText());
        if(entrada){
            App.cenaMain();
        }
       
    }

}
