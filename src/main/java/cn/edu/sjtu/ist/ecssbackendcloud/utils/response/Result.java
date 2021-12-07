package cn.edu.sjtu.ist.ecssbackendcloud.utils.response;

import lombok.Data;

/**
 * @author rsp
 * @date 2021/12/6
 */
@Data
public class Result<T> {

    /**
     * 数据
     */
    T data;

    /**
     * 消息
     */
    String message;

    /**
     * 代码
     */
    int code;
}