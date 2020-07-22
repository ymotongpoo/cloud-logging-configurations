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

const winston = require('winston');

const GoogleCloudLoggingFormatter = winston.format((info, opts={}) => {
    info['severity'] = info['level'].toUpperCase();
    return info;
});

const logger = winston.createLogger({
    level: 'info',
    format: winston.format.combine(
        winston.format.timestamp(),
        GoogleCloudLoggingFormatter(), // This must be before winston.fomrat.json()
        winston.format.json(),
    ),
    transports: [
        new winston.transports.Console({ level: 'info' })
    ]
});

logger.info('This is info level log.');
logger.warn('This is warning level log.');
logger.error('This is error level log.');