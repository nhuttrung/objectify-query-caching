package com.googlecode.objectify.impl;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CachingUtils {
  private static final MemcacheService mc = MemcacheServiceFactory.getMemcacheService();

  public static List<QueryInfo> getQueryInfosByKind(String kind){
    try {
      List<QueryInfo> queryInfos = (List<QueryInfo>)mc.get(Constants.CACHING_KIND_PREFIX + kind);
      if (queryInfos == null){
        queryInfos = new ArrayList<QueryInfo>();
      }
      return queryInfos;
    } catch (ClassCastException e) {
      mc.delete(Constants.CACHING_KIND_PREFIX + kind);
      return new ArrayList<QueryInfo>();
    }
  }
  
  public static void deleteCachedQuery(Object entity){
    String kind = Key.getKind(entity.getClass());
    deleteCachedQuery(kind);
  }
  public static void deleteCachedQuery(Object... entities){
    deleteCachedQuery(Arrays.asList(entities));
  }
  public static void deleteCachedQuery(Iterable entities){
    Set<String> kinds = new HashSet<String>();
    for (Object entity : entities){
      String kind = Key.getKind(entity.getClass());
      kinds.add(kind);
    }

    for (String kind : kinds){
      deleteCachedQuery(kind);
    }
  }
  public static void deleteCachedQuery(String kind){
    List<QueryInfo> queryInfos = CachingUtils.getQueryInfosByKind(kind);
    // Delete the result caching for each QueryInfo
    for (QueryInfo qi : queryInfos){
      mc.delete(qi);
    }
    // Delete the list of QueryInfo
    mc.delete(Constants.CACHING_KIND_PREFIX + kind);
  }
  
  public static <T> List<T> getCachedResult(QueryInfo<T> qi){
    List<QueryInfo> queryInfos = getQueryInfosByKind(qi.actual.getKind());
    for (QueryInfo q : queryInfos){
      if (q.equals(qi)){
        List<T> result = (List<T>)mc.get(qi);
        return result;
      }
    }
    
    return null;
  }
  
  public static <T> void addCachedResult(QueryInfo<T> qi, List<T> result){
    List<QueryInfo> queryInfos = getQueryInfosByKind(qi.actual.getKind());
    queryInfos.add(qi);
    
    // Cache the list of QueryInfo
    mc.put(Constants.CACHING_KIND_PREFIX + qi.actual.getKind(), queryInfos);
    // Cache the result
    mc.put(qi, result);
  }
}
