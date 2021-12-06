package cn.edu.sjtu.ist.ecssbackendcloud.utils.response;

import static cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultCode.SUCCESS;

/**
 * @author rsp
 * @date 2021/12/6
 */
public class ResultUtil {
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMessage(SUCCESS.getMessage());
        result.setCode(SUCCESS.getCode());
        return result;
    }

    public static Result<String> success() {
        Result<String> result = new Result<>();
        result.setData("");
        result.setMessage(SUCCESS.getMessage());
        result.setCode(SUCCESS.getCode());
        return result;
    }

    public static <T> Result<T> failure(String message, int code) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        System.out.println(message);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        return failure(resultCode.getMessage(), resultCode.getCode());
    }
}

