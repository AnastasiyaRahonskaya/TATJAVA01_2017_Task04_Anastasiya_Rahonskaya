package by.epam.catalog.controller.command.impl;

import by.epam.catalog.bean.News;
import by.epam.catalog.controller.command.Command;
import by.epam.catalog.service.NewsService;
import by.epam.catalog.service.exception.ServiceException;
import by.epam.catalog.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class contains overridden method FindByDate
 */
public class FindByDate implements Command {
  private static final Logger LOG = LogManager.getRootLogger();

  /**
   * method is for executing request
   *
   * @param request - input command
   * @return response
   */
  @Override
  public String execute(String request) {
    String response = null;
    try {
      String date = request.split("/")[1];
      ServiceFactory serviceFactory = ServiceFactory.getInstance();
      NewsService newsService = serviceFactory.getNewsService();
      ArrayList<News> news = newsService.findByDate(date);
      System.out.println(news);
    } catch (ServiceException | ArrayIndexOutOfBoundsException e) {
      response = "Error while finding the news by date!";
      LOG.error(e);
    }
    return response;
  }
}
