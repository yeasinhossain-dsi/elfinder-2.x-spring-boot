package org.grapheco.web.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * the DefaultPropertySourceFactory can't read complicated configuration in the
 * yml,here is the alternative plan, from
 * https://stackoverflow.com/questions/21271468/spring-propertysource-using-yaml/51392715
 * 
 * @author little-tiger
 * 
 */
public class YamlPropertyLoaderFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(encodedResource.getResource());
		Properties properties = factory.getObject();
		return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
	}
}
