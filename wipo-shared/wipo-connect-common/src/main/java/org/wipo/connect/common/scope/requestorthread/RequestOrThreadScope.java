/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.scope.requestorthread;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.wipo.connect.common.scope.thread.ThreadScope;

public class RequestOrThreadScope implements Scope {
	private final Scope requestScope = new RequestScope();
	private final Scope fallbackScope = new ThreadScope();

	private Scope activeScope() {
		if (RequestContextHolder.getRequestAttributes() != null) {
			return requestScope;
		} else {
			return fallbackScope;
		}
	}

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		return activeScope().get(name, objectFactory);
	}

	@Override
	public String getConversationId() {
		return activeScope().getConversationId();
	}

	@Override
	public void registerDestructionCallback(String arg0, Runnable arg1) {
		activeScope().registerDestructionCallback(arg0, arg1);
	}

	@Override
	public Object remove(String arg0) {
		return activeScope().remove(arg0);
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		return activeScope().resolveContextualObject(arg0);
	}

}
