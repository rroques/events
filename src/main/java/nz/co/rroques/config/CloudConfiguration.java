package nz.co.rroques.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("cloud")
public class CloudConfiguration extends AbstractCloudConfig {

        @Bean
        public DataSource dataSource() {
            return cloud().getSingletonServiceConnector(DataSource.class, null);
        }


}
