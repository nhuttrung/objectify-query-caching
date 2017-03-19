package vn.khtt.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import com.googlecode.objectify.CachingObjectifyFactory;
import com.googlecode.objectify.util.Closeable;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTestcases {
  protected LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
  protected Closeable session;

  public BaseTestcases() {
  }

  @BeforeClass
  public static void setUpBeforeClass() {
    // Reset the Factory so that all translators work properly.
    ObjectifyService.setFactory(new ObjectifyFactory());
    ObjectifyService.setFactory(new CachingObjectifyFactory());
    ObjectifyService.register(MyEntity.class);
  }

  @Before
  public void setUp() {
    this.session = ObjectifyService.begin();
    helper.setUp();
  }

  @After
  public void tearDown() {
    AsyncCacheFilter.complete();
    
    helper.tearDown();
  }
}
