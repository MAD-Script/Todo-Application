package todomanager.data;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

  private String taskName;
  private Date dueDate;
  private String personName;
  private String taskDescShort;
  private String taskDescLong;
  private String pathToTaskImage;

  public Task(
    String taskName,
    Date dueDate,
    String personName,
    String taskDescShort,
    String taskDescLong,
    String pathToTaskImage
  ) {
    this.taskName = taskName;
    this.dueDate = dueDate;
    this.personName = personName;
    this.taskDescShort = taskDescShort;
    this.taskDescLong = taskDescLong;
    this.pathToTaskImage = pathToTaskImage;
  }

  public Task() {}

  public String getTaskName() {
    return this.taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public Date getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getPersonName() {
    return this.personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public String getTaskDescShort() {
    return this.taskDescShort;
  }

  public void setTaskDescShort(String taskDescShort) {
    this.taskDescShort = taskDescShort;
  }

  public String getTaskDescLong() {
    return this.taskDescLong;
  }

  public void setTaskDescLong(String taskDescLong) {
    this.taskDescLong = taskDescLong;
  }

  public String getPathToTaskImage() {
    return this.pathToTaskImage;
  }

  public void setPathToTaskImage(String pathToTaskImage) {
    this.pathToTaskImage = pathToTaskImage;
  }

  public String toString() {
    return this.personName + ": " + this.taskName + "; " + this.taskDescShort;
  }
}
