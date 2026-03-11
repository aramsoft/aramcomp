/*
 * $Id: CompleteAutoloadTilesListener.java 799008 2009-07-29 18:46:16Z apetrelli $
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

package org.apache.tiles.extras.complete;

import org.apache.tiles.startup.TilesInitializer;
import org.apache.tiles.web.startup.AbstractTilesListener;

/**
 * Tiles listener that executes {@link CompleteAutoloadTilesInitializer}.
 *
 * @version $Rev: 799008 $ $Date: 2009-07-30 04:46:16 +1000 (Thu, 30 Jul 2009) $
 * @since 2.2.0
 */
public class CompleteAutoloadTilesListener extends AbstractTilesListener {

    /** {@inheritDoc} */
    @Override
    protected TilesInitializer createTilesInitializer() {
        return new CompleteAutoloadTilesInitializer();
    }

}
