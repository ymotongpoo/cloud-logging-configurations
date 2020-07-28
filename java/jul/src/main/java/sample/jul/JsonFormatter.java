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

import org.json.JSONObject;

public class JsonFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        JSONObject data = new JSONObject();
        Instant i = Instant.ofEpochMilli(record.getMillis());
        ZonedDateTime timestamp = ZonedDateTime.ofInstant(i, ZoneOffset.UTC);
        data.put("message", record.getMessage());
        data.put("timestamp", timestamp);

        // Note: JUL doesn't proivde the severity level "ERROR", and some work around
        // is required to make the formatter Google Cloud Logging.
        // ref. https://docs.oracle.com/en/java/javase/14/docs/api/java.logging/java/util/logging/Level.html
        Level l = record.getLevel();
        String severity = l.getName();
        if (l == Level.SEVERE) {
            severity = "ERROR";
        }
        data.put("severity", severity);
        return data.toString()+"\n";
    }
}