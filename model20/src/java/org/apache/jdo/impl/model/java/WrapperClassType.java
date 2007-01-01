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

package org.apache.jdo.impl.model.java;

import org.apache.jdo.model.java.JavaType;

/**
 * A WrapperClassType instance represents a Java wrapper class type. 
 * There are eight Java wrapper class types: 
 * <code>java.lang.Boolean</code>, <code>java.lang.Byte</code>, 
 * <code>java.lang.Short</code>, <code>java.lang.Integer</code>, 
 * <code>java.lang.Long</code>, <code>java.lang.Character</code>, 
 * <code>java.lang.Float</code>, <code>java.lang.Double</code>.
 * 
 * <p>
 * Class PredefinedType provides public static final variables referring
 * to the JavaType representation for wrapper class types.
 * 
 * @see PredefinedType#booleanClassType
 * @see PredefinedType#byteClassType
 * @see PredefinedType#shortClassType
 * @see PredefinedType#integerClassType
 * @see PredefinedType#longClassType 
 * @see PredefinedType#characterClassType
 * @see PredefinedType#floatClassType
 * @see PredefinedType#doubleClassType 
 *
 * @author Michael Bouschen
 * @since JDO 1.0.1
 */
public class WrapperClassType 
    extends ValueClassType
{
    /** */
    private PrimitiveType wrappedPrimitiveType;

    /** */
    protected WrapperClassType(Class clazz, JavaType superclass, boolean orderable)
    {
        super(clazz, superclass, orderable);
    }

    /** */
    public boolean isWrapperClass()
    {
        return true;
    }

    // ===== Methods not defined in JavaType =====

    /** */
    public PrimitiveType getWrappedPrimitiveType()
    {
        return wrappedPrimitiveType;
    }
    
    /** */
    void setWrappedPrimitiveType(PrimitiveType wrappedPrimitiveType)
    {
        this.wrappedPrimitiveType = wrappedPrimitiveType;
    }
    
}
