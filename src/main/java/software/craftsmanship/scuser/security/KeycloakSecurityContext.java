package software.craftsmanship.scuser.security;

import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class KeycloakSecurityContext {

    /**
     *
     * @return roles
     */
    public OidcKeycloakAccount getOidcKeycloakAccount(){
        return getKeycloakAuthenticationToken().getAccount();
    }

    /**
     *
     * @return preferredUsername, email, givenName, familyName
     */
    public AccessToken getAccessToken(){
        return getOidcKeycloakAccount().getKeycloakSecurityContext().getToken();
    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    private KeycloakAuthenticationToken getKeycloakAuthenticationToken(){
        return (KeycloakAuthenticationToken) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getUserPrincipal();
    }

}
