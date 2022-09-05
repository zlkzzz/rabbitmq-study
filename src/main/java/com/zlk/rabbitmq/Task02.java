package com.zlk.rabbitmq;

import com.rabbitmq.client.Channel;

import java.util.Scanner;

public class Task02 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        try(Channel channel = RabbitMqUtils.getChannel()){
          channel.queueDeclare(TASK_QUEUE_NAME,false,false,false,null);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入消息：");
        while(scanner.hasNext()){
            String message  = scanner.nextLine();
            channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes());
            System.out.println("生产者发出消息"+message);
        }
        };
    }
}
