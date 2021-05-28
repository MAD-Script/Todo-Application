package todomanager.exceptions;

public class InvalidTaskNameException extends Exception {

  public InvalidTaskNameException() {
    super();
  }

  public InvalidTaskNameException(String message) {
    super(message);
  }
}
