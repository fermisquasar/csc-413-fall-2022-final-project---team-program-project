package handler;

import dao.ConversationDao;
import dao.MessageDao;
import dao.UserDao;
import dto.ConversationDto;
import dto.MessageDto;
import handler.AuthFilter.AuthResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;


public class CreateConversationHandler implements BaseHandler {
	
    public HttpResponseBuilder handleRequest(ParsedRequest request) {
    	 MessageDto messageDto = GsonTool.gson.fromJson(request.getBody(), dto.MessageDto.class);
         UserDao userDao = UserDao.getInstance();

         AuthResult authResult = AuthFilter.doFilter(request);
         if(!authResult.isLoggedIn){
             return new HttpResponseBuilder().setStatus(StatusCodes.UNAUTHORIZED);
         }

         if (userDao.query(new Document("userName", messageDto.getToId())).size() == 0) {
             var res = new RestApiAppResponse<>(false, null,
                     "unknown user");
             return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
         }

         String conversationId = makeConvoId(messageDto.getFromId(), messageDto.getToId());

         ConversationDao conversationDao = ConversationDao.getInstance();

         if(conversationDao.query(new Document("conversationId", conversationId)).size() == 0){
             ConversationDto convo = new ConversationDto();
             convo.setReceiverId(messageDto.getToId());
             convo.setSenderId(messageDto.getFromId());
             convo.setConversationId(conversationId);
             conversationDao.put(convo);
         }

         var res = new RestApiAppResponse<>(true, List.of(messageDto), null);
         return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
     }

     public static String makeConvoId(String a, String b){
         return List.of(a,b).stream()
                 .sorted(Comparator.naturalOrder())
                 .collect(Collectors.joining("_"));
     }
}
