/*
 * Copyright 2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
 
package org.apache.jdo.tck.query;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.jdo.tck.pc.mylib.PCPoint;
import org.apache.jdo.tck.util.BatchTestRunner;

/**
 *<B>Title:</B> New Query with Candidate Class and Collection
 *<BR>
 *<B>Keywords:</B> query extent
 *<BR>
 *<B>Assertion ID:</B> A14.5-7.
 *<BR>
 *<B>Assertion Description: </B> <code>PersistenceManager.newQuery(Class cls, Collection cln)</code> constructs a query instance with the candidate class and candidate <code>Collection</code> specified.
 */

public class NewQueryWithCandidateClassAndCollection extends QueryTest {

    /** */
    private static final String ASSERTION_FAILED = 
        "Assertion A14.5-7 (NewQueryWithCandidateClassAndCollection) failed: ";
    
    /**
     * The <code>main</code> is called when the class
     * is directly executed from the command line.
     * @param args The arguments passed to the program.
     */
    public static void main(String[] args) {
        BatchTestRunner.run(NewQueryWithCandidateClassAndCollection.class);
    }

    /** */
    public void test() {
        pm = getPM();

        initDatabase(pm, PCPoint.class);
        runTestNewQuery(pm);

        pm.close();
        pm = null;
    }

    /** */
    void runTestNewQuery(PersistenceManager pm) {
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();

            Query query = pm.newQuery(PCPoint.class, inserted);
            Object results = query.execute();

            // check query result
            printOutput(results, inserted);
            checkQueryResultWithoutOrder(ASSERTION_FAILED, results, inserted);
            
            tx.commit();
            tx = null;
        }
        finally {
            if ((tx != null) && tx.isActive())
                tx.rollback();
        }
    }
}

