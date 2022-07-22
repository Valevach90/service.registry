package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.FileInfoDto;
import com.andersen.banking.meeting_db.entities.FileInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-22T17:07:12+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class FileInfoMapperImpl implements FileInfoMapper {

    @Override
    public FileInfoDto toFileInfoDto(FileInfo fileInfo) {
        if ( fileInfo == null ) {
            return null;
        }

        FileInfoDto fileInfoDto = new FileInfoDto();

        fileInfoDto.setId( fileInfo.getId() );
        fileInfoDto.setFileName( fileInfo.getFileName() );
        fileInfoDto.setLink( fileInfo.getLink() );
        fileInfoDto.setDateOfCreation( fileInfo.getDateOfCreation() );
        fileInfoDto.setDateOfUpdate( fileInfo.getDateOfUpdate() );

        return fileInfoDto;
    }

    @Override
    public FileInfo toFileInfo(FileInfoDto fileInfoDto) {
        if ( fileInfoDto == null ) {
            return null;
        }

        FileInfo fileInfo = new FileInfo();

        fileInfo.setId( fileInfoDto.getId() );
        fileInfo.setFileName( fileInfoDto.getFileName() );
        fileInfo.setLink( fileInfoDto.getLink() );
        fileInfo.setDateOfCreation( fileInfoDto.getDateOfCreation() );
        fileInfo.setDateOfUpdate( fileInfoDto.getDateOfUpdate() );

        return fileInfo;
    }

    @Override
    public List<FileInfoDto> toListFileInfoDto(List<FileInfo> listFileInfo) {
        if ( listFileInfo == null ) {
            return null;
        }

        List<FileInfoDto> list = new ArrayList<FileInfoDto>( listFileInfo.size() );
        for ( FileInfo fileInfo : listFileInfo ) {
            list.add( toFileInfoDto( fileInfo ) );
        }

        return list;
    }

    @Override
    public List<FileInfo> toListFileInfo(List<FileInfoDto> listOfFileInfoDto) {
        if ( listOfFileInfoDto == null ) {
            return null;
        }

        List<FileInfo> list = new ArrayList<FileInfo>( listOfFileInfoDto.size() );
        for ( FileInfoDto fileInfoDto : listOfFileInfoDto ) {
            list.add( toFileInfo( fileInfoDto ) );
        }

        return list;
    }
}
