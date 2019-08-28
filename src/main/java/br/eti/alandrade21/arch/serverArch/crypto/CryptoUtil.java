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
package br.eti.alandrade21.arch.serverArch.crypto;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class with criptography services.
 * 
 * @author andre.andrade
 * @since 0.0.1, 15 de ago de 2019
 */
public class CryptoUtil {
	
	/**
	 * Generate a MD5 digest for an object.
	 * 
	 * This code is based on a solution used by spring.
	 *  
	 * @author andre.andrade
	 * @since 0.0.1, 15 de ago de 2019
	 *
	 * @param value Base objeto to generate the MD5 digest. The toSting() methos of this object will be called during the MD5 digest generation.
	 * @return a 32 characters String, containing hexadecimal digits.
	 */
	public static String generateMD5Digest(Object value) {
		
		if (value == null) {
			return null;
		}
		
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
		}

		try {
			byte[] bytes = digest.digest(value.toString().getBytes("UTF-8"));
			
			// 032x means: 
			// 0: Fill the string withe left zeroes,
			// 32: Until a maximum lenght of 32 characters.
			// x: Each character is a hexadecimal digit.
			return String.format("%032x", new BigInteger(1, bytes));
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
		}
	}
}
