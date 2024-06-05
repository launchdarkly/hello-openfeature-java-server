# LaunchDarkly Sample OpenFeature Java Server application

We've built a simple console that demonstrates how LaunchDarkly's OpenFeature provider works.

## Build instructions

This project uses Gradle. It requires that Java is already installed on your system (version 11 or higher). It will automatically use the latest release of the LaunchDarkly SDK with major version 6.

1. Set the environment variable `LAUNCHDARKLY_SDK_KEY` to your LaunchDarkly SDK key. If there is an existing boolean feature flag in your LaunchDarkly project that you want to evaluate, set `LAUNCHDARKLY_FLAG_KEY` to the flag key; otherwise, a boolean flag of `sample-feature` will be assumed.

    ```bash
    export LAUNCHDARKLY_SDK_KEY="1234567890abcdef"
    export LAUNCHDARKLY_FLAG_KEY="my-boolean-flag"
    ```

2. On the command line, run `./gradlew run`.

You should see the message `"The <flag key> feature flag evaluates to <true/false>"`.
