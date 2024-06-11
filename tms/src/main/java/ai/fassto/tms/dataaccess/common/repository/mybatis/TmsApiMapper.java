package ai.fassto.tms.dataaccess.common.repository.mybatis;

import ai.fassto.tms.dataaccess.common.dto.TmsApiResponseDto;
import ai.fassto.tms.dataaccess.common.dto.TmsInsertApiResponseDto;
import ai.fassto.tms.dataaccess.common.dto.TmsSelectApiResponseDto;
import ai.fassto.tms.dataaccess.common.dto.TmsUpdateApiResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TmsApiMapper {
    void insertTmsResponseData(TmsInsertApiResponseDto tmsInsertApiResponseDto);

    void updateTmsResponseData(TmsUpdateApiResponseDto tmsUpdateApiResponseDto);

    TmsApiResponseDto selectTmsResponseData(TmsSelectApiResponseDto tmsSelectApiResponseDto);
}
