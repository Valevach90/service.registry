package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.FileInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface presents endpoints for working with file storage.
 */

@Tag(name = "File storage controller", description = "endpoints to work with file storages")
@RequestMapping(value = "/api/v1/files")
public interface FileStorageController {

    @Operation(summary = "Get all FileInfoDto",
            description = "get list of all File Information Dto"
    )
    @GetMapping
    List<FileInfoDto> getAllFileInfoDto(
    );

    @Operation(summary = "Get file download link",
            description = "get file download link by file name")
    @GetMapping(value = "/link/{name}")
    String getFileDownloadLink(
            @Parameter(description = "file name", required = true)
            @PathVariable ("name") String name);

    @Operation(summary = "Get files download links",
            description = "get files download links for series of files names")
    @GetMapping(value = "/info/{seriesOfFilesNames}")
    List<FileInfoDto> getFilesDownloadLinks(
            @Parameter(description = "series of files names", required = true)
            @PathVariable ("seriesOfFilesNames") String... seriesOfFilesNames);
}
