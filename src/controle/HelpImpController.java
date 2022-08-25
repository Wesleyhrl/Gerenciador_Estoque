package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import visao.App;

public class HelpImpController {

    @FXML
    private Button OK;

    @FXML
    void actionOK(ActionEvent event) {
        App.closeHelpStage();
    }

}
