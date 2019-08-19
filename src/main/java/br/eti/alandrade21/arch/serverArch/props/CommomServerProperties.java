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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import lombok.Getter;

/**
 * Loads commom server properties from a Json file named <code>commomServerProperties.json</code>.
 * 
 * The Json file must be on the directory pointed by the TomCat environment variable <code>SERVER_PROPERTIES_FILE_PATH</code>. This 
 * environment variable must contain the absolute path to the directory.
 * 
 * @author andre
 * @since 0.0.1, 15 de ago de 2019
 */
@PropertySource(value="file:${SERVER_PROPERTIES_FILE_PATH}/commomServerProperties.json", encoding="UTF-8")
@ConfigurationProperties
@Getter
public class CommomServerProperties {
	
	private Boolean hibernateShowSQL;
	
	private Boolean hibernateFormatSQL;
	
	private String hibernateHbm2DllAuto;
	
	private Boolean generateSwaggerDocs;
	
	private EnvironmentEnum environmentId;
}
