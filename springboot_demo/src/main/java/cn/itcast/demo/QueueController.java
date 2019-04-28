package cn.itcast.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息生产者
 * @author Administrator
 */
@RestController
public class QueueController {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@RequestMapping("/sendsms")
	public void sendSms(){
		Map map=new HashMap<>();
		map.put("mobile", "15221320441");
		map.put("template_code", "SMS_163530384");	
		map.put("sign_name", "养痘小达人");
		map.put("param", "{\"code\":\"520520\"}");
		jmsMessagingTemplate.convertAndSend("sms",map);
	}
}