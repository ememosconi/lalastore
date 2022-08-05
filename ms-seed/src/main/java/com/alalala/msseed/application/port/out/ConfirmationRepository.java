package com.alalala.msseed.application.port.out;

import com.alalala.msseed.domain.Seed;

import java.util.UUID;

public interface ConfirmationRepository {

    void save(Seed seed, UUID uuid);

    Seed findById(String id);
}
