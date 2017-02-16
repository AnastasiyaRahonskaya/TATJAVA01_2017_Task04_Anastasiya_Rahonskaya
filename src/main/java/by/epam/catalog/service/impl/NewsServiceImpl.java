package by.epam.catalog.service.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.dao.NewsDAO;
import by.epam.catalog.dao.exception.ConnectionPoolException;
import by.epam.catalog.dao.exception.DAOException;
import by.epam.catalog.dao.factory.DAOFactory;
import by.epam.catalog.service.NewsService;
import by.epam.catalog.service.exception.ServiceException;

/**
 * Class contains the implementation of the interface NewsService
 */
public class NewsServiceImpl implements NewsService {
  /**
   * method is for adding the new
   *
   * @param news - object of the class News
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public void addNew(News news) throws ServiceException {
    if (validation(news)) {
      throw new ServiceException("Error while adding the news!");
    }
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.addNew(news);
    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Wrong data in adding new!", e);
    }
  }

  /**
   * method is for finding the new by category
   *
   * @param category - new's category
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public void findByCategory(String category) throws ServiceException {
    if (validation(category)) {
      throw new ServiceException("Error while finding by Category!");
    }
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.findByCategory(category);
    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Fail while find the new by category!", e);
    }
  }

  /**
   * method is for finding the new by title
   *
   * @param title - new's title
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public void findByTitle(String title) throws ServiceException {
    if (validation(title)) {
      throw new ServiceException("Error while finding by Category!");
    }
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.findByTitle(title);
    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Wrong data in finding news by title!", e);
    }
  }

  /**
   * method is for finding the new by author
   *
   * @param author - new's author
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public void findByAuthor(String author) throws ServiceException {
    if (validation(author)) {
      throw new ServiceException("Error while finding by Category!");
    }
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.findByAuthor(author);
    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Wrong data in finding news by author!", e);
    }
  }

  /**
   * method is for finding the new by date
   *
   * @param date - new's date
   * @throws ServiceException - exceptions caused by Service layer
   */
  @Override
  public void findByDate(String date) throws ServiceException {
    if (validation(date)) {
      throw new ServiceException("Error while finding by Category!");
    }
    DAOFactory daoObjectFactory = DAOFactory.getInstance();
    try {
      NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
      newsDAO.findByDate(date);
    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Wrong data in finding news by date!", e);
    }
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
    } catch (ConnectionPoolException e) {
      throw new ServiceException("Error in connection to the database.", e);
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
    } catch (ConnectionPoolException e) {
      throw new ServiceException("Error in closing the connection to the database.", e);
    }
  }

  /**
   * method is for validate input data
   *
   * @param news - object of the class News
   * @return true, if one of the fields of the object news is empty,
   * otherwise false
   */
  public boolean validation(News news) {
    if (news.getCategory().isEmpty() | news.getAuthor().isEmpty() | news.getTitle().isEmpty() | news.getDate().isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * method is for validate input data
   * @param s - input data
   * @return true, if one of the fields of the object news is empty,
   * otherwise false
   */
  public boolean validation(String s) {
    if (s.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
}