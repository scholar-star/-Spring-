package hello.core.order;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // 기본적으로 찾을 package(우선적으로 찾는다.)
        // 2개 이상의 빈이 있으면 찾지 못할 수 있는데, basePackage을 이용해 우선적으로 지정하게 할 수도 있다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
