package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class Securityconfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login") // 跳转到自定义的登录路径
                .loginProcessingUrl("/user/login") // 设置登录提交的controller，不需要自己编写
                .defaultSuccessUrl("/index")
                .and().authorizeRequests()
                .antMatchers( "/login", "/css/**", "/js/**", "/lib/**", "/api/**").permitAll()
                .anyRequest().authenticated() // 所有连接登录后才能被访问
                .and()
                .rememberMe() // 记住我配置
                .tokenRepository(persistentTokenRepository()) // 配置数据库源
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .userDetailsService(userDetailsService);
//        http.csrf().disable() 关闭csrf防护， 默认为开启
        // 配置frame， 让其可以在同域名下打开
        http.headers().frameOptions().disable();

        // 退出登录配置
        http.logout().logoutUrl("/user/logout") // 点击退出登录时的按钮，应该访问哪个连接
                        .logoutSuccessUrl("/login"); // 登出成功后应该跳转到哪个页面

        // 配置用户退出浏览器后记住登录状态
        http.rememberMe();
    }

    /**
     * 配置remember-me
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        // 将 DataSource 设置到 PersistentTokenRepository
        persistentTokenRepository.setDataSource(dataSource);
        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        // persistentTokenRepository.setCreateTableOnStartup(true);
        return persistentTokenRepository;
    }

}
