package todomanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import todomanager.data.Task;
import todomanager.utilities.DateConverter;

public class DetailViewController {

  @FXML
  private Button detail_CloseButton;

  @FXML
  private Label detail_TaskName;

  @FXML
  private Label detail_DueDate;

  @FXML
  private Label detail_PersonName;

  @FXML
  private Label detail_TaskDescShort;

  @FXML
  private Label detail_TaskDescLong;

  @FXML
  private ImageView detail_TaskImage;

  private String imagePath = null;

  @FXML
  void detail_CloseButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Closing Task Detail View");
    System.out.println("=".repeat(40));
    Stage detailStage = (Stage) detail_CloseButton.getScene().getWindow();
    detailStage.close();
  }

  public void setTaskInfo(Task task) {
    this.detail_TaskName.setText(task.getTaskName());
    this.detail_DueDate.setText(
        DateConverter.convertDateToLocalDate(task.getDueDate()).toString()
      );
    this.detail_PersonName.setText(task.getPersonName());
    this.detail_TaskDescShort.setText(task.getTaskDescShort());
    this.detail_TaskDescLong.setText(task.getTaskDescLong());
    this.imagePath = task.getPathToTaskImage();
    this.updateTaskImage();
  }

  public void updateTaskImage() {
    Image taskImage = new Image("file://" + this.imagePath);
    this.detail_TaskImage.setImage(taskImage);
  }
}
