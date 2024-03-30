package com.quyennv.lms.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.quyennv.lms.data.db.jpa.entities"})
@EnableJpaRepositories(basePackages = {"com.quyennv.lms.data.db.jpa.repositories"})
public class DbConfig {
}
