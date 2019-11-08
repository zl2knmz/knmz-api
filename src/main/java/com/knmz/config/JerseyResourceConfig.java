package com.knmz.config;

import com.knmz.Application;
import com.knmz.exception.JerseyExceptionHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.util.Set;

/**
 * JerseyResourceConfig
 *
 * @Author: chenzeping
 * @Date: 2019/7/23 18:49
 */
@Component
public class JerseyResourceConfig extends ResourceConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(JerseyResourceConfig.class);

    public JerseyResourceConfig() {
        super();
        register(JerseyExceptionHandler.class);
        Reflections reflections = new Reflections(Application.class.getPackage().getName());
        Set<Class<?>> resourceClasses = reflections.getTypesAnnotatedWith(Path.class);
        for (Class<?> resource : resourceClasses) {
            if (Resource.isAcceptable(resource)) {
                register(resource);
                LOGGER.info("register resource class: {}", resource);
            }
        }
        //register(FastJsonFeature.class);
    }
}
