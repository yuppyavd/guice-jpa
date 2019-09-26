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
package com.github.sclassen.guicejpa.testframework.exceptions;

import com.github.sclassen.guicejpa.testframework.TransactionalTask;

/**
 * Error which can be thrown by a {@link TransactionalTask}.
 *
 * @author Piotr Ostrowski
 */
public class TestError extends Error {

  private static final long serialVersionUID = 1L;

  private final String message;

  /**
   * Constructor.
   */
  public TestError() {
    message = TestError.class.getSimpleName();
  }

  /**
   * Constructor.
   * 
   * @param message the message of the exception.
   */
  public TestError(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
