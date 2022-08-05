package com.alalala.msseed.application.port.out;

import org.springframework.data.domain.Page;
import com.alalala.msseed.domain.Seed;

public interface SeedRepository {
    Page<Seed> getByFilters(int page, int size);
}
