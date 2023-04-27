package handler;

import dao.ConversationDao;
import handler.AuthFilter.AuthResult;
import org.bson.Document;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class GetConversationsHandler implements BaseHandler{

    @Override
    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        ConversationDao conversationDao = ConversationDao.getInstance();
        AuthResult authResult = AuthFilter.doFilter(request);
        if(!authResult.isLoggedIn){
            return new HttpResponseBuilder().setStatus(StatusCodes.UNAUTHORIZED);
        }
        var filter1 = new Document("senderId", request.getQueryParam("userName"));
        var filter2 = new Document("receiverId", request.getQueryParam("userName"));
        
        var res = new RestApiAppResponse<>(true, conversationDao.query(filter1,filter2), null);
        return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
    }
}
