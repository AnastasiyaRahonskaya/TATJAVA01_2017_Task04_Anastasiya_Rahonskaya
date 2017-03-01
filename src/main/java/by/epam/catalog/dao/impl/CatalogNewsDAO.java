package by.epam.catalog.dao.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.dao.connection.ConnectionPool;
import by.epam.catalog.dao.NewsDAO;
import by.epam.catalog.dao.exception.ConnectionPoolDataSourceException;
import by.epam.catalog.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

/**
 * The inner interface implementation of the DAO layer
 */
public class CatalogNewsDAO implements NewsDAO {
  private ConnectionPool connectionPool = ConnectionPool.getInstance();
  private News news;
  private static final String SQL_INSERT_NEWS = "INSERT INTO catalogbd.catalog (category, title, author, date_of_publication) VALUES (?, ?, ?, ?);";
  private static final String SQL_SELECT_BY_CATEGORY = "SELECT * FROM catalogbd.catalog WHERE category = ?;";
  private static final String SQL_SELECT_BY_TITLE = "SELECT * FROM catalogbd.catalog WHERE title=?;";
  private static final String SQL_SELECT_BY_AUTHOR = "SELECT * FROM catalogbd.catalog WHERE author=?;";
  private static final String SQL_SELECT_BY_DATE = "SELECT * FROM catalogbd.catalog WHERE date_of_publication=?;";

  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws DAOException                      - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  @Override
  public String addNew(News news) throws DAOException, ConnectionPoolDataSourceException {
    PreparedStatement preparedStatement = null;
    Connection connection = connectionPool.takeConnection();
    String result;
    try {
      preparedStatement = connection.prepareStatement(SQL_INSERT_NEWS);
      preparedStatement.setString(1, news.getCategory());
      preparedStatement.setString(2, news.getTitle());
      preparedStatement.setString(3, news.getAuthor());
      preparedStatement.setString(4, news.getDate());
      preparedStatement.executeUpdate();
      result = "Your new added!";
      news = new News(news.getCategory(), news.getTitle(), news.getAuthor(), news.getDate());
      System.out.println(result);
      connectionPool.closeConnection(connection, preparedStatement);
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    return result;
  }

  /**
   * method is for finding the new by category
   *
   * @param foundCategory - new's category
   * @return result
   * @throws DAOException                      - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  @Override
  public ArrayList<News> findByCategory(String foundCategory) throws DAOException, ConnectionPoolDataSourceException {
    ArrayList<News> news = new ArrayList<>();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CATEGORY);
      preparedStatement.setString(1, foundCategory);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String category = resultSet.getString(2);
        String title = resultSet.getString(3);
        String author = resultSet.getString(4);
        String date = resultSet.getString(5);
        news.add(new News(category, title, author, date));
      }
      if (news == null) {
        throw new DAOException("Error in findByCategory.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    return news;
  }

  /**
   * method is for finding the new by title
   *
   * @param foundTitle - new's title
   * @return result
   * @throws DAOException                      - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  @Override
  public ArrayList<News> findByTitle(String foundTitle) throws DAOException, ConnectionPoolDataSourceException {
    ArrayList<News> news = new ArrayList<>();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_TITLE);
      preparedStatement.setString(1, foundTitle);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String category = resultSet.getString(2);
        String title = resultSet.getString(3);
        String author = resultSet.getString(4);
        String date = resultSet.getString(5);
        news.add(new News(category, title, author, date));
      }
      if (news == null) {
        throw new DAOException("Error in findByTitle.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    return news;
  }

  /**
   * method is for finding the new by author
   *
   * @param foundAuthor - new's author
   * @return result
   * @throws DAOException                      - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  @Override
  public ArrayList<News> findByAuthor(String foundAuthor) throws DAOException, ConnectionPoolDataSourceException {
    ArrayList<News> news = new ArrayList<>();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_AUTHOR);
      preparedStatement.setString(1, foundAuthor);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String category = resultSet.getString(2);
        String title = resultSet.getString(3);
        String author = resultSet.getString(4);
        String date = resultSet.getString(5);
        news.add(new News(category, title, author, date));
      }
      if (news == null) {
        throw new DAOException("Error in findByAuthor.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    return news;
  }

  /**
   * method is for finding the new by date
   *
   * @param foundDate - new's date
   * @return result
   * @throws DAOException                      - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  @Override
  public ArrayList<News> findByDate(String foundDate) throws DAOException, ConnectionPoolDataSourceException {
    ArrayList<News> news = new ArrayList<>();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_DATE);
      preparedStatement.setString(1, foundDate);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String category = resultSet.getString(2);
        String title = resultSet.getString(3);
        String author = resultSet.getString(4);
        String date = resultSet.getString(5);
        news.add(new News(category, title, author, date));
      }
      if (news == null) {
        throw new DAOException("Error in findByDate.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    return news;
  }

  @Override
  public void init() throws ConnectionPoolDataSourceException {
    connectionPool.initPoolData();
  }

  @Override
  public void destroy() throws ConnectionPoolDataSourceException {
    connectionPool.dispose();
  }
}
