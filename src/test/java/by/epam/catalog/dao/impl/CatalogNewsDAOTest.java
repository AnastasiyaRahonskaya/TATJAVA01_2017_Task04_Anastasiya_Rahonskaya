package by.epam.catalog.dao.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.controller.Controller;
import by.epam.catalog.dao.exception.ConnectionPoolException;
import by.epam.catalog.dao.exception.DAOException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Unit-tests
 */
public class CatalogNewsDAOTest {
  private CatalogNewsDAO catalogNewsDAO;
  private News newNews;
  private News news;
  private Controller controller;


  @BeforeMethod
  public void setUp() throws DAOException {
    controller = new Controller();
    controller.init();
    catalogNewsDAO = new CatalogNewsDAO();
    newNews = new News("Film", "Terminator", "Jane", "20.10.2015");
    news = new News("Book", "Gone with the wind", "Ray", "30.06.2006");

  }

  @AfterMethod
  public void tearDown() throws Exception {
    controller = new Controller();
    controller.destroy();
  }

  @Test
  public void testFindByCategoryNotNull() throws Exception {
    String category = "Film";
    news = catalogNewsDAO.findByCategory(category);
    assertNotNull(news);
  }

  @Test
  public void testFindByTitleNotNull() throws DAOException, ConnectionPoolException {
    String title = "Anna Karenina";
    news = catalogNewsDAO.findByTitle(title);
    assertNotNull(news);
  }

  @Test
  public void testFindByAuthorNotNull() throws DAOException, ConnectionPoolException {
    String author = "Ray";
    news = catalogNewsDAO.findByAuthor(author);
    assertNotNull(news);
  }

  @Test
  public void testFindDateNotNull() throws DAOException, ConnectionPoolException {
    String date = "06.08.2009";
    news = catalogNewsDAO.findByDate(date);
    assertNotNull(news);
  }

  @Test
  public void testFindByCategory() throws DAOException, ConnectionPoolException {
    String category = "Film";
    news = catalogNewsDAO.findByCategory(category);
    assertEquals(news, newNews);
  }

  @Test
  public void testFindByTitle() throws DAOException, ConnectionPoolException {
    String title = "Terminator";
    news = catalogNewsDAO.findByTitle(title);
    assertEquals(news, newNews);
  }

  @Test
  public void testFindByAuthor() throws DAOException, ConnectionPoolException {
    String author = "Jane";
    news = catalogNewsDAO.findByAuthor(author);
    assertEquals(news, newNews);
  }

  @Test
  public void testFindByDate() throws DAOException, ConnectionPoolException {
    String date = "20.10.2015";
    news = catalogNewsDAO.findByDate(date);
    assertEquals(news, newNews);
  }

  @DataProvider(name = "DataProviderValidateInputDataFind")
  public Object[][] createDataFind() {
    return new Object[][]{
        {null},
        {new String("Filmm")}
    };
  }

  @Test(dataProvider = "DataProviderValidateInputDataFind", expectedExceptions = {DAOException.class, ConnectionPoolException.class})
  public void testFindByCategoryNegative(String category) throws DAOException, ConnectionPoolException {
    catalogNewsDAO.findByCategory(category);
  }

  @Test(dataProvider = "DataProviderValidateInputDataFind", expectedExceptions = DAOException.class)
  public void testFindByTitleNegative(String title) throws DAOException, ConnectionPoolException {
    catalogNewsDAO.findByTitle(title);
  }

  @Test(dataProvider = "DataProviderValidateInputDataFind", expectedExceptions = {DAOException.class, ConnectionPoolException.class})
  public void testFindByAuthorNegative(String author) throws DAOException, ConnectionPoolException {
    catalogNewsDAO.findByCategory(author);
  }

  @Test(dataProvider = "DataProviderValidateInputDataFind", expectedExceptions = {DAOException.class, ConnectionPoolException.class})
  public void testFindByDateNegative(String date) throws DAOException, ConnectionPoolException {
    catalogNewsDAO.findByCategory(date);
  }

  @DataProvider(name = "DataProviderValidateInputDataAdd")
  public Object[][] createDataAdd() {
    return new Object[][]{
        {new News(null, "La La Land", "Jane", "09.01.2017")},
        {new News("Film", null, "Jane", "09.01.2017")},
        {new News("Film", "La La Land", null, "09.01.2017")},
        {new News("Film", "La La Land", "Jane", null)},
        {new News(null, null, null, null)}
    };
  }

  @Test(dataProvider = "DataProviderValidateInputDataAdd", expectedExceptions = {DAOException.class, ConnectionPoolException.class})
  public void testAddNewNegative(News news) throws DAOException, ConnectionPoolException {
    catalogNewsDAO.addNew(news);
  }
}