# LaunchDarkly Sample OpenFeature Java Server application

We've built a simple console that demonstrates how LaunchDarkly's OpenFeature provider works.

## Build instructions

This project uses Gradle. It requires that Java is already installed on your system (version 11 or higher). It will automatically use the latest release of the LaunchDarkly SDK with major version 6.

1. Edit `src/main/java/Main.Java` and set the value of `SdkKey` to your LaunchDarkly SDK key. If there is an existing boolean feature flag in your LaunchDarkly project that you want to evaluate, set `FeatureFlagKey` to the flag key.

```
    private static String SdkKey = "1234567890abcdef";

    private static String FeatureFlagKey = "my-flag";
```

2. On the command line, run `./gradlew run`.

You should see the message `"Feature flag '<flag key>' is <true/false> for this context"`.
