package net.engineeringdigest.journalApp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection  ="config_journal_aap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigJournalAppEntity {
    private String key;
    private String value; 
}
