package model;
import java.util.List;

public interface IRepository {
    public List<Conversation> importConversations();
    public void exportConversations(List<Conversation> Conversation);
    
}
