package Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public class freemarker {
	public static void main(String[] args) throws Exception {
		 //1.创建配置类
		Configuration configuration=new Configuration(Configuration.getVersion());
		//2.设置模板所在的目录 
		configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\10593\\Documents\\品优购\\Freemarker-demo\\src\\main\\resources"));
		//3.设置字符集
		configuration.setDefaultEncoding("utf-8");
		//4.加载模板
		Template template = configuration.getTemplate("test.ftl");
		//5.创建数据模型
		Map map=new HashMap();
		map.put("name", "张三 ");
		map.put("message", "欢迎来到神奇的品优购世界！");
		//6.创建Writer对象
		Writer out =new FileWriter(new File("e:\\test.html"));
		//7.输出
		template.process(map, out);
		//8.关闭Writer对象
		out.close();
	}	       
}
