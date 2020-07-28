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

package sample.jul;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;

public class JsonFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        var json = new StringBuilder("{");
        appendSeverity(json, record);
        appendMessage(json, record);
        appendTimestamp(json, record);
        json.append("}\n");
        return json.toString();
    }

    private static void appendSeverity(StringBuilder json, LogRecord record) {
        // Note: JUL doesn't proivde the severity level "ERROR", and some work around
        // is required to make the formatter Google Cloud Logging.
        // ref. https://docs.oracle.com/en/java/javase/14/docs/api/java.logging/java/util/logging/Level.html
        var l = record.getLevel();
        var severity = l.getName();
        if (l == Level.SEVERE) {
            severity = "ERROR";
        }
        json.append("\"severity\": \"").append(severity).append("\", ");
    }

    private static void appendMessage(StringBuilder json, LogRecord record) {
        // Note: record.getMessage() needs to be escaped when we do this in production.
        json.append("\"message\": \"").append(record.getMessage());
        json.append("\", ");
    }

    private static void appendTimestamp(StringBuilder json, LogRecord record) {
        var i = Instant.ofEpochMilli(record.getMillis());
        var timestamp = ZonedDateTime.ofInstant(i, ZoneOffset.UTC);
        json.append("\"timestamp\": \"").append(timestamp.toString());
        json.append("\"");
    }

}