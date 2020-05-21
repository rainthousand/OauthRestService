package org.Oauth2RestServices.config;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter 
{
	private MysqlxDatatypes.Scalar.String resourceIds= "employeeInv";
    //!! maked sure that this resourceids is store in oauth_client_details for the clientid I used to get the token

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        	.authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/elearning/course**").authenticated()
    //       	.antMatchers("/media/**").authenticated()
        	;
	}
	
		    @Override
		    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		          resources.resourceId(resourceIds);
		      }
}
