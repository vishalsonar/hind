package com.sonar.vishal.logic;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import com.sonar.vishal.pojo.Config;
import com.sonar.vishal.util.Constant;

public class JvmArg {

	public Config read() {
		Config config = new Config();
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		String[] splitString = null;
		for (String argument : arguments) {
			if (argument.contains(Constant.URL_TOKEN)) {
				splitString = argument.split(Constant.URL_TOKEN);
				if (splitString.length >= 1) {
					config.setConnectionUrl(splitString[1]);
				}
			}
			if (argument.contains(Constant.CONFIG_PATH_TOKEN)) {
				splitString = argument.split(Constant.CONFIG_PATH_TOKEN);
				if (splitString.length >= 1) {
					config.setConfigFilePath(splitString[1]);
				}
			}
			if (argument.contains(Constant.SECRET)) {
				splitString = argument.split(Constant.SECRET);
				if (splitString.length >= 1) {
					config.setSecret(splitString[1]);
				}
			}
		}
		return config;
	}
}