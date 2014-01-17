/**
 * Copyright (C) 2012 Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.tang;

import javax.inject.Inject;

import org.junit.Test;

import com.microsoft.tang.exceptions.BindException;
import com.microsoft.tang.exceptions.InjectionException;

public class TestExternalConstructor {

  static final class A {
    A() {
    }
  }

  static final class B {
    B(final A a) {
    }
  }

  static final class ACons implements ExternalConstructor<A> {

    @Inject
    ACons() {
    }

    @Override
    public A newInstance() {
      return new A();
    }
  }

  static final class BCons implements ExternalConstructor<B> {

    @Inject
    BCons(final A a) {
    }

    @Override
    public B newInstance() {
      return new B(null);
    }
  }

  @Test
  public void testExternalConstructor() throws BindException,
      InjectionException {

    final JavaConfigurationBuilder b = Tang.Factory.getTang()
        .newConfigurationBuilder();
    b.bindConstructor(A.class, ACons.class);
    b.bindConstructor(B.class, BCons.class);

    Tang.Factory.getTang().newInjector(b.build()).getInstance(B.class);
  }

/*  @Test
  public void testExplicitExternalConstructorIsSingleton() throws BindException, InjectionException {
    final JavaConfigurationBuilder b = Tang.Factory.getTang()
        .newConfigurationBuilder();
    b.bindConstructor(A.class, ACons.class);

    Injector i = Tang.Factory.getTang().newInjector(b.build());
    
    A a = i.getInstance(A.class);
    A aa = (i.getInstance(ACons.class)).newInstance();
    
    Assert.assertTrue(a == aa);

  } */
}
