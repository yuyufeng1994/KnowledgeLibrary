package com.lib.web.admin.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DesktopManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台登录
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/admin")
public class SystemController {
	private static Boolean RED5STATE = false;

	/**
	 * 流媒体服务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/red5", method = RequestMethod.GET)
	public String red5() {

		return "admin/red5";
	}

	/**
	 * 开启流媒体服务
	 * @throws IOException 
	 */
	@RequestMapping(value = "/red5-start", method = RequestMethod.GET)
	public String red5Start() throws IOException {
		System.out.println("D:\\\\soklib\\red5-server\\red5.bat");
		Runtime run = Runtime.getRuntime();
		run.exec("D:\\\\soklib\\red5-server\\red5.bat");
//		String command = Const.ROOT_PATH + "red5-server/red5.bat";
//		commnd(command);
//
//		while (true) {
//			System.out.println("RED5STATE:" + RED5STATE);
//			if (RED5STATE == true) {
//				break;
//			}
//			try {
//				Thread.currentThread().sleep(3000);
//			} catch (InterruptedException e) {
//			}
//
//		}

		return "redirect:red5";
	}

	/**
	 * 开启流媒体服务
	 * @throws IOException 
	 */
	@RequestMapping(value = "/red5-stop", method = RequestMethod.GET)
	public String red5Stop() throws IOException {
		Runtime run = Runtime.getRuntime();
		System.out.println("D:\\\\soklib\\red5-server\\red5-shutdown.bat");
		run.exec("D:\\\\soklib\\red5-server\\red5-shutdown.bat");
//		String command = Const.ROOT_PATH + "red5-server/red5-shutdown.bat";
//		commnd(command);
		// while (true) {
//		System.out.println("RED5STATE:" + RED5STATE);
		// if (RED5STATE = true) {
		// break;
		// }
		// try {
		// Thread.currentThread().sleep(3000);
		// } catch (InterruptedException e) {
		// }
		RED5STATE = false;
		// }
		return "redirect:red5";
	}

	private synchronized void commnd(String command) {
		new Thread() {
			public void run() {
				try {
					Process p = Runtime.getRuntime().exec(command);
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));// 注意中文编码问题
					String line;
					while ((line = br.readLine()) != null) {
						System.out.println(":::" + line);
						if (line.equals("oflaDemo appStart")) {
							RED5STATE = true;
						}
					}
					br.close();
				} catch (Exception e) {
				}
			};
		}.start();
	}
	public static void main(String[] args) throws IOException {
//		System.out.println("D:\\\\soklib\\red5-server\\red5.bat");
//		run.exec("D:\\\\soklib\\red5-server\\red5.bat");
		 Process p=Runtime.getRuntime().exec("cmd /d D://soklib/red5-server/red5.bat");
         p.exitValue();  
		
		
		
	}
}
