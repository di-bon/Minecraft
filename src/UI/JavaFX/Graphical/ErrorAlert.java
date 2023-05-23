package UI.JavaFX.Graphical;

import javafx.scene.control.Alert;

public class ErrorAlert extends Alert {
    public ErrorAlert(String title, String content) {
        super(AlertType.ERROR);
        this.setTitle(title);
        this.setContentText(content);
        this.showAndWait();
    }
}
