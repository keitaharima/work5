package oit.is.team2.kaizi.work5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration {

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // user1/p@ss,user2/p@ss,admin/p@ss

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e").roles("USER").build();
    UserDetails admin = User.withUsername("admin")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e").roles("ADMIN").build();
    // $ sshrun htpasswd -nbBC 10 customer1 p@ss
    UserDetails customer1 = User.withUsername("customer1")
        .password("{bcrypt}$2y$05$ssO3LBTWTqcYGO81O5hxaeYeXnICwomprXLugE31YO2F5J.GsKTpm")
        .roles("CUSTOMER")
        .build();
    // $ sshrun htpasswd -nbBC 10 customer2 p@ss
    UserDetails customer2 = User.withUsername("customer2")
        .password("{bcrypt}$2y$05$1YpDmXZTB11eKBGMKcopsOFZlrroWPc3qJo6xf/3YYdhRfm8zScfC")
        .roles("CUSTOMER")
        .build();
    // $ sshrun htpasswd -nbBC 10 seller p@ss
    UserDetails seller = User.withUsername("seller")
        .password("{bcrypt}$2y$05$gScyhoAHJUIUoeth0BujxeoBaDv8E7myAf1GYGAAoE0gmqwKAoBw.")
        .roles("SELLER")
        .build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2, admin, customer1, customer2, seller);
  }

}
