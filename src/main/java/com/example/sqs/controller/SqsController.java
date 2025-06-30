package com.example.sqs.controller;

import com.example.sqs.service.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sqs")
public class SqsController {

  private final MessageProducer producer;

  public SqsController(MessageProducer producer) {
    this.producer = producer;
  }

  @PostMapping("/standard")
  public ResponseEntity<String> sendStandard(@RequestParam String message) {
    producer.sendToStandardQueue(message);
    return ResponseEntity.ok("Sent to standard queue");
  }

  @PostMapping("/fifo")
  public ResponseEntity<String> sendFifo(@RequestParam String message) {
    producer.sendToFifoQueue(message);
    return ResponseEntity.ok("Sent to FIFO queue");
  }
}