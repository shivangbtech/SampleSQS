package com.example.sqs.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class SqsQueueInitializer {

  @Autowired
  private AmazonSQS amazonSQS;

  @PostConstruct
  public void initQueues() {
    createIfNotExists("my-standard-queue", false);
    createIfNotExists("my-fifo-queue.fifo", true);
    createIfNotExists("my-dlq-queue", false);
  }

  private void createIfNotExists(String queueName, boolean isFifo) {
    try {
      amazonSQS.getQueueUrl(queueName);
    } catch (QueueDoesNotExistException e) {
      Map<String, String> attrs = new HashMap<>();
      if (isFifo) {
        attrs.put("FifoQueue", "true");
        attrs.put("ContentBasedDeduplication", "true");
      }
      amazonSQS.createQueue(new CreateQueueRequest(queueName).withAttributes(attrs));
      System.out.println("Queue created: " + queueName);
    }
  }
}
