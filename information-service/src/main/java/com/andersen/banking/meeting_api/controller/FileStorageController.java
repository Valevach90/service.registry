package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.dto.FileInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping(value = "/link")
    String getFileDownloadLink(
            @Parameter(description = "file name", required = true)
            @RequestParam("name") String name);

    @Operation(summary = "Get file(s) info with download link(s)",
            description = "get file(s) info with download link(s)")
    @GetMapping(value = "/info")
    List<FileInfoDto> getFilesDownloadLinks(
            @Parameter(description = "file(s) name(s)", required = true)
            @RequestParam("name") String... name);
}
