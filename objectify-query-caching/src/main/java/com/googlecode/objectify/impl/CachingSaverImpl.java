package com.googlecode.objectify.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

import java.util.Map;

public class CachingSaverImpl extends SaverImpl {
  public CachingSaverImpl(ObjectifyImpl<?> ofy) {
    super(ofy);
  }

  @Override
  public <E> Result<Key<E>> entity(E entity) {
    Result<Key<E>> result = super.entity(entity);

    // Delete cached Query
    CachingUtils.deleteCachedQuery(entity);

    return result;
  }

  @Override
  public <E> Result<Map<Key<E>, E>> entities(Iterable<E> entities) {
    Result<Map<Key<E>, E>> result = super.entities(entities);

    // Delete cached Query
    CachingUtils.deleteCachedQuery(entities);
    
    return result;
  }
}
