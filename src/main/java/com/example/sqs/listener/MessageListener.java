package com.example.sqs.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  @Value("${sqs.standard.queue.name}")
  private String standardQueueName;

  @SqsListener("my-standard-queue")
  public void listenStandardQueue(String message, @Header("SenderId") String senderId) {
    System.out.println("Received from Standard Queue: " + message + " from sender: " + senderId);
  }

  @SqsListener("my-fifo-queue.fifo")
  public void listenFifoQueue(String message) {
    System.out.println("Received from FIFO Queue: " + message);
  }

  @SqsListener("my-dlq-queue")
  public void listenDLQ(String message) {
    System.err.println("Message sent to DLQ: " + message);
  }
}