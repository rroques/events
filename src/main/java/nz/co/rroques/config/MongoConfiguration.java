package nz.co.rroques.config;

import com.mongodb.MongoClient;
import nz.co.rroques.gateway.EventGateway;
import nz.co.rroques.gateway.jpa.JPAEventGateway;
import nz.co.rroques.gateway.mongo.MongoEventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("nz.co.rroques.gateway.mongo")
@Profile("test")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private MongoEventGateway mongoEventGateway;

    @Bean
    public EventGateway eventGateway() {
        return mongoEventGateway;
    }

    @Override
    protected String getDatabaseName() {
        return "events";
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient();
    }
}
