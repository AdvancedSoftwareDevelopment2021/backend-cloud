package cn.edu.sjtu.ist.ecssbackendcloud.utils;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EdgeInfoUtil {

    public EdgeInfoPO convertDtoToPo(EdgeInfoDto edgeInfoDto, Date timestamp) {
        EdgeInfoPO edgeInfoPO = new EdgeInfoPO();
        edgeInfoPO.setName(edgeInfoDto.getName());
        edgeInfoPO.setDescription(edgeInfoDto.getDescription());
        edgeInfoPO.setApi(edgeInfoDto.getApi());
        edgeInfoPO.setUrl(edgeInfoDto.getUrl());
        edgeInfoPO.setRegisterTimestamp(timestamp);
        edgeInfoPO.setInterval(String.valueOf(edgeInfoDto.getInterval()));
        edgeInfoPO.setTimeUnit(edgeInfoDto.getTimeUnit());
        return edgeInfoPO;
    }

    public PingInfoRequest createPingBody(EdgeInfoPO edgeInfoPO) {
        PingInfoRequest pingInfoRequest = new PingInfoRequest();
        pingInfoRequest.setId(edgeInfoPO.getId());
        pingInfoRequest.setUrl("http://localhost:8080");
        pingInfoRequest.setTimeUnit(edgeInfoPO.getTimeUnit());
        pingInfoRequest.setInterval(edgeInfoPO.getInterval());
        return pingInfoRequest;
    }

}
