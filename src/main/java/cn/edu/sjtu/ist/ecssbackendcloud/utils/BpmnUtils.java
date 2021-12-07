package cn.edu.sjtu.ist.ecssbackendcloud.utils;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ssingualrity
 * 该类作为BPMN一些常用操作的集合工具类
 */
public class BpmnUtils {

    private static String charsetName = "utf-8";

    public static String getExtensionFrom(BaseElement baseElement, String key) {
        ExtensionElements extensionElements = baseElement.getExtensionElements();
        if (extensionElements == null) {
            return null;
        }
        for (CamundaProperty property : extensionElements.getElementsQuery().filterByType(CamundaProperty.class).list()) {
            if (property.getCamundaName().equals(key)) {
                return property.getCamundaValue();
            }
        }
        return null;
    }

    /**
     * 获取某一element的全部属性
     */
    public static Map<String, String> getExtensionByElement(BpmnModelInstance instance, String elementId) {
        FlowElement element = instance.getModelElementById(elementId);
        if (element == null) {
//            throw new ServiceException(ResultCode.INCORRECT_BPMN_FORMAT);
            throw new RuntimeException("INCORRECT_BPMN_FORMAT");
        }
        Map<String, String> propertyMap = new HashMap<>();

        ExtensionElements extensionElements = element.getExtensionElements();
        if (extensionElements != null) {
            for (CamundaProperty property : extensionElements.getElementsQuery().filterByType(CamundaProperty.class).list()) {
                propertyMap.put(property.getCamundaName(), property.getCamundaValue());
            }
        }
        return propertyMap;
    }

    public static String getExtensionValue(BpmnModelInstance instance, String elementId, String key) {
        ExtensionElements extensionElements = getExtensionElement(instance, elementId);
        if (extensionElements != null) {
            for (CamundaProperty property : extensionElements.getElementsQuery().filterByType(CamundaProperty.class).list()) {
                if (property.getCamundaName().equals(key)) {
                    return property.getCamundaValue();
                }
            }
        }
        return null;
    }

    public static BpmnModelInstance addExtension(BpmnModelInstance instance, String elementId, String key, String value) {
        ExtensionElements extensionElements = getExtensionElement(instance, elementId);
        CamundaProperty property = extensionElements.addExtensionElement(CamundaProperty.class);
        property.setCamundaName(key);
        property.setCamundaValue(value);
        return instance;
    }

    public static BpmnModelInstance setExtension(BpmnModelInstance instance, String elementId, String key, String value) {
        ExtensionElements extensionElements = getExtensionElement(instance, elementId);
        for (CamundaProperty property : extensionElements.getElementsQuery().filterByType(CamundaProperty.class).list()) {
            if (property.getCamundaName().equals(key)) {
                extensionElements.removeChildElement(property);
                break;
            }
        }
        return addExtension(instance, elementId, key, value);
    }

    public static BpmnModelInstance deleteExtension(BpmnModelInstance instance, String elementId, String key){
        ExtensionElements extensionElements = getExtensionElement(instance, elementId);
        for (CamundaProperty property : extensionElements.getElementsQuery().filterByType(CamundaProperty.class).list()) {
            if (property.getCamundaName().equals(key)) {
                extensionElements.removeChildElement(property);
                break;
            }
        }
        return instance;
    }

    private static ExtensionElements getExtensionElement(BpmnModelInstance instance, String elementId) {
        FlowElement element = instance.getModelElementById(elementId);
        if (element == null) {
//            throw new ServiceException(ResultCode.INCORRECT_BPMN_FORMAT);
            throw new RuntimeException("INCORRECT_BPMN_FORMAT");
        }
        ExtensionElements extensionElements = element.getExtensionElements();
        if (extensionElements == null) {
            extensionElements = instance.newInstance(ExtensionElements.class);
            element.setExtensionElements(extensionElements);
        }
        return extensionElements;
    }

    public static String bpmnInstToStr(BpmnModelInstance instance) {
        try {
            return Bpmn.convertToString(instance);
        } catch (Exception e) {
            throw new RuntimeException("FILE_CONVERT_ERROR");
//            throw new ServiceException(ResultCode.FILE_CONVERT_ERROR);
        }
    }

    public static String multiFileToStr(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("FILE_CONVERT_ERROR");
//            throw new ServiceException(ResultCode.FILE_CONVERT_ERROR);
        }
    }

    public static InputStream strToInStream(String str) {
        try {
            return new ByteArrayInputStream(str.getBytes(charsetName));
        } catch (Exception e) {
            throw new RuntimeException("FILE_CONVERT_ERROR");
//            throw new ServiceException(ResultCode.FILE_CONVERT_ERROR);
        }
    }
}
