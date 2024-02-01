package com.example.jpanext.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
// Entity Auditing 기능 설정
@EnableJpaAuditing
public class JpaConfig {
}
