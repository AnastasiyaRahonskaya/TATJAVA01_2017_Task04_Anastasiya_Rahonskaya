package by.epam.catalog.dao.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.dao.connection.ConnectionPool;
import by.epam.catalog.dao.NewsDAO;
import by.epam.catalog.dao.exception.ConnectionPoolException;
import by.epam.catalog.dao.exception.DAOException;

import java.sql.*;

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
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  @Override
  public News addNew(News news) throws DAOException, ConnectionPoolException {
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
      throw new DAOException("Error while adding the new!", e);
    }
    return news;
  }

  /**
   * method is for finding the new by category
   *
   * @param category - new's category
   * @return result
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  @Override
  public News findByCategory(String category) throws DAOException, ConnectionPoolException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CATEGORY);
      preparedStatement.setString(1, category);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        System.out.println("Unfortunately, we don't have news with this category.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException("Error in findByCategory", e);
    }
    return news;
  }

  /**
   * method is for finding the new by title
   *
   * @param title - new's title
   * @return result
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  @Override
  public News findByTitle(String title) throws DAOException, ConnectionPoolException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_TITLE);
      preparedStatement.setString(1, title);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        System.out.println("Unfortunately, we don't have news with this title.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException("Error in findByCategory", e);
    }
    return news;
  }

  /**
   * method is for finding the new by author
   *
   * @param author - new's author
   * @return result
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  @Override
  public News findByAuthor(String author) throws DAOException, ConnectionPoolException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_AUTHOR);
      preparedStatement.setString(1, author);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        System.out.println("Unfortunately, we don't have news by this author.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException("Error in findByAuthor", e);
    }
    return news;
  }

  /**
   * method is for finding the new by date
   *
   * @param date - new's date
   * @return result
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  @Override
  public News findByDate(String date) throws DAOException, ConnectionPoolException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = connectionPool.takeConnection();
    try {
      preparedStatement = connection.prepareStatement(SQL_SELECT_BY_DATE);
      preparedStatement.setString(1, date);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        System.out.println("Unfortunately, we don't have news with this date of publication.");
      }
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    } catch (SQLException e) {
      throw new DAOException("Error in findByDate", e);
    }
    return news;
  }

  @Override
  public void init() throws ConnectionPoolException {
    connectionPool.initPoolData();
  }

  @Override
  public void destroy() throws ConnectionPoolException {
    connectionPool.dispose();
  }
}
