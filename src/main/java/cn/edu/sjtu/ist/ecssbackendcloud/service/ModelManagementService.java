package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelEdgeDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelInfoDTO;

import java.util.List;

public interface ModelManagementService {

    ModelInfoDTO addModel(ModelInfoDTO modelInfoDto);

    void deleteModelInfoById(String modelId);

    ModelInfoDTO updateModelInfoById(String modelId, ModelInfoDTO modelInfoDto);

    List<ModelInfoDTO> getAllModelInfoByUser(String userId);

    List<ModelInfoDTO> getAllModelInfo();

    ModelInfoDTO getModelInfoById(String modelId);

    List<ModelEdgeDTO> getModelEdgeList(String modelId);

    Boolean issueModel(String id, String ip, String port);
}
