package vn.khtt.test.controllers;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
  private boolean setupTestEntities;
  
  public TestController() {
  }

  @RequestMapping(value="/test/00", method={RequestMethod.GET})
  public String testSetupTestEntities() throws Exception {
    setupTestEntities();

    return "test-01";
  }

  @RequestMapping(value="/test/01", method={RequestMethod.GET})
  public String testQuery() throws Exception {
    // Simple Query
    ofy().load().type(MyEntity.class).list();
    
    return "test-01";
  }

  @RequestMapping(value="/test/02", method={RequestMethod.GET})
  public String testQueryFilter() throws Exception {
    // Query with filter
    ofy().load().type(MyEntity.class).filter("num >", 5).list();
    
    return "test-01";
  }

  @RequestMapping(value="/test/03", method={RequestMethod.GET})
  public String testDeleteEntity() throws Exception {
    ofy().delete().type(MyEntity.class).id(9);
    
    return "test-01";
  }

  @RequestMapping(value="/test/04", method={RequestMethod.GET})
  public String testSaveEntity() throws Exception {
    ofy().save().entity(new MyEntity(1, "Item #" + 1, 1));
    
    return "test-01";
  }

  @RequestMapping(value="/test/05", method={RequestMethod.GET})
  public String testQueryFirst() throws Exception {
    ofy().load().type(MyEntity.class).filter("num", 5).first().now();
    
    return "test-01";
  }

  private void setupTestEntities(){
    if (setupTestEntities){
      return;
    }
    
    int N = 10;
    for (int i=1; i<=N; i++){
      MyEntity entity = new MyEntity(i, "Item #" + i, i);
      ofy().save().entity(entity);
    }
  }
}
