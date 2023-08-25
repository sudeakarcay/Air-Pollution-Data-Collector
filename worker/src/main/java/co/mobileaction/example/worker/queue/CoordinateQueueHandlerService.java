package co.mobileaction.example.worker.queue;

import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.worker.service.ICoordinateExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CoordinateQueueHandlerService implements ICoordinateQueueHandlerService
{
    private final AmqpTemplate coordinateRequestProblemQueueTemplate;

    private final ICoordinateExecutorService coordinateExecutorService;

    @RabbitListener(queues = "${messaging.coordinate.queue.request}", containerFactory = "coordinateQueueListener")
    public void handleMessage(CoordinateDto result)
    {
        try
        {
            coordinateExecutorService.executeMessage(result);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for city name: {}", result.getName(), e);

            coordinateRequestProblemQueueTemplate.convertAndSend(result);
        }
    }
}
