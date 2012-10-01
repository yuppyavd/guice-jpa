/**
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

import junit.framework.TestCase;
import ch.sclassen.guice.guicejpa.PersistenceModule;
import ch.sclassen.guice.guicejpa.PersistenceService;
import ch.sclassen.guice.guicejpa.testframework.TransactionalWorker;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnAnyThrowingNone;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnAnyThrowingRuntimeTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnAnyThrowingTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingNone;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingRuntimeTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnNoneThrowingTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnRuntimeTestExceptionThrowingNone;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnRuntimeTestExceptionThrowingRuntimeTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnRuntimeTestExceptionThrowingTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnTestExceptionThrowingNone;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnTestExceptionThrowingRuntimeTestException;
import ch.sclassen.guice.guicejpa.testframework.tasks.TaskRollingBackOnTestExceptionThrowingTestException;

import com.google.inject.Guice;
import com.google.inject.Injector;

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
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnAnyThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnAnyThrowingRuntimeTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnAnyThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnAnyThrowingTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnAnyThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingNone() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingRuntimeTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnNoneThrowingTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnNoneThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnRuntimeTestExceptionThrowingNone() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnRuntimeTestExceptionThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnRuntimeTestExceptionThrowingRuntimeTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnRuntimeTestExceptionThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

  public void testTaskRollingBackOnRuntimeTestExceptionThrowingTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnRuntimeTestExceptionThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnTestExceptionThrowingNone() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnTestExceptionThrowingNone.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnTestExceptionThrowingRuntimeTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnTestExceptionThrowingRuntimeTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertAllEntitesHaveBeenPersisted();
  }

  public void testTaskRollingBackOnTestExceptionThrowingTestException() {
    // given
    TransactionalWorker worker = injector.getInstance(TransactionalWorker.class);
    worker.scheduleTask(TaskRollingBackOnTestExceptionThrowingTestException.class);

    // when
    worker.doTasks();

    // then
    worker.assertNoEntityHasBeenPersisted();
  }

}
