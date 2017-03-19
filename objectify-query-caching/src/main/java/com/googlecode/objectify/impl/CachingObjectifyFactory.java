package com.googlecode.objectify.impl;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;

public class CachingObjectifyFactory extends ObjectifyFactory {
  public CachingObjectifyFactory() {
  }
  
  public Objectify begin() {
    return new CachingObjectifyImpl(this);
  }
}
