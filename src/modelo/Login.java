package modelo;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;

public class Login {
    private final String user = "admin";
    private final String password = "admin";
    /**
     * Método que valida o login.
     * @param user
     * @param password
     * @return true or false
     */
    public boolean validarLogin(String user , String password){
        if(user.equals(this.user) && password.equals(this.password)){
            Notifications.create()
            .position(Pos.TOP_CENTER)
            .title("Easy Stock")
            .text("Login efetuado com sucesso.")
            .showInformation();
            return true;
        }else{
            Notifications.create()
            .position(Pos.TOP_CENTER)
            .title("Easy Stock")
            .text("Erro no Login \nDigite usuário e senha corretamente.")
            .showError();
            return false;
        }
    }
    
    
}
