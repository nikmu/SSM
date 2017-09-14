package com.ssm.demo.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.demo.model.TokenModel;
import com.ssm.demo.security.TokenException;
import com.ssm.demo.security.TokenManager;
import com.ssm.demo.service.system.IUserService;

public class StatelessRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private TokenManager tokenManager;
	
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof TokenModel;
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        TokenModel tokenModel = (TokenModel) token;
        String mytoken = tokenModel.getToken();
        String username = tokenManager.getUser(mytoken);
        if (username == null) {
        	String message = String.format("token [%s] is invalid", mytoken);
        	throw new TokenException(message);
		}
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(
                username,
                mytoken,
                getName());
    }
}
