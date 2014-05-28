/*
 *  Copyright 2012 Marcin Plonka
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


/**
 * @author mplonka
 */
package org.jolokia.extra.addon.jsr77.simplifier.stats;

import javax.management.j2ee.statistics.*;

public class JCAStatsSimplifier extends StatsSimplifier<JCAStats> {

    public JCAStatsSimplifier() {
        this(JCAStats.class);
    }

	protected JCAStatsSimplifier(Class<JCAStats> type) {
		super(type);
		addExtractor("connectionPools", new ConnectionPoolsExtractor());
		addExtractor("connections", new ConnectionsExtractor());
	}

    private static class ConnectionPoolsExtractor implements AttributeExtractor<JCAStats> {
        public JCAConnectionPoolStats[] extract(JCAStats o) {
            return o.getConnectionPools();
        }
    }

    private static class ConnectionsExtractor implements AttributeExtractor<JCAStats> {
        public JCAConnectionStats[] extract(JCAStats o) {
            return o.getConnections();
        }
    }
}
