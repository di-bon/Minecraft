package Main;

import UI.JavaFX.Graphical.MainGUI;
import UI.JavaFX.Controllers.MainSimpleController;
import UI.logic.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainSimpleController mainSimpleController = new MainSimpleController(new MainView(true));
        MainGUI mainGUI = mainSimpleController.get_main_gui();

        Scene scene = new Scene(mainGUI, 800, 600);
        primaryStage.setTitle("Minecraft");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}