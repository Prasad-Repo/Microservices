package com.mytectra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@SpringBootApplication
public class OathSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OathSecurityApplication.class, args);
	}
	
}

@Configuration class AuthenticationManagerProvider extends WebSecurityConfigurerAdapter{
	
	@Bean
	@Override public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
		}
}




@Configuration
@EnableAuthorizationServer
class OauthConfiguration extends AuthorizationServerConfigurerAdapter{
	
	private final AuthenticationManager authenticationManager;
	
	
	public OauthConfiguration(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	//Configure Clients 
	@Override public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
		.withClient("iphone")
		.authorizedGrantTypes("authorization_code","password","refresh_token")
		.scopes("read","write")
		.secret("{noop}iphone")
		.and()
		.withClient("android")
		.authorizedGrantTypes("horization_code","password","refresh_token")
		.scopes("read","write")
		.secret("{noop}android");
	
	}
	
	
	//Configure Users
	@Override public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception{
		super.configure(endpoints);
		
	}
	
	
}
