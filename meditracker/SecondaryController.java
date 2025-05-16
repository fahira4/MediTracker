import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class SecondaryController {
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException {
        AplikasiUtama.setRoot("primary");
    }
}