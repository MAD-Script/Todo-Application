package todomanager.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/todomanager/scene/tasksFrame.fxml")
      );
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root);

      primaryStage.setResizable(false);
      primaryStage.setTitle("Todo Application");
      primaryStage.setScene(scene);
      primaryStage.sizeToScene();
      primaryStage.show();
      primaryStage.setOnCloseRequest(event -> Platform.exit());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println(
      "     ███████████              █████               █████████                      \n    ░█░░░███░░░█             ░░███               ███░░░░░███                     \n    ░   ░███  ░   ██████   ███████   ██████     ░███    ░███  ████████  ████████ \n        ░███     ███░░███ ███░░███  ███░░███    ░███████████ ░░███░░███░░███░░███\n        ░███    ░███ ░███░███ ░███ ░███ ░███    ░███░░░░░███  ░███ ░███ ░███ ░███\n        ░███    ░███ ░███░███ ░███ ░███ ░███    ░███    ░███  ░███ ░███ ░███ ░███\n        █████   ░░██████ ░░████████░░██████     █████   █████ ░███████  ░███████ \n       ░░░░░     ░░░░░░   ░░░░░░░░  ░░░░░░     ░░░░░   ░░░░░  ░███░░░   ░███░░░  \n                                                              ░███      ░███     \n                                                              █████     █████    \n                                                             ░░░░░     ░░░░░     "
    );
    launch(args);
  }
}
