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

package main

import (
	"os"
	"time"

	"github.com/rs/zerolog"
)

var logger zerolog.Logger

// c.f. https://cloud.google.com/logging/docs/reference/v2/rest/v2/LogEntry#LogSeverity
func levelFieldMarshalFunc(l zerolog.Level) string {
	switch l {
	case zerolog.TraceLevel:
		return "DEFAULT"
	case zerolog.DebugLevel:
		return "DEBUG"
	case zerolog.InfoLevel:
		return "INFO"
	case zerolog.WarnLevel:
		return "WARNING"
	case zerolog.ErrorLevel:
		return "ERROR"
	case zerolog.FatalLevel:
		return "CRITICAL"
	case zerolog.PanicLevel:
		return "ALERT"
	default:
		return "DEFAULT"
	}
}

func init() {
	zerolog.TimestampFieldName = "timestamp"
	zerolog.TimeFieldFormat = time.RFC3339Nano
	zerolog.LevelFieldName = "severity"
	zerolog.LevelFieldMarshalFunc = levelFieldMarshalFunc
	logger = zerolog.
		New(os.Stdout).
		With().
		Timestamp().
		Logger()
}

func main() {
	t := time.NewTicker(time.Second)
	for range t.C {
		logger.Info().Msg("This is info level log.")
		logger.Warn().Msg("This is warning level log.")
		logger.Error().Msg("This is error level log.")
	}
}
