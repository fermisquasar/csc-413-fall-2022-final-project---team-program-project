package dto;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class ConversationDto extends BaseDto{

  private String conversationId;
  private String senderId;
  private String receiverId;

  public void setConversationId(String conversationId) {
    this.conversationId = conversationId;
  }

  public void setReceiverId(String username) {
    this.receiverId = username;
  }
  
  public void setSenderId(String username) {
	this.senderId = username;
  }

  public String getConversationId() {
    return conversationId;
  }

  public String getSenderId() {
    return senderId;
  }
  
  public String getReceiverId() {
	 return receiverId;
  }
  
  @Override
  public Document toDocument() {
    // TODO
    var doc = new Document();
    doc.append("senderId", senderId);
    doc.append("receiverId", receiverId);
    doc.append("conversationId", conversationId);
    return doc;
  }

  public static ConversationDto fromDocument(Document document) {
    // TODO
    var res = new ConversationDto();
    res.setConversationId(document.getString("conversationId"));
    res.setReceiverId(document.getString("receiverId"));
    res.setSenderId(document.getString("senderId"));
    return res;
  }
}
