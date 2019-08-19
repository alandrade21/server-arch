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
 * Loads server application properties from a Json file namede <code>applicationProperties.json</code> loaded from the classpath of
 * a server project.
 * 
 * The Json file must be inside the <code>src/main/resources</code> folder in your project.
 * 
 * @author andre
 * @since 0.0.1, 16 de ago de 2019
 */
@PropertySource(value = "classpath:applicationProperties.json", encoding="UTF-8")
@ConfigurationProperties
@Getter
public class ApplicationPropertiesInternal {

	private String hibernateDialect;
	
	private String dataSource;
	
	private String pacoteEntidade;
	
	private String pacoteDao;	
}
