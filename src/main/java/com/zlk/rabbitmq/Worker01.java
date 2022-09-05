package com.zlk.rabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Worker01 {
    private static final String QUEUE_NAME= "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("C1等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag,delivery) -> {
            String message = new String(delivery.getBody(),"utf-8");
            System.out.println("接收到消息"+message);
            //1.消息标记tag 2.false代表只应答接收到的那个传递的消息 true为应答所有消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        boolean autoAck  = false;
        channel.basicConsume(QUEUE_NAME,autoAck,deliverCallback,consumerTag->{});
    }
}
