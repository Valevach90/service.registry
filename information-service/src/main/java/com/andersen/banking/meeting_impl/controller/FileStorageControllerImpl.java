package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.FileStorageController;
import com.andersen.banking.meeting_impl.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public List<String> getNamesOfAllFiles() {
        log.trace("Receiving list of names of all files");

        List<String> names = fileStorageService.getNamesOfAllFiles();

        log.trace("Returning list of names of files: {}", names);

        return names;
    }

    @Override
    public Optional<String> getFileDownloadLink(String name) {
        log.trace("Receiving download link by file name: {}", name);

        Optional<String> link = fileStorageService.getFileDownloadLink(name);

        log.trace("Returning download link: file {}, link {}", name, link);
        return link;
    }
}
