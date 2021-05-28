package todomanager.utilities;

import java.time.LocalDate;
import todomanager.exceptions.*;

public class TaskDataValidator {

  private static final int VALID_TASKNAME_LENGTH = 4;
  private static final int VALID_SHORTDESC_LENGTH = 8;
  private static final int VALID_LONGDESC_LENGTH = 5;
  private static final int VALID_NAME_LENGTH = 3;

  public static boolean isTaskDataValid(
    String taskName,
    LocalDate date,
    String personName,
    String taskDescShort,
    String taskDescLong,
    String imagePath
  )
    throws Exception {
    if (!isTaskNameNotNull(taskName)) {
      throw new InvalidTaskNameException(
        "Task Name cannot be empty and must contain atlest " +
        VALID_TASKNAME_LENGTH +
        " characters"
      );
    }
    if (isNull(date)) {
      throw new NullException("No Due Date chosen, please choose a date");
    }
    if (!isNameNotNull(personName)) {
      throw new InvalidTaskNameException(
        "Name cannot be empty and must contain atlest " +
        VALID_NAME_LENGTH +
        " characters"
      );
    }
    if (!isTaskDescShortNotNull(taskDescShort)) {
      throw new InvalidTaskNameException(
        "Short description cannot be empty and must contain atlest " +
        VALID_SHORTDESC_LENGTH +
        " characters"
      );
    }
    if (!isTaskDescLongNotNull(taskDescLong)) {
      throw new InvalidTaskNameException(
        "Full Details of Task cannot be empty and must contain atlest " +
        VALID_LONGDESC_LENGTH +
        " characters"
      );
    }

    if (isNull(imagePath)) {
      throw new NullException("Invalid Path to Image, no image selected ");
    }

    return true;
  }

  public static <object> boolean isNull(object data) {
    return data == null;
  }

  private static boolean isTaskNameNotNull(String taskName) {
    return !isNull(taskName) && taskName.length() >= VALID_TASKNAME_LENGTH;
  }

  private static boolean isTaskDescShortNotNull(String taskDescShort) {
    return (
      !isNull(taskDescShort) && taskDescShort.length() >= VALID_SHORTDESC_LENGTH
    );
  }

  private static boolean isTaskDescLongNotNull(String taskDescLong) {
    return (
      !isNull(taskDescLong) && taskDescLong.length() >= VALID_LONGDESC_LENGTH
    );
  }

  private static boolean isNameNotNull(String personName) {
    return !isNull(personName) && personName.length() >= VALID_NAME_LENGTH;
  }
}
