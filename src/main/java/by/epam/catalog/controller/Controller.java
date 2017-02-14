package by.epam.catalog.controller;

import by.epam.catalog.controller.command.Command;
import by.epam.catalog.service.NewsService;
import by.epam.catalog.service.exception.ServiceException;
import by.epam.catalog.service.factory.ServiceFactory;

/**
 * Class is for managing the commands
 */
public class Controller {
  private final CommandProvider provider = new CommandProvider();

  private final char paramSeparator = '/';

  /**
   * method is for executing the commands
   *
   * @param request - input command
   * @return response
   */
  public String executeTask(String request) {
    String commandName;
    Command executionCommand;
    commandName = request.substring(0, request.indexOf(paramSeparator));
    executionCommand = provider.getCommand(commandName);
    String response;
    response = executionCommand.execute(request);
    return response;
  }

  public void init() {
    try {
      ServiceFactory serviceFactory = ServiceFactory.getInstance();
      NewsService newsService = serviceFactory.getNewsService();
      newsService.init();
    } catch (ServiceException e) {
      System.out.println("Error in connection to the database.");
    }
  }

  public void destroy() {
    try {
      ServiceFactory serviceFactory = ServiceFactory.getInstance();
      NewsService newsService = serviceFactory.getNewsService();
      newsService.destroy();
    } catch (ServiceException e) {
      System.out.println("Error in closing the connection to the database.");
    }
  }
}
