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
package org.wipo.connect.common.crypto;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Map;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class CryptoUtils.
 */
public class CryptoUtils {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CryptoUtils.class);

    private CryptoUtils() {
	super();
    }

    /**
     * Removes the cryptography restrictions.
     *
     * Do the following, but with reflection to bypass access checks:
     *
     * JceSecurity.isRestricted = false; JceSecurity.defaultPolicy.perms.clear(); JceSecurity.defaultPolicy.add(CryptoAllPermission.INSTANCE);
     */
    public static void removeCryptographyRestrictions() {
	try {
	    final Class<?> jceSecurity = Class.forName("javax.crypto.JceSecurity");
	    final Class<?> cryptoPermissions = Class.forName("javax.crypto.CryptoPermissions");
	    final Class<?> cryptoAllPermission = Class.forName("javax.crypto.CryptoAllPermission");

	    final Field isRestrictedField = jceSecurity.getDeclaredField("isRestricted");
	    isRestrictedField.setAccessible(true);
	    Field modifiers = Field.class.getDeclaredField("modifiers");
	    modifiers.setAccessible(true);
	    modifiers.setInt(isRestrictedField, isRestrictedField.getModifiers() & ~Modifier.FINAL);
	    isRestrictedField.set(null, false);

	    /*
	     * if ( Boolean.TRUE.equals(isRestrictedField.get(null)) ) { if ( Modifier.isFinal(isRestrictedField.getModifiers()) ) { Field modifiers = Field.class.getDeclaredField("modifiers");
	     * modifiers.setAccessible(true); modifiers.setInt(isRestrictedField, isRestrictedField.getModifiers() & ~Modifier.FINAL); } isRestrictedField.setAccessible(true);
	     * isRestrictedField.setBoolean(null, false); // isRestricted = false; isRestrictedField.setAccessible(false); }
	     */
	    final Field defaultPolicyField = jceSecurity.getDeclaredField("defaultPolicy");
	    defaultPolicyField.setAccessible(true);
	    final PermissionCollection defaultPolicy = (PermissionCollection) defaultPolicyField.get(null);

	    final Field perms = cryptoPermissions.getDeclaredField("perms");
	    perms.setAccessible(true);
	    ((Map<?, ?>) perms.get(defaultPolicy)).clear();

	    final Field instance = cryptoAllPermission.getDeclaredField("INSTANCE");
	    instance.setAccessible(true);
	    defaultPolicy.add((Permission) instance.get(null));

	    LOGGER.info("Successfully removed cryptography restrictions");
	} catch (final Exception e) {
	    LOGGER.error("Failed to remove cryptography restrictions", e);
	}
    }
}
