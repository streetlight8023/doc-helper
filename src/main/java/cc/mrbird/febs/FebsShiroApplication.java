package cc.mrbird.febs;

import cc.mrbird.febs.common.annotation.FebsShiro;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author MrBird
 */
@FebsShiro
public class FebsShiroApplication {
//public class FebsShiroApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsShiroApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(FebsShiroApplication.class);
//    }
}
