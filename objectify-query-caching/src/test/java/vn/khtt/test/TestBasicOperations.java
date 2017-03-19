package vn.khtt.test;

import com.googlecode.objectify.Key;
import static com.googlecode.objectify.ObjectifyService.ofy;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestBasicOperations extends BaseTestcases {
  private int numOfEntities;
  
  @Before
  public void setUp() {
    super.setUp();

    int N = 10;
    // Setup some entities
    for (int i=1; i<=10; i++){
      MyEntity entity = newEntity(i);
      ofy().save().entity(entity).now();
    }
    numOfEntities = N;
  }
  
  @Test
  public void testQueryWithSave() {
    MyEntity entity;
    MyEntity entity2;

    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());

    // 1. Test querying with a 'save' entity
    entity = newEntity(++numOfEntities);
    ofy().save().entity(entity).now();
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());

    // 2. Test querying with a 'save' entities
    entity = newEntity(++numOfEntities);
    entity2 = newEntity(++numOfEntities);
    ofy().save().entities(entity, entity2).now();
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());
  }
  
  @Test
  public void testQueryWithDelete() {
    MyEntity entity;
    MyEntity entity2;

    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());
    
    // Test querying with a 'delete' entity
    entity = ofy().load().type(MyEntity.class).id(1).now();
    ofy().delete().entity(entity).now();
    numOfEntities--;
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());
    
    // Test querying with a 'delete' entities
    entity = ofy().load().type(MyEntity.class).id(2).now();
    entity2 = ofy().load().type(MyEntity.class).id(3).now();
    ofy().delete().entities(entity, entity2).now();
    numOfEntities -= 2;
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());

    // Test querying with a 'delete' by Key
    ofy().delete().key(Key.create(MyEntity.class, 4)).now();
    numOfEntities--;
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());

    // Test querying with a 'delete' by Keys
    ofy().delete().keys(Key.create(MyEntity.class, 5), Key.create(MyEntity.class, 6)).now();
    numOfEntities -= 2;
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());
    
    // Test querying with a 'delete' by Id
    ofy().delete().type(MyEntity.class).id(7).now();
    numOfEntities--;
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());

    // Test querying with a 'delete' by Ids
    // TODO: Seem Objectify Bug
    /*
    ofy().delete().type(MyEntity.class).ids(8L, 9L).now();
    numOfEntities -= 2;
    assertEquals(numOfEntities, ofy().load().type(MyEntity.class).list().size());
    */
  }
  
  private MyEntity newEntity(int n){
    return new MyEntity(n, "Item #" + n, n);
  }
}
