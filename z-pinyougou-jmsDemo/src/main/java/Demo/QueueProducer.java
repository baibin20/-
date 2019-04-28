package Demo;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class QueueProducer {
	
	@Test//消息生产者
	public void demo1() throws JMSException {		
	    //1.创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//2.获取连接
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);		
		//5.创建队列对象
		Queue queue = session.createQueue("test-queue");
		//6.创建消息生产者
		MessageProducer producer = session.createProducer(queue);
		//7.创建消息
		TextMessage textMessage = session.createTextMessage("欢迎来到神奇的品优购世界");
		//8.发送消息
		producer.send(textMessage);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	
	/*上述代码中第4步创建session  的两个参数：
	第1个参数 是否使用事务
	第2个参数 消息的确认模式
  AUTO_ACKNOWLEDGE = 1    自动确认
  CLIENT_ACKNOWLEDGE = 2    客户端手动确认   
  DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
  SESSION_TRANSACTED = 0    事务提交并确认*/
	
	@Test //消息消费者
	public void demo2() throws JMSException {
		//1.创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//2.获取连接
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建队列对象
		Queue queue = session.createQueue("test-queue");
		//6.创建消息消费
		MessageConsumer consumer = session.createConsumer(queue);		
		//7.监听消息
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("接收到消息："+textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});	
		//8.等待键盘输入
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();	
		
	}
	
	
	@Test //发布/订阅模式      1.1 消息生产者
	public void demo3() throws Exception {
		
		//1.创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//2.获取连接
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建主题对象
		Topic topic = session.createTopic("test-topic");
		//6.创建消息生产者
		MessageProducer producer = session.createProducer(topic);
		//7.创建消息
		TextMessage textMessage = session.createTextMessage("欢迎来到神奇的品优购世界");
		//8.发送消息
		producer.send(textMessage);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	
	
	@Test ////发布/订阅模式      1.1 消息消费者
	public void demo4() throws Exception{
		
		//1.创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//2.获取连接
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建主题对象
		//Queue queue = session.createQueue("test-queue");
		Topic topic = session.createTopic("test-topic");
		//6.创建消息消费
		MessageConsumer consumer = session.createConsumer(topic);
		
		//7.监听消息
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("接收到消息："+textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//8.等待键盘输入
		System.in.read();
		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();	
	}	
	
}
