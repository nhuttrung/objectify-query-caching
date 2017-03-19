package com.googlecode.objectify.impl;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.cmd.Deleter;
import com.googlecode.objectify.cmd.Loader;
import com.googlecode.objectify.cmd.Saver;

public class CachingObjectifyImpl<O extends Objectify> extends ObjectifyImpl<O> {
  public CachingObjectifyImpl(ObjectifyFactory fact) {
    super(fact);
  }

  @Override
  public Loader load() {
    return new CachingLoaderImpl<>(this);
  }

  @Override
  public Saver save() {
    return new CachingSaverImpl(this);
  }

  @Override
  public Deleter delete() {
    return new CachingDeleterImpl(this);
  }
}
