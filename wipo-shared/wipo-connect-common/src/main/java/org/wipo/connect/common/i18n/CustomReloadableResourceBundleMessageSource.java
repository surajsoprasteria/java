package org.wipo.connect.common.i18n;

import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class CustomReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getAllProperties(Locale locale, boolean cleanCache) {
	if (cleanCache) {
	    clearCacheIncludingAncestors();
	}
	PropertiesHolder propertiesHolder = getMergedProperties(locale);
	return propertiesHolder.getProperties();

    }

    public Set<String> getMessageKeys(Locale locale, boolean cleanCache) {
	return getAllProperties(locale, cleanCache).keySet().stream().map(k -> k.toString()).collect(Collectors.toSet());
    }

}