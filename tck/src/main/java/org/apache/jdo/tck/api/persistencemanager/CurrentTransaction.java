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

import javax.jdo.Transaction;
import org.apache.jdo.tck.pc.mylib.PCPoint;
import org.apache.jdo.tck.util.BatchTestRunner;

/**
 * <B>Title:</B> CurrentTransaction <br>
 * <B>Keywords:</B> <br>
 * <B>Assertion ID:</B> A12.5.2-1 <br>
 * <B>Assertion Description: </B> The PersistenceManager.currentTransaction method returns the
 * Transaction instance associated with the PersistenceManager.
 */
public class CurrentTransaction extends PersistenceManagerTest {

  /** */
  private static final String ASSERTION_FAILED =
      "Assertion A12.5.2-1 (CurrentTransaction) failed: ";

  /**
   * The <code>main</code> is called when the class is directly executed from the command line.
   *
   * @param args The arguments passed to the program.
   */
  public static void main(String[] args) {
    BatchTestRunner.run(CurrentTransaction.class);
  }

  /** */
  public void testCurrentTransaction() {
    pm = getPM();
    PCPoint p1 = new PCPoint();
    Transaction tx = pm.currentTransaction();
    tx.begin();
    pm.makePersistent(p1);
    tx.commit();
    pm.close();
    pm = null;
  }
}
