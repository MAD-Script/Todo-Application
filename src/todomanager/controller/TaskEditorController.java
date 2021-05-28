package todomanager.controller;

import java.io.File;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TaskEditorController {

  @FXML
  private Button editTask_CancelButton;

  @FXML
  public Button editTask_SaveButton;

  @FXML
  private TextField editTask_TaskName;

  @FXML
  private DatePicker editTask_TaskDate;

  @FXML
  private TextField editTask_PersonName;

  @FXML
  private TextField editTask_TaskDescShort;

  @FXML
  private TextArea editTask_TaskDescLong;

  @FXML
  private ImageView editTask_TaskImage;

  @FXML
  private Button editTask_SelectImage;

  private String imagePath = null;

  public String getEditTask_TaskName() {
    return this.editTask_TaskName.getText();
  }

  public void setEditTask_TaskName(String editTask_TaskName) {
    this.editTask_TaskName.setText(editTask_TaskName);
  }

  public LocalDate getEditTask_TaskDate() {
    return this.editTask_TaskDate.getValue();
  }

  public void setEditTask_TaskDate(LocalDate editTask_TaskDate) {
    this.editTask_TaskDate.setValue(editTask_TaskDate);
  }

  public String getEditTask_PersonName() {
    return this.editTask_PersonName.getText();
  }

  public void setEditTask_PersonName(String editTask_PersonName) {
    this.editTask_PersonName.setText(editTask_PersonName);
  }

  public String getEditTask_TaskDescShort() {
    return this.editTask_TaskDescShort.getText();
  }

  public void setEditTask_TaskDescShort(String editTask_TaskDescShort) {
    this.editTask_TaskDescShort.setText(editTask_TaskDescShort);
  }

  public String getEditTask_TaskDescLong() {
    return this.editTask_TaskDescLong.getText();
  }

  public void setEditTask_TaskDescLong(String editTask_TaskDescLong) {
    this.editTask_TaskDescLong.setText(editTask_TaskDescLong);
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public void exitEditor() {
    System.out.println("Closing Task Editor");
    System.out.println("=".repeat(40));
    Stage editStage = (Stage) editTask_SaveButton.getScene().getWindow();
    editStage.close();
  }

  @FXML
  public void editTask_CancelButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Canceling Edits");
    System.out.println("Closing Task Editor");
    System.out.println("=".repeat(40));
    Stage editStage = (Stage) editTask_CancelButton.getScene().getWindow();
    editStage.close();
  }

  @FXML
  public void editTask_SelectImageClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Selecting Image");

    FileChooser fileChooser = new FileChooser();
    Stage fileStage = (Stage) this.editTask_SelectImage.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(fileStage);

    if (selectedFile != null) {
      System.out.println("Image Selected");
      String selectedFilePath = selectedFile.toURI().getPath();
      this.imagePath = selectedFilePath;
      System.out.println(selectedFilePath);

      Image taskImage = new Image("file://" + this.imagePath);
      this.editTask_TaskImage.setImage(taskImage);
    }
    System.out.println("=".repeat(40));
  }

  public void updateTaskImage() {
    Image taskImage = new Image("file://" + this.imagePath);
    this.editTask_TaskImage.setImage(taskImage);
  }
}
