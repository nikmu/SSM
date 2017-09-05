package com.ssm.rest.demo.security;

import com.ssm.rest.demo.common.WebContext;
import com.ssm.rest.demo.common.utils.StringUtil;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

	private static final String DEFAULT_TOKEN_NAME = "X-Token";
	
	private TokenManager tokenManager;
	private String tokenName;
	
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	public void setTokenName(String tokenName) {
		if (StringUtil.isEmpty(tokenName)) {
			tokenName = DEFAULT_TOKEN_NAME;
		}
		this.tokenName = tokenName;
	}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void controllerMethod() {}
	
	@Around("controllerMethod()")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable{
		// 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // 若目标方法忽略了安全性检查，则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }
        // 从 request header 中获取当前 token
        String token = WebContext.getRequest().getHeader(tokenName);
        // 检查 token 有效性
        if (!tokenManager.checkToken(token)) {
            String message = String.format("token [%s] is invalid", token);
            throw new TokenException(message);
        }
        // 调用目标方法
        return pjp.proceed();
	}
	
	public void before() {
		System.out.println("before !!!");
	}
}
