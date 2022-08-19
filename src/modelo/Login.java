package modelo;

import javax.management.Notification;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;

public class Login {
    private final String user = "admin";
    private final String password = "admin";
    /**
     * Construtor que valida o login do usuário.
     * @param user
     * @param password
     */
    public Login(String user , String password){
        if(user.equals(this.user) && password.equals(this.password)){
            Notifications.create()
            .position(Pos.CENTER)
            .title("Easy Stock")
            .text("Login efetuado com sucesso")
            .showInformation();
        }else{
            Notifications.create()
            .position(Pos.CENTER)
            .title("Easy Stock")
            .text("Erro no Login \n Digite usuário e senha corretamente.")
            .showError();

        }
    }
    
    
}
