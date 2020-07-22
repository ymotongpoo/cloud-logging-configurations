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

'use strict';

const bunyan = require('bunyan');

// @google-cloud/logging has the integrations with bunyan, but
// it is the direct integration with the API.
// https://googleapis.dev/nodejs/logging-bunyan/latest/index.html
//
// This exmaple shows the way to modify the JSON log in Google Cloud Logging
// friendly format.
//
// bunyan doesn't provide option to change field names, so you need to workaround
// by creating your own stream as follows.
// c.f.
//  - https://github.com/trentm/node-bunyan/issues/313
//  - https://github.com/trentm/node-bunyan/blob/2.0.4/lib/bunyan.js#L982
const GoogleCloudLoggingStream = (filepath) => {
    return {
        write: (log) => {
            log.severity = bunyan.nameFromLevel[log.level].toUpperCase();
            // field names are hard coded in mkRecord(), so you need to
            // replace the field names by yourself.
            log.timestamp = log.time;
            delete log.time;
            log.message = log.msg;
            delete log.msg;
            const line = JSON.stringify(log, bunyan.safeCycles(), 0);
            console.log(line);
        }
    }
};

const logger = bunyan.createLogger({
    name: 'bunyan-json-log-sample',
    level: 'info',
    streams: [{
        type: 'raw',
        stream: GoogleCloudLoggingStream()
    }]
});

logger.info('This is info level log.');
logger.warn('This is warning level log.');
logger.error('This is error level log.');
