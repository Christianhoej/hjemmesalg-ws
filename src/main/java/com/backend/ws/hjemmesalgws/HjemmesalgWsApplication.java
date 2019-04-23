package com.backend.ws.hjemmesalgws;

import com.backend.ws.hjemmesalgws.security.Application_Properties_Reader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Extending SpringBootServletInit og Overriding configure.... gør at programmet kan køre som en WAR fil i en apache tomcat server.
//Herudover skal der også ædres i pom.xml filen. Packaging skal ændres fra jar til war
//også i pom.xml skla der tilføres en dependency, som gør at Apache Tomcat dependencies tilføres ved "runtime" og ikke compile-time.
@SpringBootApplication
public class HjemmesalgWsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(HjemmesalgWsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HjemmesalgWsApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SpringApplicationContext springApplicationContext (){
        return new SpringApplicationContext();
    }
    @Bean(name="Application_Properties_Reader")
    public Application_Properties_Reader getApplication_PropertiesReader(){
        return new Application_Properties_Reader();
    }

}
