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

package org.apache.jdo.tck.api.jdohelper;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;
import org.apache.jdo.tck.JDO_Test;
import org.apache.jdo.tck.pc.mylib.Point;
import org.apache.jdo.tck.util.BatchTestRunner;

/**
 * <B>Title:</B> Is New Not Persistence Capable <br>
 * <B>Keywords:</B> jdohelper <br>
 * <B>Assertion ID:</B> A8.4.4-4 <br>
 * <B>Assertion Description: </B> The jdohelper.isNew method returns false If the instance does not
 * implement PersistenceCapable evaluating to true when == is used.
 */
/*
 * Revision History
 * ================
 * Author         :   	Date   : 	Version
 * Azita Kamangar  	10/2/01		 1.0
 */

public class IsNewNotPersistenceCapable extends JDO_Test {

  /** */
  private static final String ASSERTION_FAILED =
      "Assertion A8.4.4-4 (IsNewNotPersistenceCapable) failed: ";

  /**
   * The <code>main</code> is called when the class is directly executed from the command line.
   *
   * @param args The arguments passed to the program.
   */
  public static void main(String[] args) {
    BatchTestRunner.run(IsNewNotPersistenceCapable.class);
  }

  /* test JDOHelper.isNew(Object pc)
   *
   */
  public void testIsNewNotPersistenceCapable() {
    pm = getPM();
    Transaction tx = pm.currentTransaction();
    tx.begin();
    Point p1 = new Point(1, 3);
    boolean isNew = JDOHelper.isNew(p1);
    tx.commit();
    if (isNew) fail(ASSERTION_FAILED, "JDOHelper.isNew returns true for instance of non-pc class.");
    pm.close();
    pm = null;
  }
}
