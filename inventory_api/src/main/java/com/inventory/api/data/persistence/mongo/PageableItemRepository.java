package com.inventory.api.data.persistence.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.inventory.api.data.domain.document.ItemDocument;

public interface PageableItemRepository extends PagingAndSortingRepository<ItemDocument, Long> {

	Page<ItemDocument> findAll(Pageable pageable);
}
