package com.natsu.springbootshirodemo.shiro;

import com.natsu.springbootshirodemo.filter.URLPathMatchingFilter;
import com.natsu.springbootshirodemo.realm.DatabaseRealm;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.activation.MailcapCommandMap;
import javax.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ShiroConfiguration {

  @Bean
  public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /**
   * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
   * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
   *
   * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    log.info("ShiroConfiguration.shiroFilter()");
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    // 设置SecurityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 如果不设置会自动寻找web工程根目录下的"/login.jsp" 页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后的跳转页面
    shiroFilterFactoryBean.setSuccessUrl("/index");
    // 未授权界面
    shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
    // 拦截器
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    // 自定义拦截器
    Map<String, Filter> customiedFilter = new HashMap<>();
    customiedFilter.put("url", getURLPathMatchingFilter());

    // 配置映射关系
    filterChainDefinitionMap.put("/login", "anon");
    filterChainDefinitionMap.put("/index", "anon");
    filterChainDefinitionMap.put("/static/**", "anon");
    filterChainDefinitionMap.put("/config/**", "anon");
    filterChainDefinitionMap.put("/doLogout", "logout");
    filterChainDefinitionMap.put("/**", "url");
    shiroFilterFactoryBean.setFilters(customiedFilter);
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }

  public URLPathMatchingFilter getURLPathMatchingFilter() {
    return new URLPathMatchingFilter();
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    // 设置realm
    securityManager.setRealm(getDataBaseRealm());
    return securityManager;
  }

  @Bean
  public DatabaseRealm getDataBaseRealm() {
    DatabaseRealm myrealm = new DatabaseRealm();
    myrealm.setCredentialsMatcher(hashedCredentialsMatcher());
    return myrealm;
  }

  /**
   * 凭证匹配器 由于密码校验通过Shiro的SimpleAuthenticationInfo进行处理 所以需要修改doGetAuthenticationInfo中代码
   */
  public HashedCredentialsMatcher hashedCredentialsMatcher() {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    // 散列算法
    hashedCredentialsMatcher.setHashAlgorithmName("md5");
    // 散列的次数
    hashedCredentialsMatcher.setHashIterations(2);
    return hashedCredentialsMatcher;
  }

  /**
   * 开启Shiro 的aop注解支持 使用代理方式,所以需要开启代码支持
   */
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }
}
