package com.sonar.vishal.logic;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URLDecoder;
import java.util.List;

import com.sonar.vishal.pojo.Config;
import com.sonar.vishal.util.Constant;

public class JvmArg {

	private Config config;
	private String[] splitString;

	public JvmArg() {
		config = new Config();
	}

	public Config read() {
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		for (String argument : arguments) {
			if (argument.contains(Constant.URL_TOKEN)) {
				loadURLToken(argument);
			}
			if (argument.contains(Constant.CONFIG_PATH_TOKEN)) {
				loadPathToken(argument);
			}
			if (argument.contains(Constant.SECRET)) {
				loadSecretToken(argument);
			}
		}
		return config;
	}

	private void loadSecretToken(String argument) {
		splitString = argument.split(Constant.SECRET);
		if (splitString.length >= 1) {
			config.setSecret(splitString[1]);
		}
	}

	private void loadPathToken(String argument) {
		splitString = argument.split(Constant.CONFIG_PATH_TOKEN);
		if (splitString.length >= 1) {
			try {
				config.setConfigFilePath(URLDecoder.decode(splitString[1], Constant.UTF));
			} catch (Exception e) {
				config.setConfigFilePath(splitString[1]);
			}
		}
	}

	private void loadURLToken(String argument) {
		splitString = argument.split(Constant.URL_TOKEN);
		if (splitString.length >= 1) {
			config.setConnectionUrl(splitString[1]);
		}
	}
}