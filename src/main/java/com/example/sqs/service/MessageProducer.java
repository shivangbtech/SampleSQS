package com.example.sqs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

  private final AmazonSQSAsync amazonSQSAsync;

  @Value("${sqs.standard.queue.url}")
  private String standardQueueUrl;

  @Value("${sqs.fifo.queue.url}")
  private String fifoQueueUrl;

  public MessageProducer(AmazonSQSAsync amazonSQSAsync) {
    this.amazonSQSAsync = amazonSQSAsync;
  }

  public void sendToStandardQueue(String message) {
    amazonSQSAsync.sendMessage(new SendMessageRequest(standardQueueUrl, message));
  }

  public void sendToFifoQueue(String message) {
    SendMessageRequest request = new SendMessageRequest()
        .withQueueUrl(fifoQueueUrl)
        .withMessageBody(message)
        .withMessageGroupId("group1")
        .withMessageDeduplicationId(String.valueOf(System.currentTimeMillis()));
    amazonSQSAsync.sendMessage(request);
  }
}

