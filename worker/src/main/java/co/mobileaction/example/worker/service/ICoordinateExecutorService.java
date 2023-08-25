package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.CoordinateDto;

public interface ICoordinateExecutorService
{
    void executeMessage(CoordinateDto coordinateDto);
}
