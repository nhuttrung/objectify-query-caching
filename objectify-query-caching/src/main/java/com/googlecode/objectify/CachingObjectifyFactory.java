package com.googlecode.objectify;

import com.googlecode.objectify.impl.CachingObjectifyImpl;


public class CachingObjectifyFactory extends ObjectifyFactory {
  public CachingObjectifyFactory() {
  }
  
  public Objectify begin() {
    return new CachingObjectifyImpl(this);
  }
}
