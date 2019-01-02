package org.demo.help;

public class ProjectPathGet {
	public String PathGet() {
		
		String s = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
		String[] b = s.split("/");
		String c="";
		for(int i=1;i<(b.length-2);i++) {
			c=c+b[i]+"/";
		}
		return c;
	}
}
