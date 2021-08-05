## 0.3.2 (2021-08-05)

### Changes

  - Build for Java 8

## 0.3.1 (2021-08-04)

### Changes

  - Add versionSuffix ([#30](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/pull/31)).

## 0.3.0 (2021-05-17)

### Changes

  - Fix incompatibility with Gradle 7 ([#30](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/pull/30)).

## 0.2.2 (2019-03-29)

### Changes

  - Update AWS client libraries to so the plugin works with Java 11 ([#24](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/pull/24)).

## 0.2.1 (2018-11-26)

### Changes

  - Support authentication using AWS instances profile ([#21](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/issues/21))

## 0.2.0 (2018-04-18)

### Changes

  - Create all tasks during project initialization instead of creating them
    dynamically using a rule. Now they are visible in task list. 
    ([#16](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/pull/16))

## 0.1.3 (2017-11-10)

### Changes

  - Update the publishing plugin since publishing 0.1.2 failed.

## 0.1.2 (2017-11-09)

### Changes

  - Renamed `war` configuration variable to `file` to emphasize universality of this plugin ([#11](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/issues/11))
  - Upgrade Gradle to 4.2

## 0.1.1 (2017-02-27)

### Changes

  - Add ability to specify prefix for the version string ([#9](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/pull/9))

## 0.1.0 (2017-01-16)

### Changes

  - Add random part to S3 key to prevent accidental overwrites (fixes [#8](https://github.com/EvidentSolutions/gradle-beanstalk-plugin/issues/8))
