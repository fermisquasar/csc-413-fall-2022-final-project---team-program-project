package dao;

import com.mongodb.client.MongoCollection;
import dto.MessageDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;

// TODO fill this out
public class MessageDao extends BaseDao<MessageDto> {

  private static MessageDao instance;

  private MessageDao(MongoCollection<Document> collection){
    super(collection);
  }

  public static MessageDao getInstance(){
    if(instance != null){
      return instance;
    }
    instance = new MessageDao(MongoConnection.getCollection("MessageDao"));
    return instance;
  }

  public static MessageDao getInstance(MongoCollection<Document> collection){
    instance = new MessageDao(collection);
    return instance;
  }

  @Override
  public void put(MessageDto messageDto) {
    // TODO

    switch (messageDto.getMessage()) {
      case "Cake", "cake" -> messageDto.setMessage("It's a lie!");
      case "I have a secret" -> messageDto.setMessage("Shhhh!");
      case "Hello World", "hello world" -> messageDto.setMessage("Hello Human");
      case "Test" -> messageDto.setMessage("A+");
      case "test" -> messageDto.setMessage("F");
      case "What is the meaning of life?" -> messageDto.setMessage("42");
      case "Who's the best?" -> messageDto.setMessage("You're the best!");
      case "Hello", "hello" -> messageDto.setMessage("Goodbye");
    }

    collection.insertOne(messageDto.toDocument());
  }

  public List<MessageDto> query(Document filter){
    // TODO
    //return null;
    return collection.find(filter)
        .into(new ArrayList<>())
        .stream()
        .map(MessageDto::fromDocument)
        .collect(Collectors.toList());
  }

}
