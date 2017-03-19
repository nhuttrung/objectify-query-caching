package vn.khtt.test;

import com.googlecode.objectify.ObjectifyService;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.googlecode.objectify.impl.CachingObjectifyFactory;
import vn.khtt.test.controllers.MyEntity;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {
  public Application() {
    // 1
    ObjectifyService.setFactory(new CachingObjectifyFactory());
    
    // 2.
    ObjectifyService.register(MyEntity.class);
  }
}
