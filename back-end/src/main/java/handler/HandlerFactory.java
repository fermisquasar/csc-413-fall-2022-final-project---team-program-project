package handler;

import dao.AuthDao;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;

public class HandlerFactory {
  // routes based on the path. Add your custom handlers here
  public static BaseHandler getHandler(ParsedRequest request) {
    // TODO
    switch (request.getPath()) {
      case "/createUser":
        return new CreateUserHandler();
      case "/login":
        return new LoginHandler();
      case "/getConversations":
        return new GetConversationsHandler();
      case "/getConversation":
        return new GetConversationHandler();
      case "/createMessage":
        return new CreateMessageHandler();
      case "/createConversation":
          return new CreateConversationHandler();
      case "/logout":
        return new LogoutHandler();
      default:
        return new FallbackHandler();
    }
  }

}
