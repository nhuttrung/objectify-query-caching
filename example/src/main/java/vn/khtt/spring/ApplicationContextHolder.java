package vn.khtt.spring;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

public class ApplicationContextHolder implements ApplicationContextAware {
  private static ApplicationContext ctx;
  public static ApplicationContext getApplicationContext(){
    return ctx;
  }
  
  public static String getMessage(String code, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    try{
      return ctx.getMessage(code, args, locale);
    }catch(NoSuchMessageException e){
      return "??" + code + "_" + locale + "??";
    }
  }
  
  public ApplicationContextHolder() {
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}
