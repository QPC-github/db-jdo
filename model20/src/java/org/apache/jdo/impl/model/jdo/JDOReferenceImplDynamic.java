/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package org.apache.jdo.impl.model.jdo;

import org.apache.jdo.model.java.JavaType;
import org.apache.jdo.model.jdo.JDOReference;

/**
 * An instance of this class represents the JDO relationship metadata 
 * of a reference relationship field.
 *
 * @author Michael Bouschen
 */
public class JDOReferenceImplDynamic extends JDORelationshipImpl 
    implements JDOReference
{

    /**
     * Determines whether this JDORelationship represents a reference
     * relationship or not. A return of <code>true</code> means this
     * JDORelationship is a JDOReference instance.
     * @return <code>true</code> if this JDORelationship represents a
     * reference relationship; <code>false</code> otherwise.
     */
    public boolean isJDOReference() {
        return true;
    }

    //========= Internal helper methods ==========

    /** 
     * Get the type representation of the relationship. This will be 
     * the JavaType for references, the element type for collections
     * and arrays, and the value type for maps.
     * @return the relationship type
     */
    public JavaType getRelatedJavaType() {
        return getDeclaringField().getType();
    }

}
