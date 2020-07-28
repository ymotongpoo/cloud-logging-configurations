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

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;

public class GCLLogger {
    public static void main(final String[] args) {
        try {
            var stream = GCLLogger.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        var logger = Logger.getLogger("jul-sample");

        logger.info("This is info level log.");
        logger.warning("This is warning level log.");
        logger.severe("This is error level log.");
    }
}