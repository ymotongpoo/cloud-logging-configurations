# JSON formatted structured logging for Java

There is [the summary doc](https://cloud.google.com/functions/docs/concepts/java-logging) that describes how to output logs in the correct structured log format with [Java Logging API (JUL)](https://docs.oracle.com/javase/jp/11/docs/api/java.logging/java/util/logging/Logger.html) on Google Cloud Functions.

Google Cloud Functions Framework for Java provides its own [JSON log handler](https://github.com/GoogleCloudPlatform/functions-framework-java/blob/master/invoker/core/src/main/java/com/google/cloud/functions/invoker/gcf/JsonLogHandler.java), and it's recommended to use for its purpose on GCF.

In this repository, the collection of samples are intended to use on another platforms such as Google Kubernetes Engine and Google Cloud Run, where the application normally doesn't use the Function Framework.