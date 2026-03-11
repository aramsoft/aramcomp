/*
 * $Id: HasAddableKeys.java 1215004 2011-12-16 00:29:25Z nlebas $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tiles.request.attribute;

/**
 * Allows to get and set attributes.
 *
 * @version $Rev: 1215004 $ $Date: 2011-12-16 11:29:25 +1100 (Fri, 16 Dec 2011) $
 * @param <V> The type of the value of the attribute.
 */
public interface HasAddableKeys<V> extends HasKeys<V>, Addable<V> {
}
