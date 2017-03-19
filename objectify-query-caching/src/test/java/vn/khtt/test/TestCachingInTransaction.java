package vn.khtt.test;

import static com.googlecode.objectify.ObjectifyService.ofy;
import org.junit.Test;

public class TestCachingInTransaction extends BaseTestcases {
  public TestCachingInTransaction() {
  }

  @Test
  public void testSimpleTransaction() {
    ofy().transact(new Runnable(){
      @Override
      public void run() {
        ofy().load().type(MyEntity.class).list();
      }
    });
  }
}
