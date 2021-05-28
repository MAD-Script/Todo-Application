package todomanager.utilities;

import java.io.*;
import java.util.ArrayList;
import todomanager.data.Task;

public class Serializer {

  public static final String taskdatabasepath =
    "G:\\Study\\CSE215 Lab\\Todo Application\\src\\todomanager\\data\\taskdata.bin";

  public static boolean serializeData(
    String filePath,
    ArrayList<Task> taskList
  ) {
    File dataFile = new File(filePath);
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;

    boolean isSuccess = false;
    try {
      fileOutputStream = new FileOutputStream(dataFile);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(taskList);
      isSuccess = true;
    } catch (Exception exception) {
      isSuccess = false;
    }
    return isSuccess;
  }

  public static ArrayList<Task> deserialize(String filePath) {
    File dataFile = new File(filePath);
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;

    ArrayList<Task> tasklist = null;

    try {
      fileInputStream = new FileInputStream(dataFile);
      objectInputStream = new ObjectInputStream(fileInputStream);

      tasklist = (ArrayList<Task>) objectInputStream.readObject();
    } catch (Exception exception) {
      System.out.println(exception.toString());
    }

    return tasklist;
  }
}
