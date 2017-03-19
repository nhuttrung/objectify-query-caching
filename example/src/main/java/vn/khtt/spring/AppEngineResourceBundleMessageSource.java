package vn.khtt.spring;

import java.io.UnsupportedEncodingException;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Fixed issue: java.util.ResourceBundle$Control is a restricted class. Please see the Google App Engine developer's guide for more details.
 */
public class AppEngineResourceBundleMessageSource extends ResourceBundleMessageSource{
  private static final String UTF8 = "UTF-8";
  private static final String ISO_8859_1 = "ISO-8859-1";
  public AppEngineResourceBundleMessageSource() {
    setBasename("messages");
    setCacheSeconds(Integer.MAX_VALUE);
  }
  
  @Override
  protected String getMessageInternal(String code, Object[] args, Locale locale) {
    String message = super.getMessageInternal(code, args, locale);
    if (message == null){
      return message;
    }

    try {
      return new String(message.getBytes(ISO_8859_1), UTF8);
    } catch (UnsupportedEncodingException e) {
      return message;
    }
  }
}
