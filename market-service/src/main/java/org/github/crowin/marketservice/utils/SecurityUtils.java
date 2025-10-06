package org.github.crowin.marketservice.utils;

import org.github.crowin.marketservice.exception.ClientBasicException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUtils {

    /**
     * Get the user id from the security context.
     * @return User id or empty if not authenticated.
     */
    private Optional<Long> getUserIdOptional() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getPrincipal() instanceof Long ? Optional.of((long) auth.getPrincipal()) : Optional.empty();
    }

    public long getUserId() {
        return getUserIdOptional().orElseThrow(() -> new ClientBasicException("UserId isn't found", ClientErrorCode.USER_ID_NOT_FOUND));
    }


}
