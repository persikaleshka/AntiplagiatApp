package com.app.filestore.repository;

import com.app.filestore.model.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<StoredFile, UUID> {
}
