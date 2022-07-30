package com.launchdarkly.tutorial;

import com.launchdarkly.sdk.server.*;

import java.io.IOException;

import com.launchdarkly.sdk.*;

public class App {
  public static void main(String... args) throws IOException {
    // Create a new LDClient with your environment-specific SDK key
    LDClient ldClient = new LDClient("YourKey");

    // Set up the user properties. This user should appear on your
    // LaunchDarkly users dashboard soon after you run the demo.
    LDUser user = new LDUser.Builder("UNIQUE IDENTIFIER")
                            .firstName("Brian")
                            .lastName("Ludlaw")
                            .custom("groups", LDValue.buildArray().add("beta_testers").build())
                            .build();                                   

    // Here the value of the user is being predifined as false. However from the targeting side we can use the LDUSER variables defined above to set the value to false
    // This then allows us to quickly verify who has access to a specific code block                        
    boolean showFeature = ldClient.boolVariation("FirstFlag", user, false);

  
  
    if (showFeature){
        System.out.println("SDK successfully connected! The value of FirstFlag is " + showFeature + "for " + user.getFirstName());
    }
    else{
        System.out.println("SDK successfully connected! The value of FirstFlag is " + showFeature + "for " + user.getFirstName());   
    }

    // Here we ensure that the SDK shuts down cleanly and has a chance to deliver analytics
    // events to LaunchDarkly before the program exits. If analytics events are not delivered,
    // the user properties and flag usage statistics will not appear on your dashboard. In a
    // normal long-running application, the SDK would continue running and events would be
    // delivered automatically in the background.
    ldClient.close();
  }
}