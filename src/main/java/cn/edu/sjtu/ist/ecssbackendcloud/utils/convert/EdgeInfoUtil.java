package cn.edu.sjtu.ist.ecssbackendcloud.utils.convert;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EdgeInfoUtil {

    public EdgeInfoPO convertDtoToPo(EdgeInfoDTO edgeInfoDto, Date timestamp) {
        EdgeInfoPO edgeInfoPO = new EdgeInfoPO();
        edgeInfoPO.setName(edgeInfoDto.getName());
        edgeInfoPO.setDescription(edgeInfoDto.getDescription());
        edgeInfoPO.setApi(edgeInfoDto.getApi());
        edgeInfoPO.setIp(edgeInfoDto.getIp());
        edgeInfoPO.setPort(edgeInfoDto.getPort());
        edgeInfoPO.setRegisterTimestamp(timestamp);
        edgeInfoPO.setInterval(edgeInfoDto.getInterval());
        edgeInfoPO.setTimeUnit(edgeInfoDto.getTimeUnit());
        return edgeInfoPO;
    }

    public EdgeInfoDTO convertPO2DTO(EdgeInfoPO edgeInfoPO){
        EdgeInfoDTO edgeInfoDTO = new EdgeInfoDTO();
        edgeInfoDTO.setId(edgeInfoPO.getId());
        edgeInfoDTO.setName(edgeInfoPO.getName());
        edgeInfoDTO.setDescription(edgeInfoPO.getDescription());
        edgeInfoDTO.setApi(edgeInfoPO.getApi());
        edgeInfoDTO.setIp(edgeInfoPO.getIp());
        edgeInfoDTO.setPort(edgeInfoPO.getPort());
        edgeInfoDTO.setRegisterTimestamp(edgeInfoPO.getRegisterTimestamp());
        edgeInfoDTO.setInterval(edgeInfoPO.getInterval());
        edgeInfoDTO.setTimeUnit(edgeInfoPO.getTimeUnit());
        edgeInfoDTO.setStatus(edgeInfoPO.getStatus());
        return edgeInfoDTO;

    }


    public PingInfoRequest createPingBody(EdgeInfoPO edgeInfoPO) {
        PingInfoRequest pingInfoRequest = new PingInfoRequest();
        pingInfoRequest.setId(edgeInfoPO.getId());
        pingInfoRequest.setUrl("http://localhost:8080");
        pingInfoRequest.setTimeUnit(edgeInfoPO.getTimeUnit().toString());
        pingInfoRequest.setInterval(edgeInfoPO.getInterval().toString());
        return pingInfoRequest;
    }

}
