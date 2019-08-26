/*
 * Copyright (c) 2019 André Andrade - alandrade21@gmail.com
 * 
 * This file is part of the "server-arch" library.
 *
 * "server-arch" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * "server-arch" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "server-arch".  If not, see <https://www.gnu.org/licenses/>.
 */

package br.eti.alandrade21.arch.serverArch.props;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * Loads server application properties from a Json file namede <code>applicationProperties.json</code> loaded from the classpath of
 * a server project.
 * 
 * The Json file must be inside the <code>src/main/resources</code> folder in your project.
 * 
 * @author andre
 * @since 0.0.1, 16 de ago de 2019
 */
@Component
@PropertySource(value="classpath:/applicationProperties.json", encoding="UTF-8",  
				factory=ApplicationPropertiesInternal.JsonPropertySourceFactory.class)
@ConfigurationProperties
@Getter
@Setter
public class ApplicationPropertiesInternal {

	private String hibernateDialect;
	
	private String dataSource;
	
	private String entityPackage;
	
	private String daoPackage;
	
	public static class JsonPropertySourceFactory implements PropertySourceFactory{

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
	    public org.springframework.core.env.PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
			
	        Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
	        return new MapPropertySource("ApplicationPropertiesInternal", readValue);
	    }
	}
}
