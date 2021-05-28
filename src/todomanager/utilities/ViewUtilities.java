package todomanager.utilities;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todomanager.controller.ErrorDialogueController;

public class ViewUtilities {

  public static void displayErrorMessageDialogue(
    String errMessage,
    Stage parentStage
  ) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
        ViewUtilities.class.getResource(
            "/todomanager/scene/errorDialogueFrame.fxml"
          )
      );
      Pane root = (Pane) fxmlLoader.load();

      ErrorDialogueController errController = fxmlLoader.getController();
      errController.displayError(errMessage);

      Scene errorDialogueScene = new Scene(root);
      Stage errorDialogueStage = new Stage();

      errorDialogueStage.setScene(errorDialogueScene);
      errorDialogueStage.initOwner(parentStage);
      errorDialogueStage.initModality(Modality.APPLICATION_MODAL);
      errorDialogueStage.setTitle("Error");
      errorDialogueStage.showAndWait();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
