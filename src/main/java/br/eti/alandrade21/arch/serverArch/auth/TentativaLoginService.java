/*
 * Copyright (c) 2019 André Andrade - alandrade21@gmail.com
 * 
 * This file is part of the "Autorizador" system.
 *
 * The "Autorizador" system is a property system and its font code is classified.
 * It is not allowed the "Autorizador" system in its binary, font code, or in any other formats 
 * without the express authorization of te copyright owner.
 */
package br.eti.alandrade21.arch.serverArch.auth;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Esta classe monta um mapa de todos os endereços IP que falharam ao tentar um login no autenticador.
 * 
 * Ao atingir um determinado número de tentativas consecutivas de login com falha, este IP é bloqueado.
 * 
 * Um login com sucesso apaga o IP da lista.
 * 
 * O objetivo é minimizar o efeito de um ataque de flood.
 * 
 * @author andre
 * @since 0.0.1, 19 de ago de 2019
 */
// @Service
public class TentativaLoginService {

	private LoadingCache<String, Integer> attemptsCache;
	public static final int MAX_ATTEMPT = 10;

	public TentativaLoginService() {
		this.attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
			@Override
			public Integer load(final String key) {
				return 0;
			}
		});
	}

	public boolean isBlocked(final String key) {
		try {
			return this.attemptsCache.get(key) >= MAX_ATTEMPT;
		} catch (final ExecutionException e) {
			return false;
		}
	}

	public void loginFailed(final String key) {
		int attempts = 0;
		try {
			attempts = this.attemptsCache.get(key);
		} catch (final ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		this.attemptsCache.put(key, attempts);
	}

	public void loginSucceeded(final String key) {
		this.attemptsCache.invalidate(key);
	}
	
	// TODO colocar um mecanismo que desbloqueia um IP.

}
