/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.ext.apex;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.groovy.ext.apex.sstore.SessionStore
/**
 * Represents a browser session.
 * <p>
 * Sessions persist between HTTP requests for a single browser session. They are deleted when the browser is closed, or
 * they time-out. Session cookies are used to maintain sessions using a secure UUID.
 * <p>
 * Sessions can be used to maintain data for a browser session, e.g. a shopping basket.
 * <p>
 * The context must have first been routed to a {@link io.vertx.ext.apex.handler.SessionHandler}
 * for sessions to be available.
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@CompileStatic
public class Session {
  final def io.vertx.ext.apex.Session delegate;
  public Session(io.vertx.ext.apex.Session delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * @return The unique ID of the session. This is generated using a random secure UUID.
   */
  public String id() {
    def ret = this.delegate.id();
    return ret;
  }
  /**
   * Put some data in a session
   *
   * @param key  the key for the data
   * @param obj  the data
   * @return a reference to this, so the API can be used fluently
   */
  public Session put(String key, Object obj) {
    this.delegate.put(key, InternalHelper.unwrapObject(obj));
    return this;
  }
  /**
   * Get some data from the session
   *
   * @param key  the key of the data
   * @return  the data
   */
  public <T> T get(String key) {
    // This cast is cleary flawed
    def ret = (T) InternalHelper.wrapObject(this.delegate.get(key));
    return ret;
  }
  /**
   * Remove some data from the session
   *
   * @param key  the key of the data
   * @return  the data that was there or null if none there
   */
  public <T> T remove(String key) {
    // This cast is cleary flawed
    def ret = (T) InternalHelper.wrapObject(this.delegate.remove(key));
    return ret;
  }
  /**
   * @return the time the session was last accessed
   */
  public long lastAccessed() {
    def ret = this.delegate.lastAccessed();
    return ret;
  }
  /**
   * Destroy the session
   */
  public void destroy() {
    this.delegate.destroy();
  }
  /**
   * @return has the session been destroyed?
   */
  public boolean isDestroyed() {
    def ret = this.delegate.isDestroyed();
    return ret;
  }
  /**
   * @return  the principal (username) of the logged in user (if any). Must be used in conjunction with a
   * {@link io.vertx.ext.apex.handler.AuthHandler}.
   */
  public String getPrincipal() {
    def ret = this.delegate.getPrincipal();
    return ret;
  }
  /**
   * @return  true if the user is logged in.
   */
  public boolean isLoggedIn() {
    def ret = this.delegate.isLoggedIn();
    return ret;
  }
  /**
   * Logout the user.
   */
  public void logout() {
    this.delegate.logout();
  }
  /**
   * @return the amount of time in ms, after which the session will expire, if not accessed.
   */
  public long timeout() {
    def ret = this.delegate.timeout();
    return ret;
  }
  /**
   * @return the store for the session
   */
  public SessionStore sessionStore() {
    def ret= SessionStore.FACTORY.apply(this.delegate.sessionStore());
    return ret;
  }
  /**
   * Set the principal for the session
   *
   * @param principal  the principal
   */
  public void setPrincipal(String principal) {
    this.delegate.setPrincipal(principal);
  }
  /**
   * Mark the session as being accessed.
   */
  public void setAccessed() {
    this.delegate.setAccessed();
  }

  static final java.util.function.Function<io.vertx.ext.apex.Session, Session> FACTORY = io.vertx.lang.groovy.Factories.createFactory() {
    io.vertx.ext.apex.Session arg -> new Session(arg);
  };
}
