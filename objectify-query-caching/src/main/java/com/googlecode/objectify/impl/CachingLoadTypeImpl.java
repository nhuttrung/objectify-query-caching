package com.googlecode.objectify.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadIds;

public class CachingLoadTypeImpl<T> extends LoadTypeImpl<T> {
  private final String kind;

  /** Might be null; perhaps we specified a raw kind only */
  private final Class<T> type;

  /** Possible parent */
  private final Key<T> parent;

  public CachingLoadTypeImpl(LoaderImpl<?> loader, String kind, Class<T> type) {
    this(loader, kind, type, null);
  }
  
  public CachingLoadTypeImpl(LoaderImpl<?> loader, String kind, Class<T> type, Key<T> parent){
    super(loader, kind, type, parent);

    this.kind = kind;
    this.type = type;
    this.parent = parent;
  }

  @Override
  QueryImpl<T> createQuery() {
    return new CachingQueryImpl<>(loader, kind, type);
  }

  @Override
  public LoadIds<T> parent(Object keyOrEntity) {
    Key<T> parentKey = loader.ofy.factory().keys().anythingToKey(keyOrEntity);
    return new CachingLoadTypeImpl<>(loader, kind, type, parentKey);
  }
}
