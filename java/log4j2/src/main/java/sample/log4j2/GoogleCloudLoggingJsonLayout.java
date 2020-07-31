// Copyright 2020 Yoshi Yamaguchi
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package sample.log4j2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.util.StringBuilderWriter;

@Plugin(name = "GoogleCloudLoggingJsonLayout",
    category = "Core",
    elementType = "Layout",
    printObject = true)
public class GoogleCloudLoggingJsonLayout extends AbstractStringLayout {
    private static final ObjectMapper mapper = new ObjectMapper();

    protected GoogleCloudLoggingJsonLayout(Charset charset) {
        super(charset);
    }

    @PluginFactory
    public static GoogleCloudLoggingJsonLayout createLayout(
        @PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new GoogleCloudLoggingJsonLayout(charset);
    }

    @Override
    public String toSerializable(LogEvent event) {
        var map = new LinkedHashMap<String, Object>();
        putTimestamp(map, event);
        putSeverity(map, event);
        putMessage(map, event);

        try {
            StringBuilderWriter w = new StringBuilderWriter();
            mapper.writeValue(w, map);
            w.append("\n");
            return w.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static void putTimestamp(Map<String, Object> map, LogEvent event) {
        var i = Instant.ofEpochMilli(event.getTimeMillis());
        var timestamp = ZonedDateTime.ofInstant(i, ZoneOffset.UTC);
        map.put("timestamp", timestamp.toString());
    }

    private static void putSeverity(Map<String, Object> map, LogEvent event) {
        map.put("severity", event.getLevel().toString());
    }

    private static void putMessage(Map<String, Object> map, LogEvent event) {
        map.put("message", event.getMessage().getFormattedMessage());
    }
}