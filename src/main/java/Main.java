import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.ImmutableContext;
import dev.openfeature.sdk.OpenFeatureAPI;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.openfeature.serverprovider.Provider;

public class Main {
    // Set SdkKey to your LaunchDarkly SDK key.
    private static String SdkKey = "";

    // Set FeatureFlagKey to the feature flag key you want to evaluate.
    private static String FeatureFlagKey = "my-boolean-flag";
    public static void main(String[] args) {
        LDClient ldClient = new LDClient(SdkKey);
        OpenFeatureAPI.getInstance().setProvider(new Provider(ldClient));

        if(ldClient.isInitialized()) {
            System.out.println("SDK successfully initialized!");
        } else {
            System.out.println("SDK failed to initialize");
        }

        Client client = OpenFeatureAPI.getInstance().getClient();

        boolean value = client.getBooleanValue(FeatureFlagKey, false, new ImmutableContext("example-user-key"));
        System.out.printf("Feature flag '%s' is %s for this context\n", FeatureFlagKey, value);
    }
}
