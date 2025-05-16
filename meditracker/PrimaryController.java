import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class PrimaryController {
    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        AplikasiUtama.setRoot("secondary");
    }
}