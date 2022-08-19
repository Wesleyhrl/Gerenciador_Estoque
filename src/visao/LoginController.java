package visao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Login;

public class LoginController {

    @FXML
    private PasswordField inputSenha;

    @FXML
    private TextField inputUser;

    @FXML
    void fazerLogin(ActionEvent event) {
        System.out.println(inputUser.getText());
        Login login = new Login(inputUser.getText(), inputSenha.getText());
    }

}
