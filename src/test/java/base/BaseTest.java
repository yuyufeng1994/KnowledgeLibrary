package base;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA. Description:Dao 测试基类
 * 配置Spring和Junit整合,junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public abstract class BaseTest {
	public static void main(String[] args) {
		Process p = null;
       
        try{
               p = new ProcessBuilder("D:/soklib/red5-server/red5.bat").start(); //执行ipconfig/all命令
        }catch(IOException e){
              e.printStackTrace();
        }
	}
}
