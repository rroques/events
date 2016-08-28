package nz.co.rroques.config;

import nz.co.rroques.gateway.EventGateway;
import nz.co.rroques.gateway.jpa.JPAEventGateway;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class H2Configuration {

    @Autowired
    private JPAEventGateway jpaEventGateway;

    @Bean
    public EventGateway eventGateway() {
        return jpaEventGateway;
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/h2-console/*");
        registration.addInitParameter("webAllowOthers", "true");
        return registration;
    }
}
