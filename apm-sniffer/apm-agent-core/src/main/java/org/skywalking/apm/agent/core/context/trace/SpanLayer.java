/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.agent.core.context.trace;

/**
 * Span 分层
 *
 * @author wusheng
 */
public enum SpanLayer {

    DB(1),
    RPC_FRAMEWORK(2),
    HTTP(3),
    MQ(4);

    private int code;

    SpanLayer(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static void asDB(AbstractSpan span) {
        span.setLayer(SpanLayer.DB);
    }

    public static void asRPCFramework(AbstractSpan span) {
        span.setLayer(SpanLayer.RPC_FRAMEWORK);
    }

    public static void asHttp(AbstractSpan span) {
        span.setLayer(SpanLayer.HTTP);
    }

    public static void asMQ(AbstractSpan span) {
        span.setLayer(SpanLayer.MQ);
    }
}
