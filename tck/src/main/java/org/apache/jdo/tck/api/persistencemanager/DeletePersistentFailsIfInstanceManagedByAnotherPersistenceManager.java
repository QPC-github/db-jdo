/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jdo.tck.api.persistencemanager;

import java.util.Collection;
import java.util.HashSet;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import org.apache.jdo.tck.pc.mylib.PCPoint;
import org.apache.jdo.tck.util.BatchTestRunner;

/**
 * <B>Title:</B> DeletePersistent Fails If Instance Managed By Another PersistenceManager <br>
 * <B>Keywords:</B> exception <br>
 * <B>Assertion IDs:</B> A12.5.7-11. <br>
 * <B>Assertion Description: </B> PersistenceManager.deletePersistent and deletePersistentAll will
 * throw a JDOUserException if the parameter instance is managed by a different PersistenceManager.
 */
public class DeletePersistentFailsIfInstanceManagedByAnotherPersistenceManager
    extends PersistenceManagerTest {

  /** */
  private static final String ASSERTION_FAILED =
      "Assertion A12.5.7-11 (DeletePersistentFailsIfInstanceManagedByAnotherPersistenceManager) failed: ";

  /**
   * The <code>main</code> is called when the class is directly executed from the command line.
   *
   * @param args The arguments passed to the program.
   */
  public static void main(String[] args) {
    BatchTestRunner.run(DeletePersistentFailsIfInstanceManagedByAnotherPersistenceManager.class);
  }

  private PCPoint p1 = null;
  private PCPoint p2 = null;
  private PCPoint p3 = null;
  private PCPoint p4 = null;
  private PCPoint p5 = null;

  /** */
  public void testDeletePersistentFailsIfInstanceManagedByAnotherPersistenceManager() {
    pm = getPM();
    PersistenceManager pm2 = getPMF().getPersistenceManager();
    try {
      createObjects(pm2);

      /* positive tests */
      runTestDeletePersistent(pm);
      runTestDeletePersistentAll1(pm);
      runTestDeletePersistentAll2(pm);
    } finally {
      cleanupPM(pm2);
      pm2 = null;
      cleanupPM(pm);
      pm = null;
    }
  }

  /** */
  private void createObjects(PersistenceManager pm) {
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();
      p1 = new PCPoint(1, 3);
      p2 = new PCPoint(2, 4);
      p3 = new PCPoint(3, 5);
      p4 = new PCPoint(4, 6);
      p5 = new PCPoint(5, 7);

      pm.makePersistent(p1);
      pm.makePersistent(p2);
      pm.makePersistent(p3);
      pm.makePersistent(p4);
      pm.makePersistent(p5);
      tx.commit();
    } finally {
      if ((tx != null) && tx.isActive()) tx.rollback();
    }
  }

  /* test deletePersistent (Object pc) */
  private void runTestDeletePersistent(PersistenceManager pm) {
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();
      try {
        pm.deletePersistent(p1);
        fail(
            ASSERTION_FAILED,
            "pm.deletePersistent(Object) with pc instance managed by another pm should throw exception");
      } catch (JDOUserException ex) {
        // expected exception
      }
      tx.rollback();
    } finally {
      if (tx.isActive()) tx.rollback();
    }
  }

  /* test deletePersistentAll (Collection pcs) */
  private void runTestDeletePersistentAll1(PersistenceManager pm) {
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();

      Collection<PCPoint> col1 = new HashSet<>();
      col1.add(p2);
      col1.add(p3);

      try {
        pm.deletePersistentAll(col1);
        fail(
            ASSERTION_FAILED,
            "pm.deletePersistent(Collection) with pc instance(s) managed by another pm should throw exception");
      } catch (JDOUserException ex) {
        // expected exception
      }
      tx.rollback();
    } finally {
      if (tx.isActive()) tx.rollback();
    }
  }

  /* test deletePersistentAll (Object[] o) */
  private void runTestDeletePersistentAll2(PersistenceManager pm) {
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();

      Collection<PCPoint> col1 = new HashSet<>();
      col1.add(p4);
      col1.add(p5);
      Object[] obj1 = col1.toArray();
      try {
        pm.deletePersistentAll(obj1);
        fail(
            ASSERTION_FAILED,
            "pm.deletePersistent(Object[]) with pc instance(s) managed by another pm should throw exception");
      } catch (JDOUserException ex) {
        // expected exception
      }
      tx.rollback();
    } finally {
      if (tx.isActive()) tx.rollback();
    }
  }
}
