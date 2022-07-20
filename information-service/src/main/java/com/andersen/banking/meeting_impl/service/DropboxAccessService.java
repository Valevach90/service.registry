package com.andersen.banking.meeting_impl.service;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with Dropbox file storages.
 */

public interface DropboxAccessService {

    /**
     * Find names of all stored files.
     *
     * @return list of names of all stored files
     */
    List<String> findAllFilesNames();

    /**
     * Get file download link by file name.
     *
     * @param name name of file
     * @return link to download file
     */
    Optional<String> findDownloadLinkByFileName(String name);
}
