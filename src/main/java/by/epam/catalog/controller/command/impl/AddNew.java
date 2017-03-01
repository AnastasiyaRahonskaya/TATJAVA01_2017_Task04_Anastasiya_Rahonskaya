package by.epam.catalog.controller.command.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.controller.command.Command;
import by.epam.catalog.service.NewsService;
import by.epam.catalog.service.exception.ServiceException;
import by.epam.catalog.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class contains overridden method AddNew
 */
public class AddNew implements Command {
  private static final Logger LOG = LogManager.getRootLogger();
  /**
   * method is for executing request
   *
   * @param request - input command
   * @return response
   */
  @Override
  public String execute(String request) {
    String response;
    try {
      String category = request.split("/")[1];
      String title = request.split("/")[2];
      String author = request.split("/")[3];
      String date = request.split("/")[4];
      ServiceFactory serviceFactory = ServiceFactory.getInstance();
      NewsService newsService = serviceFactory.getNewsService();

      String result = newsService.addNew(new News(category, title, author, date));
      response = "The new added!";
      System.out.println(response);
    } catch (ServiceException | ArrayIndexOutOfBoundsException e) {
      response = "Error while adding the new!";
      LOG.error(e);
    }
    return response;
  }
}
