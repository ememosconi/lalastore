package com.alalala.msseed.application.port.out;

import com.alalala.msseed.domain.Seed;

import java.util.UUID;

public interface NotificationRepository {

    void notify(Seed seed, UUID notificationId);
}
