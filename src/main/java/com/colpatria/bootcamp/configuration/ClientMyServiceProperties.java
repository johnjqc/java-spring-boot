/*
 * Copyright 2018-${today.year}, Scotiabank - Digital Factory
 *
 * All rights reserved Date: 12/08/20
 */
package com.colpatria.bootcamp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Local providers properties for feign clients
 *
 * @author <a href="quirjohn@colpatria.com">John Quiroga C</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "client.my-service")
public class ClientMyServiceProperties {

	private MyServiceProperties config;

}
