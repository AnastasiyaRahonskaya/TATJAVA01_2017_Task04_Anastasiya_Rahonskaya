package by.epam.catalog.dao.exception;

/**
 * Created by Anastasiya_Rahonskay on 2/8/2017.
 */
public class ConnectionPoolException extends Exception {
  private static final long serialVersionUID = 1L;

  public ConnectionPoolException(String message, Exception e) {
    super(message, e);
  }
}
