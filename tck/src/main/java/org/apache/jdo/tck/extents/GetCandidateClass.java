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

package org.apache.jdo.tck.extents;

import javax.jdo.Extent;
import org.apache.jdo.tck.pc.company.Employee;
import org.apache.jdo.tck.util.BatchTestRunner;

/**
 * <B>Title:</B> Get Candidate Class <br>
 * <B>Keywords:</B> extent <br>
 * <B>Assertion ID:</B> A15.3-7. <br>
 * <B>Assertion Description: </B> <code>Extent.getCandidateClass()</code> returns the class of the
 * instances contained in this <code>Extent</code>.
 */
public class GetCandidateClass extends ExtentTest {

  /** */
  private static final String ASSERTION_FAILED = "Assertion A15.3-7 (GetCandidateClass) failed: ";

  /**
   * The <code>main</code> is called when the class is directly executed from the command line.
   *
   * @param args The arguments passed to the program.
   */
  public static void main(String[] args) {
    BatchTestRunner.run(GetCandidateClass.class);
  }

  /** */
  public void test() {
    Extent<Employee> ex = getExtent();
    if (Employee.class != ex.getCandidateClass()) {
      fail(ASSERTION_FAILED, "Candidate class in extent should be: " + Employee.class.getName());
    }
  }
}
