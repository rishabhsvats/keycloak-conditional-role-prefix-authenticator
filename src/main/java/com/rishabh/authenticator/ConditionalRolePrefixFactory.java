package com.rishabh.authenticator;

import static org.keycloak.provider.ProviderConfigProperty.STRING_TYPE;
import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.Collections;
import java.util.List;

public class ConditionalRolePrefixFactory implements ConditionalAuthenticatorFactory {

    public static final String ID = "conditional_role_prefix";
    static final String PREFIX = "prefix-of-role";


    private static final ContionalRolePrefix SINGLETON = new ContionalRolePrefix();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public ConditionalAuthenticator getSingleton() {
        return null;
    }

    @Override
    public String getDisplayType() {
        return "Condition - role prefix";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[] { AuthenticationExecutionModel.Requirement.REQUIRED };
    }
    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Allow login with only specified prefix";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        ProviderConfigProperty name = new ProviderConfigProperty();

        name.setType(STRING_TYPE);
        name.setName(PREFIX);
        name.setLabel("Role Prefix");
        name.setHelpText("Role Prefix");

        return Collections.singletonList(name);
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return ID;
    }

}
