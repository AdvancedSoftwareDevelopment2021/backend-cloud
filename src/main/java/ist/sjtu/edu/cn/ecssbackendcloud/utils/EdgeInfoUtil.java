package ist.sjtu.edu.cn.ecssbackendcloud.utils;

import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.EdgeInfoDto;
import ist.sjtu.edu.cn.ecssbackendcloud.entity.po.EdgeInfoPO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EdgeInfoUtil {

    public EdgeInfoPO convertDtoToPo(EdgeInfoDto edgeInfoDto, Date timestamp) {
        EdgeInfoPO edgeInfoPO = new EdgeInfoPO();
        edgeInfoPO.setName(edgeInfoDto.getName());
        edgeInfoPO.setDescription(edgeInfoDto.getDescription());
        edgeInfoPO.setApi(edgeInfoDto.getApi());
        edgeInfoPO.setAddress(edgeInfoDto.getAddress());
        edgeInfoPO.setPort(edgeInfoDto.getPort());
        edgeInfoPO.setRegisterTimestamp(timestamp);
        edgeInfoPO.setInterval(String.valueOf(edgeInfoDto.getInterval()));
        edgeInfoPO.setTimeUnit(edgeInfoDto.getTimeUnit());
        return edgeInfoPO;
    }

}
