package cn.itcast.demo;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@JmsListener(destination="itcast_map")
	public void readMap(Map map){
		System.out.println(map);		
	}
}