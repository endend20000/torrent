package org.cabbage.torrent.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cabbage.torrent.viewresolver.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration  
@EnableWebMvc  
@ComponentScan(basePackages = "org.cabbage.torrent")
public class WebConfig extends WebMvcConfigurerAdapter{
   
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(
                MediaType.TEXT_HTML);
    }
    
    @Bean(name="multipartResolver") 
    public CommonsMultipartResolver getResolver() throws IOException{
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800);//5MB
        resolver.setMaxInMemorySize(40960);
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(jsonViewResolver());
        resolvers.add(jspViewResolver());
        
        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }  
}
