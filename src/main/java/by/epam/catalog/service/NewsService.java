package by.epam.catalog.service;

import by.epam.catalog.bean.News;
import by.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Class contains interface for Service layer
 */
public interface NewsService {
  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws ServiceException - exceptions caused by Service layer
   */
  String addNew(News news) throws ServiceException;

  /**
   * method is for finding the new by category
   *
   * @param category - new's category
   * @throws ServiceException - exceptions caused by Service layer
   */
  ArrayList<News> findByCategory(String category) throws ServiceException;

  /**
   * method is for finding thw new by title
   *
   * @param title - new's title
   * @throws ServiceException - exceptions caused by Service layer
   */
  ArrayList<News> findByTitle(String title) throws ServiceException;

  /**
   * method is for finding the news by author
   *
   * @param author - new's author
   * @throws ServiceException - exceptions caused by Service layer
   */
  ArrayList<News> findByAuthor(String author) throws ServiceException;

  /**
   * method is for finding the news by date
   *
   * @param date - new's date
   * @throws ServiceException - exceptions caused by Service layer
   */
  ArrayList<News> findByDate(String date) throws ServiceException;

  /**
   * method is for init connection to the database
   * @throws ServiceException - exceptions caused by Service layer
   */
  void init() throws ServiceException;

  /**
   * method is for destroy connection to the database
   * @throws ServiceException - exceptions caused by Service layer
   */
  void destroy() throws ServiceException;
}
