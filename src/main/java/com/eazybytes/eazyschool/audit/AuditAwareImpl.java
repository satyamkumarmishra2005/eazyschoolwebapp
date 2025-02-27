package com.eazybytes.eazyschool.audit;

import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")

public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    //This method helps the Spring data Jpa to identify who is the logged in user
    //trying to perform the operation
    // I fthis Operation is performed by some anonymous user then this method will return null
    public Optional<String> getCurrentAuditor(){
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
