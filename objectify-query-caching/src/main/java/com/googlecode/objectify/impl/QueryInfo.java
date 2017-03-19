package com.googlecode.objectify.impl;

import com.google.appengine.api.datastore.Cursor;

import java.io.Serializable;

import java.util.Objects;

public class QueryInfo<T> implements Serializable {
  @SuppressWarnings("compatibility:5430296322613344069")
  private static final long serialVersionUID = 1L;
  
  Class<T> classRestriction;
  com.google.appengine.api.datastore.Query actual;
  private int limit;
  private int offset;
  private Cursor startAt;
  private Cursor endAt;
  private Integer chunk;
  private Boolean hybrid;

  private QueryInfo() {
    // For Serialization
  }

  public QueryInfo(CachingQueryImpl<T> query) {
    this.classRestriction = query.classRestriction;
    this.actual = query.actual;
    this.limit = query.limit;
    this.offset = query.offset;
    this.startAt = query.startAt;
    this.endAt = query.endAt;
    this.chunk = query.chunk;
    this.hybrid = query.hybrid;
  }

  @Override
  public int hashCode() {
    return Objects.hash(classRestriction, actual, limit, offset, startAt, endAt, chunk, hybrid);
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !(o instanceof QueryInfo)) {
      return false;
    }
    
    QueryInfo q = (QueryInfo)o;
    return Objects.equals(this.actual, q.actual) 
           && Objects.equals(this.startAt, q.startAt)
           && Objects.equals(this.endAt, q.endAt) 
           && Objects.equals(this.limit, q.limit) 
           && Objects.equals(this.offset, q.offset) 
           && Objects.equals(this.chunk, q.chunk) 
           && Objects.equals(this.hybrid, q.hybrid)
      ;
  }
}
