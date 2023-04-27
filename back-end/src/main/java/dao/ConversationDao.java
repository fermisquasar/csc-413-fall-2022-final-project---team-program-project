package dao;

import com.mongodb.client.MongoCollection;
import dto.ConversationDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.Document;

public class ConversationDao extends BaseDao<ConversationDto> {

  private static ConversationDao instance;

  private ConversationDao(MongoCollection<Document> collection){
    super(collection);
  }

  public static ConversationDao getInstance(){
    if(instance != null){
      return instance;
    }
    instance = new ConversationDao(MongoConnection.getCollection("ConversationDao"));
    return instance;
  }

  public static ConversationDao getInstance(MongoCollection<Document> collection){
    instance = new ConversationDao(collection);
    return instance;
  }

  @Override
  public void put(ConversationDto conversationDto) {
    // TODO
    collection.insertOne(conversationDto.toDocument());
  }

  @Override
  public List<ConversationDto> query(Document filter1, Document filter2) {
    // TODO
	    List array1 = collection.find(filter1)
	    	    .into(new ArrayList<>())
	    	    .stream()
	    	    .map(ConversationDto::fromDocument)
	    	    .collect(Collectors.toList());
	    	    
	   List array2 = collection.find(filter2)
	    	    	    .into(new ArrayList<>())
	    	    	    .stream()
	    	    	    .map(ConversationDto::fromDocument)
	    	    	    .collect(Collectors.toList());
	   
	   return Stream.concat(array1.stream(), array2.stream()).toList();
  }
  
  @Override
  public List<ConversationDto> query(Document filter) {
	    // TODO
		    return collection.find(filter)
		    	    .into(new ArrayList<>())
		    	    .stream()
		    	    .map(ConversationDto::fromDocument)
		    	    .collect(Collectors.toList());
	  }

}
