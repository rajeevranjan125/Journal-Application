package net.engineeringdigest.journalApp.entity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.engineeringdigest.journalApp.enums.Sentiment;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Getter
@Setter
public class JournalEntry {
    @Id
    private ObjectId id; //ObjectId is a datatype of mongoDB id
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;//LocalDateTime is the datatype
    private Sentiment sentiment;
}
