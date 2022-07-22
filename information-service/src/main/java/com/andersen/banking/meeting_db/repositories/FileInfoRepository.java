package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    /**
     * Find file information by file name.
     *
     * @param fileName
     * @return File information for selected file name
     */
    Optional<FileInfo> findByFileName(String fileName);
}
