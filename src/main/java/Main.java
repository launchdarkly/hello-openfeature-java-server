import dev.openfeature.sdk.*;
import com.launchdarkly.openfeature.serverprovider.Provider;

import java.io.IOException;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("STARTING HELLO EXAMPLE");
        String sdkKey = System.getenv("LAUNCHDARKLY_SDK_KEY");
        System.out.println("GOT SDK KEY");
        String flagKey = System.getenv("LAUNCHDARKLY_FLAG_KEY");
        System.out.println("GOT FLAG KEY");

        sdkKey = sdkKey != null ? sdkKey : "";
        flagKey = flagKey != null ? flagKey : "sample-feature";

        if(sdkKey == null || sdkKey.isEmpty()) {
            System.out.println("Please set an SDK key using the LAUNCHDARKLY_SDK_KEY environment variable.");
            System.exit(1);
        }

        Provider provider = new Provider(sdkKey);

        OpenFeatureAPI.getInstance().setProviderAndWait(provider);
        Client client = OpenFeatureAPI.getInstance().getClient();

        client.onProviderReady(eventDetails -> {
            System.out.println("SDK successfully initialized!");
        });

        client.onProviderError(eventDetails -> {
            System.out.println("SDK failed to initialize");
        });

        // Set up the user properties. This user should appear on your LaunchDarkly users dashboard
        // soon after you run the demo.
        EvaluationContext context = new ImmutableContext("example-user-key", new HashMap<>(){{
            put("kind", new Value("User"));
            put("name", new Value("Sandy"));
        }});

        boolean value = client.getBooleanValue(flagKey, false, context);
        System.out.println("The '" + flagKey + "' feature flag evaluates to " + value + ".");

        OpenFeatureAPI.getInstance().shutdown();
    }
}
