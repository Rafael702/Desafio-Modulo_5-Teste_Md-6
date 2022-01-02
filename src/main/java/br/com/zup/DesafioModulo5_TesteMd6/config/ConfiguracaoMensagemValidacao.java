package br.com.zup.DesafioModulo5_TesteMd6.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ConfiguracaoMensagemValidacao {

    @Bean
    public MessageSource mapearMensagens() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:validation");
        messageSource.setDefaultEncoding("iso-8859-1"); //iso-8859-1 -> Aceita Caracter especial
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(mapearMensagens());
        return bean;
    }


}
