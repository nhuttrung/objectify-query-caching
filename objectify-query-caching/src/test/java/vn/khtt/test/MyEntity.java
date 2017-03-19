package vn.khtt.test;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.io.Serializable;

@Entity
public class MyEntity implements Serializable {
  @Id
  private Long id;
  
  private String name;
  
  @Index
  private int num;

  public MyEntity() {
  }
  public MyEntity(long id, String name, int num) {
    this.id = id;
    this.name = name;
    this.num = num;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public String toString(){
    return "id=" + id 
           + ", name" + name 
           + ", num" + num
      ;
  }
}
