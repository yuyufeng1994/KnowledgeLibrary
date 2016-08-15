package com.lib.web.admin.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.enums.Const;

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
	 * 日志页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public String log(Model model) {
		List<String> days7 = new ArrayList<String>();
		String datet = org.apache.tools.ant.util.DateUtils.format(new Date(), "yyyy-MM-dd");
		Date date = new Date();
		Date res;
		Calendar cNow = Calendar.getInstance();
		cNow.setTime(date);
		res = cNow.getTime();
		cNow.set(Calendar.HOUR_OF_DAY, 0);
		cNow.set(Calendar.SECOND, 0);
		cNow.set(Calendar.MINUTE, 0);
		cNow.set(Calendar.MILLISECOND, 0);
		cNow.add(Calendar.DATE, +2);
		for (int i = 7; i > 0; i--) {
			cNow.add(Calendar.DATE, -1);
			res = cNow.getTime();
			datet = DateUtils.format(res, "yyyy-MM-dd");
			days7.add(datet);
		}
		model.addAttribute("days7", days7);
		return "admin/log";
	}

	/**
	 * 得到常用文件流
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/log/{day}", method = RequestMethod.GET)
	public String thumbnail(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@PathVariable("day") String day) {
		String path = Const.ROOT_PATH + "logs/" + day + ".log";
		try {
			InputStream inputStream = new FileInputStream(path);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return null;
	}

	@RequestMapping(value = "/log/now", method = RequestMethod.GET)
	public @ResponseBody JsonResult<List<String>> getLogNow() {
		String date = org.apache.tools.ant.util.DateUtils.format(new Date(), "yyyy-MM-dd");
		String path = Const.ROOT_PATH + "logs/" + date + ".log";
		List<String> list = new ArrayList<String>();
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(path, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line;
			rf.seek(nextend);
			int c = -1;
			int getLen = 35;
			int getIndex = 0;

			while (nextend > start) {

				c = rf.read();
				if (c == '\n' || c == '\r') {
					getIndex++;
					if (getLen < getIndex) {
						break;
					}
					line = rf.readLine();
					if (line != null) {
						list.add(new String(line.getBytes("iso-8859-1")));
					} else {
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					// System.out.println(rf.readLine());
					list.add(new String(rf.readLine().getBytes("iso-8859-1")));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rf != null)
					rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Collections.reverse(list);
		JsonResult<List<String>> res = new JsonResult<List<String>>(true, list);
		return res;
	}

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
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/red5-start", method = RequestMethod.GET)
	public String red5Start() throws IOException {
		System.out.println("D:\\\\soklib\\red5-server\\red5.bat");
		Runtime run = Runtime.getRuntime();
		run.exec("D:\\\\soklib\\red5-server\\red5.bat");
		// String command = Const.ROOT_PATH + "red5-server/red5.bat";
		// commnd(command);
		//
		// while (true) {
		// System.out.println("RED5STATE:" + RED5STATE);
		// if (RED5STATE == true) {
		// break;
		// }
		// try {
		// Thread.currentThread().sleep(3000);
		// } catch (InterruptedException e) {
		// }
		//
		// }

		return "redirect:red5";
	}

	/**
	 * 开启流媒体服务
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/red5-stop", method = RequestMethod.GET)
	public String red5Stop() throws IOException {
		Runtime run = Runtime.getRuntime();
		System.out.println("D:\\\\soklib\\red5-server\\red5-shutdown.bat");
		run.exec("D:\\\\soklib\\red5-server\\red5-shutdown.bat");
		// String command = Const.ROOT_PATH + "red5-server/red5-shutdown.bat";
		// commnd(command);
		// while (true) {
		// System.out.println("RED5STATE:" + RED5STATE);
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
		// System.out.println("D:\\\\soklib\\red5-server\\red5.bat");
		// run.exec("D:\\\\soklib\\red5-server\\red5.bat");
		Process p = Runtime.getRuntime().exec("cmd /d D://soklib/red5-server/red5.bat");
		p.exitValue();

	}
}
