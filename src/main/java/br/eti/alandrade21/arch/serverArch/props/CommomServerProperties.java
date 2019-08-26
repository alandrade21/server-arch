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
 * Loads commom server properties from a Json file named <code>commomServerProperties.json</code>.
 * 
 * The Json file must be on the directory pointed by the TomCat environment variable <code>SERVER_PROPERTIES_FILE_PATH</code>. This 
 * environment variable must contain the absolute path to the directory.
 * 
 * @author andre
 * @since 0.0.1, 15 de ago de 2019
 */
@Component
@PropertySource(value="file:${SERVER_PROPERTIES_FILE_PATH}/commomServerProperties.json", encoding="UTF-8", 
				factory=CommomServerProperties.JsonPropertySourceFactory.class)
@ConfigurationProperties
@Getter
@Setter
public class CommomServerProperties {
	
	private Boolean hibernateShowSQL;
	
	private Boolean hibernateFormatSQL;
	
	private String hibernateHbm2DllAuto;
	
	private Boolean generateSwaggerDocs;
	
	private EnvironmentEnum environmentId;
	
	public static class JsonPropertySourceFactory implements PropertySourceFactory{

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
	    public org.springframework.core.env.PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
			
	        Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
	        return new MapPropertySource("CommomServerProperties", readValue);
	    }
	}
}
