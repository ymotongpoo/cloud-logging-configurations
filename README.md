# cloud-logging-configurations
A collection of configurations of popular logging libraries for Cloud Logging setup

## Objective

The aim of this repository is to collect samples to configure popular logging libraries
to output JSON formatted structured logs eligible for Google Cloud Logging.

In order for Google Cloud Logging to recognize JSON formatted structure logs properly,
there are a couple of conditions:

* Logs are output to stdout
* Some field names are set correctly (eg. `severity`, `message`)

These conditions does not match the default configrations of popular logging libraries,
sometimes developers need to find out complex work around to make logs
Google Cloud Logging aware. To visualize those efforts all in one place, this repository
is created for its experimental purpose.

## Libraries

This repository contains the samples of logging libraries to be
Google Cloud Logging aware in variety of languages.

* Go
  * [github.com/sirupsen/logrus](https://pkg.go.dev/github.com/sirupsen/logrus)
  * [go.uber.org/zap](https://pkg.go.dev/go.uber.org/zap)
  * [github.com/rs/zerolog](https://pkg.go.dev/github.com/rs/zerolog)
* Python
  * [structlog](https://pypi.org/project/structlog/)
  * [json-logging](https://pypi.org/project/json-logging/)
  * [python-json-logger](https://pypi.org/project/python-json-logger/)
* Node.js
  * [pino](https://www.npmjs.com/package/pino)
  * [winston](https://www.npmjs.com/package/winston)
  * [bunyan](https://www.npmjs.com/package/bunyan)
* Java
  * [JUL](https://docs.oracle.com/en/java/javase/14/docs/api/java.logging/java/util/logging/package-summary.html)
* PHP
  * [monolog](https://packagist.org/packages/monolog/monolog)