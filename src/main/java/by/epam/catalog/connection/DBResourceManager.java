package by.epam.catalog.connection;

import java.util.ResourceBundle;

/**
 * Database resource manager
 */
public class DBResourceManager {
  private final static DBResourceManager instance = new DBResourceManager();

  private ResourceBundle bundle = ResourceBundle.getBundle("db");

  public static DBResourceManager getInstance() {
    return instance;
  }

  public String getValue(String key) {
    return bundle.getString(key);
  }
}