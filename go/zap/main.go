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
	"log"
	"time"

	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
)

var logger *zap.SugaredLogger

func init() {
	cfg := zap.Config{
		Encoding:         "json",
		Level:            zap.NewAtomicLevelAt(zapcore.DebugLevel),
		OutputPaths:      []string{"stdout"},
		ErrorOutputPaths: []string{"stderr"},
		EncoderConfig: zapcore.EncoderConfig{
			MessageKey:     "message",
			LevelKey:       "severity",
			EncodeLevel:    zapcore.LowercaseLevelEncoder,
			TimeKey:        "timestamp",
			EncodeTime:     zapcore.ISO8601TimeEncoder,
			EncodeDuration: zapcore.StringDurationEncoder,
			CallerKey:      "caller",
			EncodeCaller:   zapcore.ShortCallerEncoder,
		},
	}
	var err error
	l, err := cfg.Build()
	if err != nil {
		log.Fatalf("error on creating zap.SugaredLogger: %v", err)
	}
	logger = l.Sugar()
}

func main() {
	t := time.NewTicker(time.Second)
	for range t.C {
		logger.Info("This is info level log.")
		logger.Warn("This is warning level log.")
		logger.Error("This is error level log.")
	}
}
