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
package com.github.sclassen.guicejpa;

import com.github.sclassen.guicejpa.testframework.TransactionalWorker;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnAnyThrowingNone;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnAnyThrowingRuntimeTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnAnyThrowingTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingNone;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingRuntimeTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingTestError;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnRuntimeTestExceptionThrowingNone;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnRuntimeTestExceptionThrowingRuntimeTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnRuntimeTestExceptionThrowingTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnTestExceptionThrowingNone;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnTestExceptionThrowingRuntimeTestException;
import com.github.sclassen.guicejpa.testframework.tasks.TaskRollingBackOnTestExceptionThrowingTestException;
import com.google.inject.Guice;
import com.google.inject.Injector;

import junit.framework.TestCase;

/**
 * Tests running a single non nested transaction.
 * The test make us of the testframework. For every test a new Injector is created.
 *
 * @author Stephan Classen
 */
public class SingleTransactionTest extends TestCase {

  private Injector injector;

  @Override
  public void setUp() {
    final PersistenceModule pm = new PersistenceModule();
    pm.addApplicationManagedPersistenceUnit("testUnit");
    injector = Guice.createInjector(pm);

    //startup persistence
    injector.getInstance(PersistenceService.class).start();
  }

  @Override
  public void tearDown() {
    injector.getInstance(PersistenceService.class).stop();
    injector = null;
  }


  public void testTaskRollingBackOnAnyThrowingNone() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnAnyThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnAnyThrowingRuntimeTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnAnyThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnAnyThrowingTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnAnyThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingNone() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingRuntimeTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnRuntimeTestExceptionThrowingNone() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnRuntimeTestExceptionThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnRuntimeTestExceptionThrowingRuntimeTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnRuntimeTestExceptionThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnRuntimeTestExceptionThrowingTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnRuntimeTestExceptionThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnTestExceptionThrowingNone() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnTestExceptionThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnTestExceptionThrowingRuntimeTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnTestExceptionThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnTestExceptionThrowingTestException() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnTestExceptionThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingTestError() {
    // given
    final TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingTestError.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

}
