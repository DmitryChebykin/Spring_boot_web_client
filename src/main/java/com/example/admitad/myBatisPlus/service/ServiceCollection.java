package com.example.admitad.myBatisPlus.service;

import com.example.admitad.myBatisPlus.service.singleService.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ServiceCollection {
    private final ActionsDetailService actionsDetailService;
    private final ActionsDetailTariffLinkService actionsDetailTariffLinkService;
    private final CategoriesService categoriesService;
    private final ProgramService programService;
    private final RateTariffLinkService rateTariffLinkService;
    private final TariffService tariffService;
}
