package ai.fassto.tms.externalservice.parcel.lotte.mapper;

import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteAddrRequestDto;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteAddrResponseDto;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteParcelInfoDto;
import org.springframework.stereotype.Component;

@Component
public class LotteParcelApiDataMapper {
    public LotteAddrRequestDto toLotteAddrRequestDto(Destination sender, Destination receiver, DeliveryInfo deliveryInfo) {
        return LotteAddrRequestDto.builder()
                .id(deliveryInfo.getContractCode())
                .address(receiver.getAddress().getAddress())
                .pick_area_no(sender.getAddress().getZipCode())
                .pick_address(sender.getAddress().fullAddress())
                .build();
    }

    public LotteParcelInfoDto toLotteParcelInfoDto(LotteAddrResponseDto responseDto) {
        return LotteParcelInfoDto.builder()
                .tmlCd(responseDto.tmlCd())
                .tmlNm(responseDto.tmlNm())
                .cityGunGu(responseDto.cityGunGu())
                .eupMuinDong(responseDto.dong())
                .filtCd(responseDto.filtCd())
                .brnshpNm(responseDto.brnshpNm())
                .empNm(responseDto.empNm())
                .shipFare(responseDto.shipFare())
                .airFare(responseDto.airFare())
                .build();
    }
}