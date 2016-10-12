package com.faost.security.repository.security;


import io.katharsis.invoker.KatharsisInvokerBuilder;
import io.katharsis.locator.JsonServiceLocator;
import io.katharsis.servlet.AbstractKatharsisFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class JsonApiFilter  extends AbstractKatharsisFilter implements BeanFactoryAware{

    private static final String DEFAULT_RESOURSE_SEACH_PACKAGE = "com.faost.security";

    private static final String RESOURSE_DEFAULT_DOMAIN = "http:/localhost:8080";

    private BeanFactory beanFactory;
    @Override
    protected KatharsisInvokerBuilder createKatharsisInvokerBuilder() {
        final KatharsisInvokerBuilder builder = new KatharsisInvokerBuilder();

        builder.resourceSearchPackage(DEFAULT_RESOURSE_SEACH_PACKAGE).resourceDefaultDomain(RESOURSE_DEFAULT_DOMAIN).jsonServiceLocator(new JsonServiceLocator() {
            @Override
            public <T> T getInstance(Class<T> aClass) {
                return beanFactory.getBean(aClass);
            }
        });
        return builder;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
