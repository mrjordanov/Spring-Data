package com.example.mvc.config;

import com.example.mvc.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class BeanConfiguration {

    @Bean
    public MyValidator getValidator(){
        return new MyValidator();
    }

    @Bean(name = "default")
    @Primary
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "withLocalDate")
    public ModelMapper getAlternativeModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(ctx-> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),String.class,LocalDate.class);
        return mapper;
    }

}
