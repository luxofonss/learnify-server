package com.quyennv.lms.core.domain.entities;

import lombok.Value;

import java.util.UUID;

@Value
public class Identity {
    UUID id;
    public static Identity newIdentity() {
        return new Identity(UUID.randomUUID());
    }

    public static Identity from(UUID id) {
        return new Identity(id);
    }
    public static Identity fromString(String id) {

        return new Identity(UUID.fromString(id));
    }
}
