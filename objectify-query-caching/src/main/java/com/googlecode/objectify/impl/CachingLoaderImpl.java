package com.googlecode.objectify.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Loader;

public class CachingLoaderImpl<L extends Loader> extends LoaderImpl<L> {
  public CachingLoaderImpl(ObjectifyImpl<?> ofy) {
    super(ofy);
  }

  @Override
  QueryImpl<Object> createQuery() {
    return new CachingQueryImpl<>(this);
  }

  @Override
  public <E> LoadType<E> type(Class<E> type) {
    return new CachingLoadTypeImpl<>(this, Key.getKind(type), type);
  }

  @Override
  public <E> LoadType<E> kind(String kind) {
    return new CachingLoadTypeImpl<>(this, kind, null);
  }
}
