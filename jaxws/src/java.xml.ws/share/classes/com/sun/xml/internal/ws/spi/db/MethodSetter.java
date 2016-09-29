/*
 * Copyright (c) 1997, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.xml.internal.ws.spi.db;

import static com.sun.xml.internal.ws.spi.db.PropertyGetterBase.verifyWrapperType;
import java.lang.reflect.Method;
import javax.xml.ws.WebServiceException;


/**
 * MethodInjection
 * @author shih-chang.chen@oracle.com
 * @exclude
 */
public class MethodSetter extends PropertySetterBase {

    private Method method;

    public MethodSetter(Method m) {
        verifyWrapperType(m.getDeclaringClass());
        method = m;
        type = m.getParameterTypes()[0];
    }

    public Method getMethod() {
        return method;
    }

    public <A> A getAnnotation(Class<A> annotationType) {
        Class c = annotationType;
        return (A) method.getAnnotation(c);
    }

    public void set(final Object instance, Object val) {
        final Object resource = (type.isPrimitive() && val == null)? uninitializedValue(type): val;
        final Object[] args = {resource};
        try {
            method.invoke(instance, args);
        } catch (Exception e) {
            throw new WebServiceException(e);
        }
    }
}
