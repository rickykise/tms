package ai.fassto.tms.externalservice.parcel.cj.mapper;

import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrRequestParamDto;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResponseDto;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResultDto;
import org.springframework.stereotype.Component;

@Component
public class CjParcelApiDataMapper {
    public CjAddrRequestParamDto toCjAddrRequestParamDto(Destination sender, Destination receiver, Box box) {
        return CjAddrRequestParamDto.builder()
                .senderAddr(sender.getAddress().fullAddress())
                .receiverAddr(receiver.getAddress().fullAddress())
                .boxType(box.getCjBoxType())
                .build();
    }

    public CjAddrResultDto toCjAddrResultDto(CjAddrResponseDto responseDto) {
        return CjAddrResultDto.builder()
                .endNo(responseDto.dlvClsfCd())
                .subEndNo(responseDto.dlvSubClsfCd())
                .manBranNm(responseDto.dlvPreArrBranNm())
                .cldvEmpNm(responseDto.dlvPreArrEmpNm())
                .cldvEmpClsCd(responseDto.dlvPreArrEmpNickNm())
                .shortAddr(responseDto.rcvrClsfAddr())
                .fareDiv(responseDto.fareDiv())
                .bscFare(responseDto.bscFare())
                .dealFare(responseDto.dealFare())
                .ferryFare(responseDto.ferryFare())
                .checkedSenderZipCode(responseDto.senderZipCode())
                .checkedSenderAddress(responseDto.checkedSenderAddress())
                .checkedSenderAddressDetail(responseDto.checkedSenderAddressDetail())
                .checkedReceiverZipCode(responseDto.receiverZipCode())
                .checkedReceiverAddress(responseDto.checkedReceiverAddress())
                .checkedReceiverAddressDetail(responseDto.checkedReceiverAddressDetail())
                .build();
    }
}
