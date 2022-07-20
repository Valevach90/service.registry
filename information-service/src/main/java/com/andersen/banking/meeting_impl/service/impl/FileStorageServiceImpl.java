package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.FileInfoDto;
import com.andersen.banking.meeting_db.entity.FileInfo;
import com.andersen.banking.meeting_db.repository.FileInfoRepository;
import com.andersen.banking.meeting_impl.mapper.FileInfoMapper;
import com.andersen.banking.meeting_impl.service.DropboxAccessService;
import com.andersen.banking.meeting_impl.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
    public FileInfoDto getFileInfoDto(String name) {
        log.debug("Find file information by file name");

        FileInfo fileInfo = updateFileInfo(name);

        FileInfoDto result = fileInfoMapper.toFileInfoDto(fileInfo);

        log.info("Return file information {}", result);

        return result;
    }

    @Override
    @Transactional
    public Optional<String> getFileDownloadLink(String name) {

        log.debug("Find download link for file with name {}", name);

        String fileDownloadLink = updateFileInfo(name).getLink();

        log.debug("Return download link: file name {}, download link {}", name, fileDownloadLink);

        return Optional.of(fileDownloadLink);
    }

    @Override
    @Transactional
    public List<FileInfoDto> getFilesDownloadLinks(String... names) {
        log.debug("Find download links for series of files names {}", names);

        List<FileInfoDto> links = new ArrayList<>();

        for (String name : names) {
            links.add(getFileInfoDto(name));
        }
        log.debug("Found download links {}", links);

        return links;
    }

    @Transactional
    FileInfo updateFileInfo(String name) {

        String fileDownloadLink = dropboxAccessService.findDownloadLinkByFileName(name).get();

        Optional<FileInfo> fileInfo = fileInfoRepository.findByFileName(name);

        if (fileInfo.isPresent()) {
            FileInfo updatedFileInfo = fileInfo.get();

            if (!updatedFileInfo.getLink().equals(fileDownloadLink)) {
                updatedFileInfo.setLink(fileDownloadLink);
            }
            updatedFileInfo.setDateOfUpdate(new Timestamp(System.currentTimeMillis()));

            return fileInfoRepository.save(updatedFileInfo);

        } else {
            FileInfo createdFileInfo = new FileInfo();

            createdFileInfo.setFileName(name);
            createdFileInfo.setLink(fileDownloadLink);
            createdFileInfo.setDateOfCreation(new Timestamp(System.currentTimeMillis()));

            return fileInfoRepository.save(createdFileInfo);
        }
    }
}
