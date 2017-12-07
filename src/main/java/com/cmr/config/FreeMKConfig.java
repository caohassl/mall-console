package com.cmr.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

/**
 * Created by Administrator on 2017/12/6.
 */
@org.springframework.context.annotation.Configuration
public class FreeMKConfig {
    /**
     * configure the prefix and suffix of the page
     * @return
     */
    @Bean
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


    /**
     * freemarker config,this bean and the bean is both necessary.
     * it can be also configurey by PostConstruct
     * @see javax.annotation.PostConstruct
     * @see #viewResolver
     *
     * @return
     */
    @Bean
    public Configuration freemarkerConfig(){
//        FreeMarkerConfigurer fmCfg=new FreeMarkerConfigurer();
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
//      tplCfg.setTemplateLoader(new WebappTemplateLoader(servletContext));
//      fmCfg.setConfiguration(tplCfg);

        return tplCfg;
    }
}
