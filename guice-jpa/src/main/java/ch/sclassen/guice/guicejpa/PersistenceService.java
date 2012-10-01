/**
 * Copyright (C) 2012 Stephan Classen
 * Based on guice-perist (Copyright (C) 2010 Google, Inc.)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.sclassen.guice.guicejpa;

/**
 * Persistence provider service.
 *
 * @author Stephan Classen
 */
public interface PersistenceService {

  /**
   * Starts the underlying persistence engine and makes jpa-persist ready for use.
   * This method must be called by your code prior to using any other jpa-persist artifacts.
   * <ul>
   *  <li>If already started, calling this method does nothing.</li>
   * </ul>
   */
  void start();

  /**
   * @return {@code true} if the underlying persistence engine is running.
   *         {@code false} otherwise.
   **/
  boolean isRunning();

  /**
   * Stops the underlying persistence engine.
   * <ul>
   *  <li>If already stopped, calling this method does nothing.</li>
   *  <li>If not yet started, it also does nothing.</li>
   * </ul>
   */
  void stop();

}
