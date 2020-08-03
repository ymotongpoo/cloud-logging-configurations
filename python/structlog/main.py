#!/usr/bin/python
# Copyright 2020 Yoshi Yamaguchi
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import structlog

def field_name_modifier(logger, log_method, event_dict):
    event_dict["severity"] = event_dict["level"]
    del event_dict["level"]
    return event_dict

def getJSONLogger():
    structlog.configure(
        processors=[
            structlog.stdlib.add_log_level,
            field_name_modifier,
            structlog.processors.TimeStamper("iso"),
            structlog.processors.JSONRenderer(),
        ]
    )
    return structlog.get_logger()

def main():
    logger = getJSONLogger()
    logger.info("This is info level log.")
    logger.warn("This is warning level log.")
    logger.error("This is error level log.")

if __name__ == '__main__':
    main()