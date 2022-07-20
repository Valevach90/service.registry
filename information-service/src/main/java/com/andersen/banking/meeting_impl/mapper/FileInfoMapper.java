package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.FileInfoDto;
import com.andersen.banking.meeting_db.entity.FileInfo;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for file information.
 */
@Mapper(config = MapperConfig.class)
public interface FileInfoMapper {

    FileInfoDto toFileInfoDto(FileInfo fileInfo);

    FileInfo toFileInfo(FileInfoDto fileInfoDto);

    List<FileInfoDto> toListFileInfoDto(List<FileInfo> listFileInfo);

    List<FileInfo> toListFileInfo(List<FileInfoDto> listOfFileInfoDto);
}
