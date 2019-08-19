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

import lombok.Getter;

/**
 * @author andre
 * @since 0.0.1, 15 de ago de 2019
 */
public enum EnvironmentEnum {
	
	DEVELOP("Development"), HOMOLOG("Homologation"), PROD("Production");
	
	@Getter
	private String environmentName;

	private EnvironmentEnum(String environmentName) {
		this.environmentName = environmentName;
	}	
}
