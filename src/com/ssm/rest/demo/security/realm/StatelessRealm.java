package com.ssm.rest.demo.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.rest.demo.common.utils.CodecUtil;
import com.ssm.rest.demo.service.IUserService;

public class StatelessRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService userService;
	
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
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
        StatelessToken statelessToken = (StatelessToken) token;
        String username = statelessToken.getUsername();
        String key = getKey(username);//根据用户名获取密钥（和客户端的一样）
        //在服务器端生成客户端参数消息摘要
        String serverDigest = CodecUtil.encodeSHA256(key, statelessToken.getPassword());
        System.out.println(statelessToken.getClientDigest());
        System.out.println(serverDigest);
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(
                username,
                serverDigest,
                getName());
    }

    private String getKey(String username) {//得到密钥，此处硬编码一个
        if("admin".equals(username)) {
            return "dadadswdewq2ewdwqdwadsadasd";
        }
        return null;
    }
}
