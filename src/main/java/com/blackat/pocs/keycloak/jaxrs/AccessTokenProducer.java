package com.blackat.pocs.keycloak.jaxrs;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class AccessTokenProducer
{
    @Inject
    private HttpServletRequest request;

    @Produces
    public AccessToken getAccessToken()
    {
        KeycloakPrincipal userPrincipal = (KeycloakPrincipal) request.getUserPrincipal();
        if (null != userPrincipal)
        {
            return userPrincipal.getKeycloakSecurityContext().getToken();
        }

        return null;
    }
}
