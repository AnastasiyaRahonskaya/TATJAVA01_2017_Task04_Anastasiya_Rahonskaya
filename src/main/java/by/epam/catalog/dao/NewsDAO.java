package by.epam.catalog.dao;

import by.epam.catalog.bean.News;
import by.epam.catalog.dao.exception.ConnectionPoolDataSourceException;
import by.epam.catalog.dao.exception.DAOException;

import java.util.ArrayList;

/**
 * The outer interface implementation of the DAO layer
 */
public interface NewsDAO {
  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  String addNew(News news) throws DAOException, ConnectionPoolDataSourceException;

  /**
   * method is for finding the new by category
   *
   * @param category - new's category
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  ArrayList<News> findByCategory(String category) throws DAOException, ConnectionPoolDataSourceException;

  /**
   * method is for finding thw new by title
   *
   * @param title - new's title
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  ArrayList<News> findByTitle(String title) throws DAOException, ConnectionPoolDataSourceException;

  /**
   * method is for finding the news by author
   *
   * @param author - new's author
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  ArrayList<News> findByAuthor(String author) throws DAOException, ConnectionPoolDataSourceException;

  /**
   * method is for finding the news by date
   *
   * @param date - new's date
   * @throws DAOException            - exceptions caused by DAO layer
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  ArrayList<News> findByDate(String date) throws DAOException, ConnectionPoolDataSourceException;

  /**
   * method is for init connection to the database
   *
   * @throws ConnectionPoolDataSourceException - exceptions caused by ConnectionPool
   */
  void init() throws ConnectionPoolDataSourceException;

  /**
   * method is for destroy connection to the database
   *
   * @throws ConnectionPoolDataSourceException - exception caused by ConnectionPool
   */
  void destroy() throws ConnectionPoolDataSourceException;
}
