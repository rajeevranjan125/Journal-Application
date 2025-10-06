package net.engineeringdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity,ObjectId> {
    
}
