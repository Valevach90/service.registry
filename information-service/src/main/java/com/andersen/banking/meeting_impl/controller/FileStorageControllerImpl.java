package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.FileStorageController;
import com.andersen.banking.meeting_api.dto.FileInfoDto;
import com.andersen.banking.meeting_impl.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * File storage controller implementation.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileStorageControllerImpl implements FileStorageController {

    private final FileStorageService fileStorageService;

    @Override
    public List<FileInfoDto> getAllFileInfoDto() {
        log.trace("Receiving list of all FileInfoDto");

        List<FileInfoDto> allFileInfoDto = fileStorageService.getAllFileInfoDto();

        log.trace("Returning list of all FileInfoDto: {}", allFileInfoDto);

        return allFileInfoDto;
    }

    @Override
    public Optional<String> getFileDownloadLink(String name) {
        log.trace("Receiving download link by file name: {}", name);

        Optional<String> link = fileStorageService.getFileDownloadLink(name);

        log.trace("Returning download link: file {}, link {}", name, link);
        return link;
    }

    @Override
    public Map<String, String> getFilesDownloadLinks(String... names) {
        log.trace("Receiving download links for series of files names: {}", names);

        Map<String, String> links = fileStorageService.getFilesDownloadLinks(names);

        log.trace("Returning download links for files names: {}", links);

        return links;
    }
}
