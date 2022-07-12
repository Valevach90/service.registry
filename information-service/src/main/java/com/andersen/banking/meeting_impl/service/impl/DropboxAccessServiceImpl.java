package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_impl.exception.FileStorageServiceException;
import com.andersen.banking.meeting_impl.service.DropboxAccessService;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.sharing.ListSharedLinksResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DropboxAccessServiceImpl implements DropboxAccessService {

    private static final String DROPBOX_FOLDER_PATH = "";
    private static final String DROPBOX_DOWNLOAD_DISABLED = "?dl=0";
    private static final String DROPBOX_DOWNLOAD_ENABLED = "?dl=1";

    @Value("${dropbox.access.token}")
    private String accessToken;

    private DbxClientV2 client;

    @PostConstruct
    public void init() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("${spring.application.name}").build();

        client = new DbxClientV2(config, accessToken);
    }

    @Override
    public List<String> findAllFilesNames() {
        log.debug("Find all stored files names");

        try {
            ListFolderResult result = client.files().listFolder(DROPBOX_FOLDER_PATH);

            List<String> listOfNames = result.getEntries().stream().map(metadata -> metadata.getName()).toList();

            log.debug("Return list of names of all stored files: {}", listOfNames);

            return listOfNames;

        } catch (DbxException e) {
            log.error(e.getMessage());
            throw new FileStorageServiceException("Problem to get list of all files names");
        }
    }

    @Override
    public Optional<String> findDownloadLinkByFileName(String name) {

        log.debug("Find download link for file with name {}", name);

        try {
            ListSharedLinksResult linksResult = client.sharing().listSharedLinksBuilder()
                    .withPath("/"+ name)
                    .withDirectOnly(true)
                    .start();

            String receivedLink = linksResult.getLinks().get(0).getUrl();
            String downloadLink = receivedLink.replace(DROPBOX_DOWNLOAD_DISABLED, DROPBOX_DOWNLOAD_ENABLED);

            log.debug("Return download link by file name: file name {}, download link {}", name, downloadLink);

            return Optional.of(downloadLink);

        } catch (DbxException e) {
            log.error(e.getMessage());
            throw new FileStorageServiceException("Problem to get download link for file "+ name);
        }
    }
}
