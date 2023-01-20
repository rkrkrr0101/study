package hello.core;

import hello.core.member.MemberRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters =@ComponentScan.Filter (type=FilterType.ANNOTATION,classes=Configuration.class))
public class AutoAppConfig {
}
