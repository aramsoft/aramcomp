/*
 * $Id: WildcardDefinitionPatternMatcher.java 795343 2009-07-18 11:26:09Z apetrelli $
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

package org.apache.tiles.definition.pattern.wildcard;

import java.util.List;

import org.apache.tiles.Definition;
import org.apache.tiles.definition.pattern.DefinitionPatternMatcher;
import org.apache.tiles.definition.pattern.PatternUtil;
import org.apache.tiles.util.WildcardHelper;

/**
 * Matches wildcard patterns in definitions.
 *
 * @version $Rev: 795343 $ $Date: 2009-07-18 21:26:09 +1000 (Sat, 18 Jul 2009) $
 * @since 2.2.0
 */
public class WildcardDefinitionPatternMatcher implements
        DefinitionPatternMatcher {

    /**
     * Allows to parse wildcard expressions and to recognize substitution
     * variables.
     */
    private WildcardHelper wildcardHelper;

    /**
     * The definition to use as a basis.
     */
    private Definition definition;

    /**
     * The pattern to use.
     */
    private int[] pattern;

    /**
     * Constructor.
     *
     * @param pattern The pattern to use, in string form.
     * @param definition The definition to use as a basis.
     * @param wildcardHelper The object that parses wildcard expressions and
     * recognized substitution variables.
     * @since 2.2.0
     */
    public WildcardDefinitionPatternMatcher(String pattern,
            Definition definition, WildcardHelper wildcardHelper) {
        this.wildcardHelper = wildcardHelper;
        this.definition = definition;
        this.pattern = wildcardHelper.compilePattern(pattern);
    }

    /** {@inheritDoc} */
    public Definition createDefinition(String definitionName) {
        List<String> vars = wildcardHelper.match(definitionName, pattern);
        Definition d = null;

        if (vars != null) {
            d = PatternUtil.replacePlaceholders(definition, definitionName,
                    vars.toArray());
        }

        return d;
    }
}
