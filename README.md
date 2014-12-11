gradle-clojure-skeleton
=======================

A basic skeleton application for Clojure, with the following features:

- uses the [Gradle](https://www.gradle.org/) build tool
- uses the [clojuresqe](https://github.com/clojuresque/clojuresque) Gradle plugin
- main() is in Clojure
- includes local Java files
- includes 3rd party libraries
- can build an uberjar

To run the cider-nrepl server, execute:

```
$ gradle uberjar
$ java -jar build/libs/basic-standalone-0.0.1.jar --cider
```
