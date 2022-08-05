package com.alalala.msseed.application.usecase;

import com.alalala.msseed.application.exception.BusinessException;
import com.alalala.msseed.application.port.out.ConfirmationRepository;
import com.alalala.msseed.application.port.out.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.alalala.msseed.adapter.kafka.exeption.NotificationException;
import com.alalala.msseed.application.port.in.CreateSeedCommand;
import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.domain.Seed;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class CreateSeedUseCase implements CreateSeedCommand {

    private final NotificationRepository notificationRepository;
    private final ConfirmationRepository confirmationRepository;

    public CreateSeedUseCase(NotificationRepository notificationRepository,
                             ConfirmationRepository confirmationRepository) {
        this.notificationRepository = notificationRepository;
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public Seed createSeed(Command command) {

        Seed seed = Seed.builder()
                .seedAmount(command.getAmount())
                .seedDate(command.getDate())
                .build();

        Optional.ofNullable(command.getAmount()).orElseThrow(() -> new BusinessException(ErrorCode.AMOUNT_INVALID));

        try {
            UUID uuid = UUID.randomUUID();
            confirmationRepository.save(seed, uuid);
            notificationRepository.notify(seed, uuid);
        } catch (NotificationException ne) {
            log.error("Error al notificar el seed creado");
        }

        return seed;
    }
}
