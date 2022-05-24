package ru.otus.spring.pantushev.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.config.UserInterfaceConfig;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl
    implements MessageSourceService
{
    private final MessageSource messageSource;
    private final UserInterfaceConfig userInterfaceConfig;
    private final Locale locale;

    public Locale getLocale() {
        return locale;
    }

    @Autowired
    public MessageSourceServiceImpl(MessageSource messageSource, UserInterfaceConfig userInterfaceConfig) {
        this.messageSource = messageSource;
        this.userInterfaceConfig = userInterfaceConfig;
        if (StringUtils.isEmpty(userInterfaceConfig.getLocale())) {
            locale = Locale.getDefault();
        }
        else {
            locale = Locale.forLanguageTag(userInterfaceConfig.getLocale());
            if (StringUtils.isEmpty(locale.getLanguage()))
            {
                throw new RuntimeException("Invalid property: interface: locale:");
            }
        }
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, locale);
    }
}
