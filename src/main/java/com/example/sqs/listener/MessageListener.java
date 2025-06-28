package com.example.sqs.listener;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  @SqsListener("${sqs.standard.queue.name}")
  public void listenStandardQueue(String message, @Header("SenderId") String senderId) {
    System.out.println("Received from Standard Queue: " + message + " from sender: " + senderId);
  }

  @SqsListener("${sqs.fifo.queue.name}")
  public void listenFifoQueue(String message) {
    System.out.println("Received from FIFO Queue: " + message);
  }

  @SqsListener("${sqs.dlq.queue.name}")
  public void listenDLQ(String message) {
    System.err.println("Message sent to DLQ: " + message);
  }
}