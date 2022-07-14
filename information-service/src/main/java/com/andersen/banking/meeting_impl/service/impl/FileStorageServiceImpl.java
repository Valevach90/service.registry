package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.FileInfoDto;
import com.andersen.banking.meeting_db.entities.FileInfo;
import com.andersen.banking.meeting_db.repository.FileInfoRepository;
import com.andersen.banking.meeting_impl.mapping.FileInfoMapper;
import com.andersen.banking.meeting_impl.service.DropboxAccessService;
import com.andersen.banking.meeting_impl.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileInfoRepository fileInfoRepository;
    private final FileInfoMapper fileInfoMapper;

    private final DropboxAccessService dropboxAccessService;

    @Override
    @Transactional(readOnly = true)
    public List<FileInfoDto> getAllFileInfoDto() {
        log.debug("Find all files information");

        List<FileInfoDto> result = fileInfoMapper.toListFileInfoDto(fileInfoRepository.findAll());

        log.info("Return list of all files information {}", result);

        return result;
    }

    @Override
    @Transactional
    public Optional<String> getFileDownloadLink(String name) {
        log.debug("Find download link for file with name {}", name);

        Optional<String> fileDownloadLink = dropboxAccessService.findDownloadLinkByFileName(name);

        if (fileDownloadLink.isPresent()){
            updateFileInfo(fileDownloadLink.get(), name);
        }

        log.debug("Return download link: file name {}, download link {}", name, fileDownloadLink);

        return fileDownloadLink;
    }

    @Override
    @Transactional
    public Map<String, String> getFilesDownloadLinks(String... names) {
        log.debug("Find download links for series of files names {}", names);

        Map<String, String> links = new HashMap<>();

        for (String name : names){
            log.debug("Find download link for file with name {}", name);

            Optional<String> fileDownloadLink = dropboxAccessService.findDownloadLinkByFileName(name);

            if (fileDownloadLink.isPresent()){
                updateFileInfo(fileDownloadLink.get(), name);

                links.put(name, fileDownloadLink.get());

            } else {
                log.debug("Not found download link for file with name {}", name);
            }
        }
        log.debug("Found download links {}", links);

        return links;
    }

    private void updateFileInfo(String fileDownloadLink, String name){

        Optional<FileInfo> fileInfo = fileInfoRepository.findByFileName(name);

        if (fileInfo.isPresent()){
            FileInfo updatedFileInfo = fileInfo.get();

            if (!updatedFileInfo.getLink().equals(fileDownloadLink)){
                updatedFileInfo.setLink(fileDownloadLink);
            }
            updatedFileInfo.setDateOfUpdate(new Timestamp(System.currentTimeMillis()));

            fileInfoRepository.save(updatedFileInfo);

        } else {
            FileInfo createdFileInfo = new FileInfo();

            createdFileInfo.setFileName(name);
            createdFileInfo.setLink(fileDownloadLink);
            createdFileInfo.setDateOfCreation(new Timestamp(System.currentTimeMillis()));

            fileInfoRepository.save(createdFileInfo);
        }
    }
}
