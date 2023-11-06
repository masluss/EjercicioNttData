package com.api.users.repository;

import com.api.users.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    DocumentType findByType(String type);
}
