package in.anandm.pa;

import in.anandm.pa.utils.ObjectIdSerializer;
import in.anandm.pa.web.validators.MessageValidator;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(value = "in.anandm.pa.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(new MappingJackson2HttpMessageConverter(
                Jackson2ObjectMapperBuilder.json()
                        .serializers(objectIdSerializer()).build()));
    }

    @Bean
    ObjectIdSerializer objectIdSerializer() {
        return new ObjectIdSerializer();
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/errors");
        messageSource.setCacheSeconds(1800);
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSizePerFile(3 * 1000 * 1024);
        return multipartResolver;
    }

    @Bean
    Validator messageValidator() {
        return new MessageValidator();
    }

}
