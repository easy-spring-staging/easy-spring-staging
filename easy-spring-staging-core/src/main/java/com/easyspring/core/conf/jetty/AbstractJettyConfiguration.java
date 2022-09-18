/**
 * Copyright(C) 2021 Company:easy-spring-staging Co.
 */
package com.easyspring.core.conf.jetty;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

import javax.servlet.HttpConstraintElement;
import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity;
import java.util.Arrays;
import java.util.List;

/**
 * jetty抽象配置类.
 *
 * @author caobaoyu
 * @create 2021-09-10 15:22
 **/
public abstract class AbstractJettyConfiguration {

    public ConfigurableServletWebServerFactory creatJetty(int maxThreadCount, int minThreadCount, int idleTimeout) throws Exception {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory() {
            @Override
            protected void postProcessWebAppContext(WebAppContext webAppContext) {
                //禁止trace请求
                HttpConstraintElement disable = new HttpConstraintElement(ServletSecurity.EmptyRoleSemantic.DENY);
                HttpMethodConstraintElement trace = new HttpMethodConstraintElement("TRACE", disable);
                ServletSecurityElement sse = new ServletSecurityElement(Arrays.asList(trace));
                List<ConstraintMapping> mappings = ConstraintSecurityHandler.createConstraintsWithMappingsForPath("disable", "/*", sse);

                ConstraintSecurityHandler csh = new ConstraintSecurityHandler();
                csh.setConstraintMappings(mappings);
                webAppContext.setSecurityHandler(csh);
            }
        };
        factory.addServerCustomizers(jettyServerCustomizer(maxThreadCount, minThreadCount, idleTimeout));

        return factory;
    }

    private JettyServerCustomizer jettyServerCustomizer(int maxThreadCount, int minThreadCount, int idleTimeout) throws Exception {
        return server -> {
            threadPool(server, maxThreadCount, minThreadCount, idleTimeout);
        };
    }

    private void threadPool(Server server, int maxThreadCount, int minThreadCount, int idleTimeout) {
        // Tweak the connection config used by Jetty to handle incoming HTTP
        // connections
        final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
        //默认最大线程连接数200
        threadPool.setMaxThreads(maxThreadCount);
        //默认最小线程连接数8
        threadPool.setMinThreads(minThreadCount);
        //默认线程最大空闲时间60000ms
        threadPool.setIdleTimeout(idleTimeout);
    }
}
