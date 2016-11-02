package com.promise.task.controller;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedServerConfig implements EmbeddedServletContainerCustomizer
{

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container)
    {
        container.setPort(8001);

    }

}
