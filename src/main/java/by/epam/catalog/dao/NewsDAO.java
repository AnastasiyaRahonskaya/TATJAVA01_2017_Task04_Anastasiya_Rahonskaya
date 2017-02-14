package by.epam.catalog.dao;

import by.epam.catalog.bean.News;
import by.epam.catalog.dao.exception.ConnectionPoolException;
import by.epam.catalog.dao.exception.DAOException;

/**
 * The outer interface implementation of the DAO layer
 */
public interface NewsDAO {
  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  News addNew(News news) throws DAOException, ConnectionPoolException;

  /**
   * method is for finding the new by category
   *
   * @param category - new's category
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  News findByCategory(String category) throws DAOException, ConnectionPoolException;

  /**
   * method is for finding thw new by title
   *
   * @param title - new's title
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  News findByTitle(String title) throws DAOException, ConnectionPoolException;

  /**
   * method is for finding the news by author
   *
   * @param author - new's author
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  News findByAuthor(String author) throws DAOException, ConnectionPoolException;

  /**
   * method is for finding the news by date
   *
   * @param date - new's date
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  News findByDate(String date) throws DAOException, ConnectionPoolException;

  /**
   * method is for init connection to the database
   *
   * @throws ConnectionPoolException - exceptions caused by ConnectionPool
   */
  void init() throws ConnectionPoolException;

  /**
   * method is for destroy connection to the database
   *
   * @throws ConnectionPoolException - exception caused by ConnectionPool
   */
  void destroy() throws ConnectionPoolException;
}
