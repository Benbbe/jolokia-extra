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

public class JMSEndpointStatsSimplifier<T extends JMSEndpointStats> extends StatsSimplifier<T> {
    @SuppressWarnings("unchecked")
    public JMSEndpointStatsSimplifier() {
        this((Class<T>) JMSEndpointStats.class);
    }

	protected JMSEndpointStatsSimplifier(Class<T> type) {
        super(type);
        addExtractors(new Object[][]{
                {"expiredMessageCount", new ExpiredMessageCountExtractor<T>()},
                {"messageCount", new MessageCountExtractor<T>()},
                {"messageWaitTime", new MessageWaitTimeExtractor<T>()},
                {"pendingMessageCount", new PendingMessageCountExtractor<T>()}
        });
    }

    private class ExpiredMessageCountExtractor<T extends JMSEndpointStats> implements AttributeExtractor<T> {
        public CountStatistic extract(T o) {
            return o.getExpiredMessageCount();
        }
    }

    private class MessageCountExtractor<T extends JMSEndpointStats> implements AttributeExtractor<T> {
        public CountStatistic extract(T o) {
            return o.getMessageCount();
        }
    }

    private class MessageWaitTimeExtractor<T extends JMSEndpointStats> implements AttributeExtractor<T> {
        public TimeStatistic extract(T o) {
            return o.getMessageWaitTime();
        }
    }

    private class PendingMessageCountExtractor<T extends JMSEndpointStats> implements AttributeExtractor<T> {
        public CountStatistic extract(T o) {
            return o.getPendingMessageCount();
        }
    }
}
