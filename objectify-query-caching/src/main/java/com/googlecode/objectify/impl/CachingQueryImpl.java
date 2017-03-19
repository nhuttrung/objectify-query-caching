package com.googlecode.objectify.impl;

import java.util.List;

public class CachingQueryImpl<T> extends QueryImpl<T> {
  public CachingQueryImpl(LoaderImpl<?> loader) {
    super(loader);
  }

  public CachingQueryImpl(LoaderImpl<?> loader, String kind, Class<T> clazz) {
    super(loader, kind, clazz);
  }

  public List<T> list() {
    QueryInfo<T> qi = new QueryInfo<T>(this);
    List<T> result = CachingUtils.getCachedResult(qi);
    if (result == null){
      result = super.list();
      CachingUtils.addCachedResult(qi, result);
    }
    
    return result;
  }
}
