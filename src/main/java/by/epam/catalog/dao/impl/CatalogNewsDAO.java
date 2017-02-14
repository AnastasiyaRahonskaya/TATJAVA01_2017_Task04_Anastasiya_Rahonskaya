package by.epam.catalog.dao.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.connection.ConnectionPool;
import by.epam.catalog.dao.NewsDAO;
import by.epam.catalog.dao.exception.ConnectionPoolException;
import by.epam.catalog.dao.exception.DAOException;

import java.sql.*;

/**
 * The inner interface implementation of the DAO layer
 */
public class CatalogNewsDAO implements NewsDAO {
  private Statement statement;
  private Connection connection;
  private ResultSet resultSet;
  private ConnectionPool connectionPool = ConnectionPool.getInstance();
  private PreparedStatement preparedStatement;
  private News news;

  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  @Override
  public News addNew(News news) throws DAOException, ConnectionPoolException {
    connection = connectionPool.takeConnection();
    String result;
    String sqlQuery = "INSERT INTO catalogbd.catalog (category, title, author, date) VALUES (?, ?, ?, ?);";
    try {
      preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setString(1, news.getCategory());
      preparedStatement.setString(2, news.getTitle());
      preparedStatement.setString(3, news.getAuthor());
      preparedStatement.setString(4, news.getDate());
      preparedStatement.executeUpdate();
      result = "Your new added!";
      news = new News(news.getCategory(), news.getTitle(), news.getAuthor(), news.getDate());
      System.out.println(result);
    } catch (SQLException e) {
      throw new DAOException("Error while adding the new!", e);
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
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
    connection = connectionPool.takeConnection();
    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM catalogbd.catalog WHERE category='" + category + "'");
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        throw new DAOException("News aren't found!");
      }
    } catch (SQLException e) {
      throw new DAOException("Error in findByCategory", e);
    } finally {
      connectionPool.closeConnection(connection, statement, resultSet);
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
    connection = connectionPool.takeConnection();
    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM catalogbd.catalog WHERE title='" + title + "'");
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        throw new DAOException("Error while finding the news by title!");
      }
    } catch (SQLException e) {
      throw new DAOException("Error in findByCategory", e);
    } finally {
      connectionPool.closeConnection(connection, statement, resultSet);
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
    connection = connectionPool.takeConnection();
    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM catalogbd.catalog WHERE author='" + author + "'");
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        throw new DAOException("Error while finding the news by author!");
      }
    } catch (SQLException e) {
      throw new DAOException("Error in findByAuthor", e);
    } finally {
      connectionPool.closeConnection(connection, statement, resultSet);
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
    connection = connectionPool.takeConnection();
    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM catalogbd.catalog WHERE date='" + date + "'");
      while (resultSet.next()) {
        news = new News(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
            resultSet.getString(5));
        System.out.println(news.toString());
      }
      if (news == null) {
        throw new DAOException("Error while finding the news by date!");
      }
    } catch (SQLException e) {
      throw new DAOException("Error in findByDate", e);
    } finally {
      connectionPool.closeConnection(connection, statement, resultSet);
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
