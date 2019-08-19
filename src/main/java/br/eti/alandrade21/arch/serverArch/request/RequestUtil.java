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

package br.eti.alandrade21.arch.serverArch.request;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


/**
 * Utility class to manipulate a Request object.
 * 
 * @author andre
 * @since 0.0.1, 16 de ago de 2019
 */
public class RequestUtil {

	/**
	 * Returns the request origin IP. 
	 * 
	 * The header used to obtain this information depends on the existance of the "X-Forwarded-For" header. If this header exists,
	 * the IP returned will be this list first position. If it not exists, the return will be the result of getRemoteAddr().
	 * 
	 * Note that there is no warranty that the returned IP will be user's computer IP. The IP returned is the request origin IP
	 * (or the last forward node).
	 * 
	 * @author andre
	 * @since 0.0.1, 16 de ago de 2019
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestOriginIP(HttpServletRequest request) {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
	
	/**
	 * Returns the client login name from the Authorization header (OAuth2 authentication proccess), or a empty string
	 * if this header isn't present. 
	 * 
	 * @author andre
	 * @since 0.0.1, 16 de ago de 2019
	 *
	 * @param request 
	 * @return A string with the client login, or a empty string.
	 */
	public static String getClientLogin(HttpServletRequest request){
	    
	    final String authorizationHeaderValue = request.getHeader("Authorization");
	    final String base64AuthorizationHeader = 
	    		Optional.ofNullable(authorizationHeaderValue).map(headerValue->headerValue.substring("Basic ".length())).orElse("");

	    if(StringUtils.isNotEmpty(base64AuthorizationHeader)){
	        String decodedAuthorizationHeader = new String(Base64.getDecoder().decode(base64AuthorizationHeader), Charset.forName("UTF-8"));
	        return decodedAuthorizationHeader.split(":")[0];
	    }

	    return "";
	}
}
