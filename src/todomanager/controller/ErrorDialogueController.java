package todomanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorDialogueController {

  @FXML // fx:id="err_ErrorDialogue"
  private Label err_ErrorDialogue; // Value injected by FXMLLoader

  @FXML // fx:id="err_OkButton"
  private Button err_OkButton; // Value injected by FXMLLoader

  @FXML
  void err_OkButton(ActionEvent event) {
    Stage curreStage = (Stage) err_OkButton.getScene().getWindow();
    curreStage.close();
  }

  public void displayError(String errMessage) {
    this.err_ErrorDialogue.setText(errMessage);
  }
}
