package todomanager.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import todomanager.data.Task;
import todomanager.utilities.DateConverter;
import todomanager.utilities.Serializer;
import todomanager.utilities.TaskDataValidator;
import todomanager.utilities.ViewUtilities;

public class TasksController {

  @FXML
  private ListView<Task> tasks_TaskList;

  @FXML
  private Button tasks_AddTaskButton;

  @FXML
  private Button tasks_EditButton;

  @FXML
  private Button tasks_DetailButton;

  @FXML
  private Button tasks_DeleteButton;

  @FXML
  private Button tasks_ExitButton;

  private ArrayList<Task> taskArrayList = null;
  private ObservableList<Task> observableTaskList = null;
  private int indexOfSelection = -1;

  @FXML
  public void initialize() {
    this.taskArrayList = Serializer.deserialize(Serializer.taskdatabasepath);
    if (this.taskArrayList == null) {
      this.taskArrayList = new ArrayList<>();
    }
    this.observableTaskList = FXCollections.observableArrayList(taskArrayList);
    this.tasks_TaskList.setItems(observableTaskList);
  }

  @FXML
  public void tasks_AddTaskButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Opening Task Editor");
    System.out.println("=".repeat(40));
    this.OpenTaskEditorViewFrame();
  }

  @FXML
  public void tasks_DeleteButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Deleting Task......");
    if (indexOfSelection != -1) {
      this.tasks_TaskList.getItems().remove(indexOfSelection);
      this.taskArrayList.remove(indexOfSelection);
      // this.observableTaskList.remove(indexOfSelection);
      boolean binaryFileSaveSuccessful = Serializer.serializeData(
        Serializer.taskdatabasepath,
        this.taskArrayList
      );
      this.tasks_TaskList.refresh();

      if (binaryFileSaveSuccessful) {
        System.out.println("Task Deleted");
      } else {
        System.out.println("Task Not Deleted");
      }
    } else {
      System.out.println("Task not selected");
    }
    System.out.println("=".repeat(40));
    indexOfSelection = -1;
  }

  @FXML
  public void tasks_DetailButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Opening Task Detail View");
    if (indexOfSelection != -1) {
      Task savedTask =
        this.tasks_TaskList.getItems().get(this.indexOfSelection);

      this.openDetailViewFrame(savedTask);

      System.out.println("Detail view opened");
    } else {
      System.out.println("Task not selected");
    }
    System.out.println("=".repeat(40));
    indexOfSelection = -1;
  }

  @FXML
  public void tasks_EditButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Opening Task Editor");
    // this.indexOfSelection = this.tasks_TaskList.getSelectionModel().getSelectedIndex();

    if (indexOfSelection != -1) {
      Task savedTask =
        this.tasks_TaskList.getItems().get(this.indexOfSelection);

      this.OpenTaskEditorViewFrame(savedTask);
      System.out.println("Task Editor Opened");
      System.out.println("=".repeat(40));
    } else {
      System.out.println("Task not selected");
      System.out.println("=".repeat(40));
    }
  }

  @FXML
  public void tasks_ExitButtonClicked(ActionEvent event) {
    System.out.println("\n" + "=".repeat(40));
    System.out.println("Exiting Program");
    System.out.println("GoodBye");
    System.out.println("=".repeat(40));
    Platform.exit();
  }

  @FXML
  public void tasks_TaskListClicked(MouseEvent event) {
    this.indexOfSelection =
      this.tasks_TaskList.getSelectionModel().getSelectedIndex();
  }

  public void openDetailViewFrame(Task savedTask) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/todomanager/scene/detailViewFrame.fxml")
      );
      Parent root = (Parent) fxmlLoader.load();
      Scene detailScene = new Scene(root);
      Stage detailStage = new Stage();

      DetailViewController detailView = fxmlLoader.getController();

      detailView.setTaskInfo(savedTask);

      detailStage.setResizable(false);
      detailStage.setTitle("Task in Detail");
      detailStage.setScene(detailScene);
      detailStage.sizeToScene();
      detailStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void OpenTaskEditorViewFrame() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/todomanager/scene/taskEditorFrame.fxml")
      );
      Parent root = (Parent) fxmlLoader.load();
      Scene taskEditorScene = new Scene(root);
      Stage taskEditorStage = new Stage();
      TaskEditorController taskEditor = fxmlLoader.getController();

      taskEditorStage.setResizable(false);
      taskEditorStage.setTitle("Task Editor");
      taskEditorStage.setScene(taskEditorScene);
      taskEditorStage.sizeToScene();
      taskEditorStage.show();
      taskEditor.editTask_SaveButton.setOnAction(
        event -> {
          System.out.println("\n" + "=".repeat(40));
          System.out.println("Saving Task");

          String taskName = taskEditor.getEditTask_TaskName();
          LocalDate localDate_DueDate = taskEditor.getEditTask_TaskDate();
          String personName = taskEditor.getEditTask_PersonName();
          String taskDescShort = taskEditor.getEditTask_TaskDescShort();
          String taskDescLong = taskEditor.getEditTask_TaskDescLong();
          String pathToTaskImage = taskEditor.getImagePath();

          try {
            boolean isValid = TaskDataValidator.isTaskDataValid(
              taskName,
              localDate_DueDate,
              personName,
              taskDescShort,
              taskDescLong,
              pathToTaskImage
            );
            Date dueDate = DateConverter.convertLocalDateToDate(
              localDate_DueDate
            );

            Task userTask = new Task(
              taskName,
              dueDate,
              personName,
              taskDescShort,
              taskDescLong,
              pathToTaskImage
            );
            this.taskArrayList.add(userTask);
            this.observableTaskList.add(userTask);
            boolean binaryFileSaveSuccessful = Serializer.serializeData(
              Serializer.taskdatabasepath,
              this.taskArrayList
            );
            if (!binaryFileSaveSuccessful) {
              throw new Exception("Could not Save to file");
            }

            System.out.println("Closing Task Editor");
            System.out.println("=".repeat(40));
            taskEditorStage.close();
          } catch (Exception exception) {
            // System.out.println("\n" + exception.getMessage());
            Stage currentStage = (Stage) this.tasks_EditButton.getScene()
              .getWindow();
            ViewUtilities.displayErrorMessageDialogue(
              exception.getMessage(),
              currentStage
            );
          }
        }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void OpenTaskEditorViewFrame(Task savedTask) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/todomanager/scene/taskEditorFrame.fxml")
      );
      Parent root = (Parent) fxmlLoader.load();
      Scene taskEditorScene = new Scene(root);
      Stage taskEditorStage = new Stage();
      TaskEditorController taskEditor = fxmlLoader.getController();

      taskEditor.setEditTask_TaskName(savedTask.getTaskName());
      taskEditor.setEditTask_TaskDate(
        DateConverter.convertDateToLocalDate(savedTask.getDueDate())
      );
      taskEditor.setEditTask_PersonName(savedTask.getPersonName());
      taskEditor.setEditTask_TaskDescShort(savedTask.getTaskDescShort());
      taskEditor.setEditTask_TaskDescLong(savedTask.getTaskDescLong());
      taskEditor.setImagePath(savedTask.getPathToTaskImage());
      taskEditor.updateTaskImage();

      taskEditorStage.setResizable(false);
      taskEditorStage.setTitle("Task Editor");
      taskEditorStage.setScene(taskEditorScene);
      taskEditorStage.sizeToScene();
      taskEditorStage.show();
      taskEditor.editTask_SaveButton.setOnAction(
        event -> {
          System.out.println("\n" + "=".repeat(40));
          System.out.println("Saving Task");

          String taskName = taskEditor.getEditTask_TaskName();
          LocalDate localDate_DueDate = taskEditor.getEditTask_TaskDate();
          String personName = taskEditor.getEditTask_PersonName();
          String taskDescShort = taskEditor.getEditTask_TaskDescShort();
          String taskDescLong = taskEditor.getEditTask_TaskDescLong();
          String pathToTaskImage = taskEditor.getImagePath();

          try {
            boolean isValid = TaskDataValidator.isTaskDataValid(
              taskName,
              localDate_DueDate,
              personName,
              taskDescShort,
              taskDescLong,
              pathToTaskImage
            );
            Date dueDate = DateConverter.convertLocalDateToDate(
              localDate_DueDate
            );

            Task userTask = new Task(
              taskName,
              dueDate,
              personName,
              taskDescShort,
              taskDescLong,
              pathToTaskImage
            );
            this.taskArrayList.set(this.indexOfSelection, userTask);
            this.observableTaskList.set(this.indexOfSelection, userTask);
            this.tasks_TaskList.refresh();
            indexOfSelection = -1;

            boolean binaryFileSaveSuccessful = Serializer.serializeData(
              Serializer.taskdatabasepath,
              this.taskArrayList
            );
            if (!binaryFileSaveSuccessful) {
              throw new Exception("Could not Save to file");
            }

            System.out.println("Closing Task Editor");
            System.out.println("=".repeat(40));
            taskEditorStage.close();
          } catch (Exception exception) {
            // System.out.println("\n"+exception.getMessage());
            Stage currentStage = (Stage) this.tasks_EditButton.getScene()
              .getWindow();
            ViewUtilities.displayErrorMessageDialogue(
              exception.getMessage(),
              currentStage
            );
          }
        }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
