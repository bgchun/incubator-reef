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
package com.microsoft.tang.util;

import java.util.Map;
import java.util.TreeMap;

public class MonotonicTreeMap<T, U> extends TreeMap<T, U> {
  private static final long serialVersionUID = 1L;

  @Override
  public U put(T key, U value) {
    U old = super.get(key);
    if (old != null) {
      throw new IllegalArgumentException("Attempt to re-add: [" + key
          + "]\n old value: " + old + " new value " + value);
    }
    return super.put(key, value);
  }

  @Override
  public void putAll(Map<? extends T, ? extends U> m) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public U remove(Object o) {
    throw new UnsupportedOperationException();
  }
}