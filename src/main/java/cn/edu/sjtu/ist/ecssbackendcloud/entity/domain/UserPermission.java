package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain;

/**
 * @brief 用户权限枚举类
 * @author rsp
 * @date 2021-12-21
 */
public enum UserPermission {
    /**
     * 管理员
     */
    ADMIN("admin"),

    /**
     * 普通用户
     */
    USER("user");

    /**
     * 类型
     */
    private final String permission;

    /**
     * 构造函数
     * @param permission 权限名称
     */
    UserPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取权限
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 从字符串转为文本
     *
     * @param text 文本
     */
    public static UserPermission fromString(String text) {
        for (UserPermission b : UserPermission.values()) {
            if (b.permission.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
