package net.engineeringdigest.journalApp.entity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id; //ObjectId is a datatype of mongoDB id
    private String title;
    private String content;
    private LocalDateTime date;//LocalDateTime is the datatype

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return this.content;
    }

}
