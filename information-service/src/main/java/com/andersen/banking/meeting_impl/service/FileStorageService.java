package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.FileInfoDto;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with file storages.
 */

public interface FileStorageService {

    /**
     * Find all FileInfoDto.
     *
     * @return list of FileInfoDto
     */
    List<FileInfoDto> getAllFileInfoDto();

    /**
     * Get file download link by file name.
     *
     * @param name name of file
     * @return link to download file
     */
    Optional<String> getFileDownloadLink(String name);
}
