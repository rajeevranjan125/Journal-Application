package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)//unique=true means all username will be unique and with indexing ,the search time for username will be fast.
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef //creating a ref in user collection of journalEntries
    private List<JournalEntry> journalEntries =new ArrayList<>();//it takes refernce of all entries in the journalEntries
    private List <String> roles;
}
