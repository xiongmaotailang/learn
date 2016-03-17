package org.aeoluswqq.learn.apache.exec;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class RunCommand {
	public static void main(String a[]) throws Exception {
		runtime();
		//exec();
	}

	private static void runtime() throws Exception {
		String pl_cmd = "cmd.exe /c dir";
		Process p_pl = Runtime.getRuntime().exec(pl_cmd);
		BufferedReader br_pl = new BufferedReader(new InputStreamReader(
				p_pl.getInputStream()));
		String stdout = br_pl.readLine();
		while (stdout != null) {
			System.out.println(stdout);
			stdout = br_pl.readLine();
		}
	}
	private static void exec() throws Exception {
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		PumpStreamHandler psh = new PumpStreamHandler(stdout);
		CommandLine cl = CommandLine.parse("cmd.exe /c dir");
		DefaultExecutor exec = new DefaultExecutor();
		exec.setStreamHandler(psh);
		exec.execute(cl);
		System.out.println(stdout.toString());
	}
}
