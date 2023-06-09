package dao;

import com.mongodb.client.MongoCollection;
import dto.BaseDto;
import dto.ConversationDto;

import java.util.List;
import org.bson.Document;

public abstract class BaseDao<T extends BaseDto> {

  final MongoCollection<Document> collection;

  protected BaseDao(MongoCollection<Document> collection) {
    this.collection = collection;
  }

  abstract void put(T t);

  abstract List<T> query(Document filter);

  public List<ConversationDto> query(Document filter1, Document filter2) {
    // TODO Auto-generated method stub
    return null;
  }

}
