package cn.edu.sjtu.ist.ecssbackendcloud.utils;

import cn.edu.sjtu.ist.ecssbackendcloud.annotation.AdminAccess;
import cn.edu.sjtu.ist.ecssbackendcloud.annotation.PassToken;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;
import cn.edu.sjtu.ist.ecssbackendcloud.service.UserService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @brief 认证拦截器
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object) {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有 PassToken 注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 无，则默认全部检查
        try {
            String token = httpServletRequest.getHeader("token"); // 从 http 请求头中取出 token
            // 执行认证
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }

            // 获取 token 中的 user id
            String userId = JWT.decode(token).getAudience().get(0);
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }

            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);

            // 判断用户对方法的访问权限
            if (method.isAnnotationPresent(AdminAccess.class) && !user.isAdmin()) {
                throw new RuntimeException("用户权限不够");
            }
            return true;
        } catch (JWTDecodeException e) {
            throw new RuntimeException("JWT token解码出错");
        } catch (JWTVerificationException e) {
            throw new RuntimeException("JWT验证出错");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse,
                            Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {
    }
}