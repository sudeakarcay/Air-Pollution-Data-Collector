package co.mobileaction.example.web.queue;

import co.mobileaction.example.common.dto.CoordinateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinateQueueService implements ICoordinateQueueService
{
    private final AmqpTemplate coordinateQueueTemplate;

    @Override
    public void queueRequestForCoordinates(CoordinateDto coordinateDto)
    {
        coordinateQueueTemplate.convertAndSend(coordinateDto);

    }
}
