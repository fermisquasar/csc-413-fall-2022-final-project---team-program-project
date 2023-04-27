package handler;

import dao.AuthDao;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;

public class LogoutHandler implements BaseHandler{

    @Override
    public HttpResponseBuilder handleRequest(ParsedRequest request) {

        AuthDao authDao = AuthDao.getInstance();
        String hash = request.getHeaderValue("auth");

        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);

        Document auth = new Document("hash", hash);
        var authQuery = authDao.query(auth);

        if(!authResult.isLoggedIn){
            return new HttpResponseBuilder().setStatus("401 UNAUTHORIZED");
        }
        authDao.remove(authQuery.get(0));

        return new HttpResponseBuilder().setStatus("200 OK");
    }
}
