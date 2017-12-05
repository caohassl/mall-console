package com.cmr.config;

/**
 * Created by Administrator on 2017/12/5.
 */

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


        import javax.annotation.PostConstruct;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;



        import freemarker.template.TemplateException;


@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver resolver;
    @Autowired
    protected org.springframework.web.servlet.view.InternalResourceViewResolver springResolver;


    @PostConstruct
    public void  setSharedVariable(){
        configuration.setDateFormat("yyyy/MM/dd");

        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");

        //下面三句配置的就是我自己的freemarker的自定义标签，在这里把标签注入到共享变量中去就可以在模板中直接调用了
        //configuration.setSharedVariable("content_list", new ContentListDirective());
        //configuration.setSharedVariable("article_list", new ArticleDirective());
        //configuration.setSharedVariable("channel_list", new ChannelListDirective());</span>

        try {
            configuration.setSetting("template_update_delay", "1");
            configuration.setSetting("default_encoding", "UTF-8");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        /**
         * 配置Spring JSP的视图解析器
         */
//        springResolver.setPrefix("/XXX/");//解析前缀后XXX路径下的jsp文件
//        springResolver.setSuffix(".jsp");
//        springResolver.setOrder(1);

        /**
         * 配置Freemarker视图解析器
         */
                resolver.setSuffix(".ftl"); //解析后缀为html的
                resolver.setCache(false); //是否缓存模板
                resolver.setRequestContextAttribute("request");
                resolver.setOrder(0);
    }



}