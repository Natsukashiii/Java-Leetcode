package com.natsu.springbootshirodemo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {

  private static ApplicationContext context;


  public static ApplicationContext getContext() {
    return context;
  }

  @Override
  public void setApplicationContext(
      org.springframework.context.ApplicationContext applicationContext) throws BeansException {
    SpringContextUtils.context = applicationContext;

  }
}
