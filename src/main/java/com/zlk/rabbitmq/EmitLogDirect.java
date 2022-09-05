package com.zlk.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception{
        try(Channel channel = RabbitMqUtils.getChannel()){
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            //创建多个bindingkey
            Map<String,String> bindingKeyMap = new HashMap<>();
            bindingKeyMap.put("info","普通info信息");
            bindingKeyMap.put("error","错误error信息");
            bindingKeyMap.put("warning","警告warning信息");
            bindingKeyMap.put("debug","调试debug信息");
            for(Map.Entry<String,String> bindingKeyEntry:bindingKeyMap.entrySet()){
                String bindingKey = bindingKeyEntry.getKey();
                String message = bindingKeyEntry.getValue();
                channel.basicPublish(EXCHANGE_NAME,bindingKey,null,message.getBytes("UTF-8"));
                System.out.println("生产者发出消息:"+message);
            }

        }
    }
}
