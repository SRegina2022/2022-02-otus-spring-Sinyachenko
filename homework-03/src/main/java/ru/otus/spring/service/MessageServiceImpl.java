package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.config.LocaleConfig;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageSource messageSource;
    private final ApplicationConfig applicationConfig;

    public MessageServiceImpl(MessageSource msg, ApplicationConfig applicationConfig) {
        this.messageSource = msg;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public String getMessage(String key) {
        String message = messageSource.getMessage(key,null, applicationConfig.getLocale());
        return message;
    }

    @Override
    public String getMessage(String key, Object[] objectsFormat) {
        String message = messageSource.getMessage(key,objectsFormat, applicationConfig.getLocale());
        return message;
    }
}
