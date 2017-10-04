package com.ogoodo.wx.api;


import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

import com.ogoodo.wx.utils.valid.converter.StringToBaseEnumConverterFactory;
import com.ogoodo.wx.utils.valid.converter.StringToGenderConverter;
import com.ogoodo.wx.utils.valid.converter.UniversalEnumConverterFactory;
import com.ogoodo.wx.utils.valid.formatter.MyPhoneNumberFormatAnnotationFormatterFactory;
import com.ogoodo.wx.utils.valid.formatter.TimestampFormatAnnotationFormatterFactory;
import com.ogoodo.wx.utils.valid.json.CustomObjectMapper;



@Configuration
public class SpringConfig extends WebMvcConfigurerAdapter{

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new CustomObjectMapper());

        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageConverter());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        super.addFormatters(registry);
		System.out.println("SpringConfig->addFormatters============== {{");
//        registry.addFormatterForFieldAnnotation(new TimestampFormatAnnotationFormatterFactory());
        registry.addFormatterForFieldAnnotation(new MyPhoneNumberFormatAnnotationFormatterFactory());
//        registry.addConverterFactory(new UniversalEnumConverterFactory());
//        registry.addConverterFactory(new StringToBaseEnumConverterFactory());
//        registry.addConverter(new StringToGenderConverter());
		System.out.println("SpringConfig->addFormatters============== }}");
//        super.addFormatters(registry);
    }

        /**
     * 利用fastjson替换掉jackson，且解决中文乱码问题
     * @param converters
     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //处理中文乱码问题
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//    }

}
