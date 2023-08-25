package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.CoordinateDto;

public interface ICoordinateQueueService
{
  void queueRequestForCoordinates(CoordinateDto coordinateDto);
}
