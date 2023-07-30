package com.ecore.roles.exception;

import static java.lang.String.format;

public class InvalidArgumentDependencyException extends RuntimeException {

    public <T> InvalidArgumentDependencyException(
            Class<T> resource,
            String resourceDepends,
            String resourceBelongs) {
        super(
                format(
                        "Invalid '%s' object. The provided %s doesn't belong to the provided %s.",
                        resource.getSimpleName(),
                        resourceDepends.toLowerCase(),
                        resourceBelongs.toLowerCase()));
    }
}
