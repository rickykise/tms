package ai.fassto.tms.domain.parcel.application.port.output.api.cj;

import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResultDto;

public interface CjParcelApi {
    CjAddrResultDto checkAddress(Destination sender, Destination receiver, Box box);
}
