package com.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Code {
	private HashMap<Integer, String> codes = new HashMap<Integer, String>();
	private static Code sys_code;

	public static void newInstance(String filepath) {
		if (sys_code == null) {
			sys_code = new Code(filepath);
		}
	}

	public static Code getInstance() {
		return sys_code;
	}

	private Code(String filepath) {
		File f = new File(filepath + "\\META-INF\\code");
		if (f.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				String[] data = null;
				String[] d = null;
				StringBuffer sb = new StringBuffer();
				byte[] b = new byte[4096];
				for (int n; (n = fis.read(b)) != -1;) {
					sb.append(new String(b, 0, n, "UTF-8"));
				}
				data = sb.toString().split("\r\n");
				for (int i = 0; i < data.length; i++) {
					d = data[i].split(":");
					if (d.length > 1) {
						codes.put(Integer.parseInt(d[0]), d[1]);
					}
				}
				System.out.println("================>>>加载系统代码表");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("没有代码表");
		}
	}

	public String getName(int code) {
		return this.codes.get(code);
	}
}
