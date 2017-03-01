package by.epam.catalog.service.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.dao.NewsDAO;
import by.epam.catalog.dao.exception.ConnectionPoolDataSourceException;
import by.epam.catalog.dao.exception.DAOException;
import by.epam.catalog.dao.factory.DAOFactory;
import by.epam.catalog.service.NewsService;
import by.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Class contains the implementation of the interface NewsService
 */
public class NewsServiceImpl implements NewsService {
  private ArrayList<News> news;
  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public String addNew(News news) throws ServiceException {
    validation(news);
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    String result = null;
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      result = newsDAO.addNew(news);
    } catch (DAOException | ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
    return result;
  }

  /**
   * method is for finding the new by category
   *
   * @param category - new's category
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public ArrayList<News> findByCategory(String category) throws ServiceException {
    validation(category);
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      news = newsDAO.findByCategory(category);
    } catch (DAOException | ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
    return news;
  }

  /**
   * method is for finding the new by title
   *
   * @param title - new's title
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public ArrayList<News> findByTitle(String title) throws ServiceException {
    validation(title);
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      news = newsDAO.findByTitle(title);
    } catch (DAOException | ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
    return news;
  }

  /**
   * method is for finding the new by author
   *
   * @param author - new's author
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public ArrayList<News> findByAuthor(String author) throws ServiceException {
    validation(author);
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      news = newsDAO.findByAuthor(author);
    } catch (DAOException | ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
    return news;
  }

  /**
   * method is for finding the new by date
   *
   * @param date - new's date
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public ArrayList<News> findByDate(String date) throws ServiceException {
    validation(date);
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      news = newsDAO.findByDate(date);
    } catch (DAOException | ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
    return news;
  }

  /**
   * method is for init connection to the database
   *
   * @throws ServiceException - exception caused by Service layer
   */
  @Override
  public void init() throws ServiceException {
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.init();
    } catch (ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
  }

  /**
   * method is for destroy connection to the database
   *
   * @throws ServiceException - exception caused by Service layer
   */
  @Override
  public void destroy() throws ServiceException {
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.destroy();
    } catch (ConnectionPoolDataSourceException e) {
      throw new ServiceException(e);
    }
  }

  /**
   * method is for validate input data
   *
   * @param news - object of the class News
   */
  public void validation(News news) throws ServiceException {
    if (news.getCategory().isEmpty() | news.getAuthor().isEmpty() | news.getTitle().isEmpty() | news.getDate().isEmpty()) {
      throw new ServiceException("Error while adding the new!");
    }
  }

  /**
   * method is for validate input data
   *
   * @param s - input data
   */
  public void validation(String s) throws ServiceException {
    if (s.isEmpty() || s == null) {
      throw new ServiceException("Error!");
    }
  }
}
