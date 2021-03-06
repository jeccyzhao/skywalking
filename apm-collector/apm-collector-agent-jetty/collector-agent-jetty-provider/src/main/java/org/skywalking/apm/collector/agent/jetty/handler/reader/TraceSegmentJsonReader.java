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

package org.skywalking.apm.collector.agent.jetty.handler.reader;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng-yongsheng
 */
public class TraceSegmentJsonReader implements StreamJsonReader<TraceSegment> {

    private final Logger logger = LoggerFactory.getLogger(TraceSegmentJsonReader.class);

    private UniqueIdJsonReader uniqueIdJsonReader = new UniqueIdJsonReader();
    private SegmentJsonReader segmentJsonReader = new SegmentJsonReader();

    private static final String GLOBAL_TRACE_IDS = "gt";
    private static final String SEGMENT = "sg";

    @Override public TraceSegment read(JsonReader reader) throws IOException {
        TraceSegment traceSegment = new TraceSegment();

        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case GLOBAL_TRACE_IDS:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        traceSegment.addGlobalTraceId(uniqueIdJsonReader.read(reader));
                    }
                    reader.endArray();

                    break;
                case SEGMENT:
                    traceSegment.setTraceSegmentBuilder(segmentJsonReader.read(reader));
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return traceSegment;
    }
}
