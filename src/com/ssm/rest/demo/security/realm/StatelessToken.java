package com.ssm.rest.demo.security.realm;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String clientDigest;


    public StatelessToken(String username, String password, String clientDigest) {
		super();
		this.username = username;
		this.password = password;
		this.clientDigest = clientDigest;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientDigest() {
		return clientDigest;
	}

	public void setClientDigest(String clientDigest) {
		this.clientDigest = clientDigest;
	}

	@Override
    public Object getPrincipal() {
       return username;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }
}
