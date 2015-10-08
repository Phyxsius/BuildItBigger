/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.andy.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.andy.example.com",
                ownerName = "backend.andy.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "tellJoke")
    public Joke tellJoke(@Named("joke") String joke) {
        return new Joke(joke);
    }

}
