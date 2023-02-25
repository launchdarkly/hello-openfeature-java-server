import dev.openfeature.sdk.*;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.openfeature.serverprovider.Provider;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    // Set SdkKey to your LaunchDarkly SDK key.
    private static String SdkKey = "";

    // Set FeatureFlagKey to the feature flag key you want to evaluate.
    private static String FeatureFlagKey = "my-boolean-flag";
    public static void main(String[] args) throws IOException {
        LDClient ldClient = new LDClient(SdkKey);

        if(ldClient.isInitialized()) {
            System.out.println("SDK successfully initialized!");
        } else {
            System.out.println("SDK failed to initialize");
            System.exit(1);
            return;
        }

        OpenFeatureAPI.getInstance().setProvider(new Provider(ldClient));
        Client client = OpenFeatureAPI.getInstance().getClient();

        // Set up the user properties. This user should appear on your LaunchDarkly users dashboard
        // soon after you run the demo.
        EvaluationContext context = new ImmutableContext("example-user-key", new HashMap<>(){{
            put("kind", new Value("User"));
            put("name", new Value("Sandy"));
        }});

        boolean value = client.getBooleanValue(FeatureFlagKey, false, context);
        System.out.printf("Feature flag '%s' is %s for this context\n", FeatureFlagKey, value);

        // Here we ensure that the SDK shuts down cleanly and has a chance to deliver analytics
        // events to LaunchDarkly before the program exits. If analytics events are not delivered,
        // the context properties and flag usage statistics will not appear on your dashboard. In a
        // normal long-running application, the SDK would continue running and events would be
        // delivered automatically in the background.
        ldClient.close();
    }
}
