package com.rishabh.authenticator;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.models.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ContionalRolePrefix implements ConditionalAuthenticator {
    @Override
    public boolean matchCondition(AuthenticationFlowContext authenticationFlowContext) {
        UserModel user = authenticationFlowContext.getUser();
        String prefix = getAllowedPrefix(authenticationFlowContext);
        Set<RoleModel> roleMappings = user.getRoleMappingsStream()
                .filter(roleModel -> roleModel.getName().startsWith(prefix)).collect(Collectors.toSet());

        System.out.println("Role with prefix : " + prefix + " = " + roleMappings.stream().count());
        for (RoleModel s : roleMappings) {
            System.out.println(s.getName());
        }

        if(roleMappings.stream().count() >= 1)
            return false;
        else
            return true;

    }

    private String getAllowedPrefix(AuthenticationFlowContext context) {
        AuthenticatorConfigModel configModel = context.getAuthenticatorConfig();
        Map<String, String> config = configModel.getConfig();
        return config.get(ConditionalRolePrefixFactory.PREFIX);
    }

    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {

    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
