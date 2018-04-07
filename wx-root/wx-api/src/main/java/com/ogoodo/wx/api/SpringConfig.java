package com.ogoodo.wx.api;


import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
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

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverterEx() {
        //1、需要先定义一个 convert 转换消息对象；
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加 fastJson 的配置信息，比如: 是否要格式化返回的Json数据；
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        //3、在 Convert 中添加配置信息;
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //4、将 convert 添加到 converts 中;
        return fastConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters); 
        converters.add(jackson2HttpMessageConverter());
        converters.add(fastJsonHttpMessageConverterEx());
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
