package com.cmr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/12/4.
 */
@Slf4j
@SpringBootApplication
@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration
public class WebConsoleApplication {

    public static void main(String args[]){
        log.info("webConsole Application is starting ...");
        SpringApplication.run(WebConsoleApplication.class,args);
    }


   /* @Bean
    public FreeMarkerViewResolver viewResolver(){
        FreeMarkerViewResolver viewResolver=new FreeMarkerViewResolver();
        viewResolver.setViewClass(FreeMarkerView.class);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setExposeSessionAttributes(true);
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setCache(true);
        viewResolver.setOrder(0);
        return viewResolver;
    }


    @Bean
    public FreeMarkerConfigurer freemarkerConfig(){
                FreeMarkerConfigurer fmCfg=new FreeMarkerConfigurer();
                Configuration tplCfg = new Configuration();
                tplCfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
                tplCfg.setNumberFormat("0.##########");
                tplCfg.setTemplateUpdateDelay(0);
                tplCfg.setDefaultEncoding("UTF-8");
                tplCfg.setOutputEncoding("UTF-8");
                tplCfg.setLocale(Locale.SIMPLIFIED_CHINESE);
                tplCfg.setDateFormat("yyyy-MM-dd");
                tplCfg.setTimeFormat("HH:mm:ss");
                tplCfg.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
                tplCfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
                tplCfg.setClassicCompatible(true);//空串显示""
//                tplCfg.setTemplateLoader(new WebappTemplateLoader(servletContext));
                fmCfg.setConfiguration(tplCfg);

                return fmCfg;
    }*/
}
