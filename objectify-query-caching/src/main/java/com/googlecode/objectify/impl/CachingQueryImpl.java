package com.googlecode.objectify.impl;

import com.googlecode.objectify.LoadResult;

import com.googlecode.objectify.Result;

import java.util.List;

public class CachingQueryImpl<T> extends QueryImpl<T> {
  public CachingQueryImpl(LoaderImpl<?> loader) {
    super(loader);
  }

  public CachingQueryImpl(LoaderImpl<?> loader, String kind, Class<T> clazz) {
    super(loader, kind, clazz);
  }

  @Override
  public LoadResult<T> first() {
    Result<T> result = new Result<T>(){
      @Override
      public T now() {
        List<T> list = CachingQueryImpl.this.list();
        if (list.size() > 0){
          return list.get(0);
        }
        return null;
      }
    };
    return new LoadResult<>(null, result);
  }
  
  public List<T> list() {
    // No cache --> do not use cache
    if (!this.loader.ofy.cache){
      return super.list();
    }
    // In a transaction context --> do not use cache
    if (this.loader.ofy.getTransaction() != null){
      return super.list();
    }
    
    QueryInfo<T> qi = new QueryInfo<T>(this);
    List<T> result = CachingUtils.getCachedResult(qi);
    if (result == null){
      result = super.list();
      CachingUtils.addCachedResult(qi, result);
    }
    
    return result;
  }
}
