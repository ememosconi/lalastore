package com.alalala.msseed.config.properties;

import com.alalala.msseed.config.properties.model.KafkaProperties;
import com.alalala.msseed.config.properties.model.RedisProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpringConfigurationProperties {
    private KafkaProperties kafka;
    private RedisProperties redis;
}
