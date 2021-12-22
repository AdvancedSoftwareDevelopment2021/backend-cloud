package cn.edu.sjtu.ist.ecssbackendcloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @brief 标注管理员角色才能访问的方法
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminAccess {
}
