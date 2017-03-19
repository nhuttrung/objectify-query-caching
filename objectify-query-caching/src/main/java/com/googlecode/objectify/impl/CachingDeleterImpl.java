package com.googlecode.objectify.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.DeleteType;

public class CachingDeleterImpl extends DeleterImpl {
  public CachingDeleterImpl(ObjectifyImpl<?> ofy) {
    super(ofy);
  }

  @Override
  public Result<Void> entity(Object entity) {
    // Delete cached Query
    CachingUtils.deleteCachedQuery(entity);
    
    return super.entity(entity);
  }

  @Override
  public Result<Void> entities(Iterable<?> entities) {
    // Delete cached Query
    CachingUtils.deleteCachedQuery(entities);

    return super.entities(entities);
  }

  @Override
  public Result<Void> key(Key<?> key) {
    // Delete cached Query
    CachingUtils.deleteCachedQuery(key.getKind());

    return super.key(key);
  }

  @Override
  public Result<Void> keys(Iterable<? extends Key<?>> keys) {
    // Delete cached Query
    for (Key key : keys){
      CachingUtils.deleteCachedQuery(key.getKind());
    }
    
    return super.keys(keys);
  }

  @Override
  public DeleteType type(Class<?> type) {
    // TODO: Do we need to override this method?
    return super.type(type);
  }
}
