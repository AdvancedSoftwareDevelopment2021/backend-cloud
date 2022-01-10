package cn.edu.sjtu.ist.ecssbackendcloud.utils.convert;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.IssueModelRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.ModelInfoPO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Component
public class ModelInfoUtil {

    public ModelInfoPO convertDtoToPo(ModelInfoDTO modelInfoDto, Date timestamp) {
        ModelInfoPO modelInfoPO = new ModelInfoPO();
        modelInfoPO.setName(modelInfoDto.getName());
        modelInfoPO.setDescription(modelInfoDto.getDescription());
        modelInfoPO.setRegisterTimestamp(timestamp);
        modelInfoPO.setInterval(modelInfoDto.getInterval());
        modelInfoPO.setTimeUnit(modelInfoDto.getTimeUnit());
        modelInfoPO.setOwner(modelInfoDto.getOwner());
        modelInfoPO.setFile(modelInfoDto.getFile());
        return modelInfoPO;
    }

    public ModelInfoDTO convertPO2DTO(ModelInfoPO modelInfoPO){
        ModelInfoDTO modelInfoDTO = new ModelInfoDTO();
        modelInfoDTO.setId(modelInfoPO.getId());
        modelInfoDTO.setName(modelInfoPO.getName());
        modelInfoDTO.setDescription(modelInfoPO.getDescription());
        modelInfoDTO.setRegisterTimestamp(modelInfoPO.getRegisterTimestamp());
        modelInfoDTO.setInterval(modelInfoPO.getInterval());
        modelInfoDTO.setTimeUnit(modelInfoPO.getTimeUnit());
        modelInfoDTO.setOwner(modelInfoPO.getOwner());
        modelInfoDTO.setFile(modelInfoPO.getFile());
        return modelInfoDTO;

    }

    public static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
