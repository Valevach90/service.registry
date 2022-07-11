package com.andersen.banking.meeting_impl.service;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with file storages.
 */

public interface FileStorageService {

    /**
     * Find all files names.
     *
     * @return list of files names
     */
    List<String> getNamesOfAllFiles();

    /**
     * Get file download link by file name.
     *
     * @param name name of file
     * @return link to download file
     */
    Optional<String> getFileDownloadLink(String name);
}
