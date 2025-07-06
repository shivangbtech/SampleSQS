package com.example.sqs.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class AwsConfig {

  @Value("${cloud.aws.region.static}")
  private String region;

  @Value("${cloud.aws.credentials.access-key}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secret-key}")
  private String secretKey;

  @Value("${cloud.aws.sqs.endpoint}")
  private String endpoint;

  @Bean
  @Primary
  @Scope("singleton")
  public AmazonSQSAsync amazonSQSAsync() {
    System.out.println("Shivang amazonSQSAsync is called");
    return AmazonSQSAsyncClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("endpoint", region))
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .build();
  }

//  @Bean
//  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
//    System.out.println("Shivang simpleMessageListenerContainerFactory is called");
//    SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
//    factory.setAmazonSqs(amazonSQSAsync);
//    return factory;
//  }
//
//  @Bean
//  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//    return null; // or throw new UnsupportedOperationException()
//  }

//  @Bean
//  @Primary
//  @Scope("singleton")
//  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//    AmazonSQSAsync amazonSQSAsync = amazonSQSAsync();
//    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//    container.setAmazonSqs(amazonSQSAsync);
//    container.setMessageHandler(queueMessageHandler(amazonSQSAsync));
//    container.setMaxNumberOfMessages(10);
//    container.setWaitTimeOut(10);
//    container.setAutoStartup(true);
//    return container;
//  }
//
//  @Bean
//  public QueueMessageHandler queueMessageHandler(AmazonSQSAsync amazonSQSAsync) {
//    QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
//    factory.setAmazonSqs(amazonSQSAsync);
//    return factory.createQueueMessageHandler();
//  }
}
