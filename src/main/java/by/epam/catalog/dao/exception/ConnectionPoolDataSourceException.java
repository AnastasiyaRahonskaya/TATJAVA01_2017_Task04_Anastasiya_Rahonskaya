package by.epam.catalog.dao.exception;

/**
 * Created by Anastasiya_Rahonskay on 2/8/2017.
 */
public class ConnectionPoolDataSourceException extends Exception {
  private static final long serialVersionUID = 1L;

  public ConnectionPoolDataSourceException(String message, Exception e) {
    super(message, e);
  }
}
