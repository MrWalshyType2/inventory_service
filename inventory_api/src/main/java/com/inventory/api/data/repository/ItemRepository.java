package com.inventory.api.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inventory.api.data.domain.document.ItemDocument;

public interface ItemRepository extends MongoRepository<ItemDocument, String> {

}
