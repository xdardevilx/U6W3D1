package valerio.U6W3D1.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class ServerConfig {
    @Bean
    public Cloudinary uploaderImg(@Value("${cloudinary.cloudName}") String cloudName,
                                  @Value("${cloudinary.apiKey}") String apiKey,
                                  @Value("${cloudinary.apiSecret}") String apiSecret) {
        Map<String, String> confing = new HashMap<>();
        confing.put("cloud_name", cloudName);
        confing.put("api_key", apiKey);
        confing.put("api_secret", apiSecret);
        return new Cloudinary(confing);


    }




}
