package com.coredev.utils;

import java.lang.reflect.Method;

import com.coredev.entity.Command;
import com.coredev.repository.CommandRepository;
import com.coredev.types.CDBag;

public class CommandExecuter {
	private static CommandExecuter instance = null;

	public static CommandExecuter getInstance() {
		if(instance == null) {
			instance = new CommandExecuter();
		}
		return instance;
	}

	public CDBag execute(CDBag inBag) throws Exception {
		CDBag outBag = new CDBag();

		Command command = new CommandRepository().getCommand(inBag.get("command").toString());
		Class<?> clazz = Class.forName(command.getClassName());
		Object obj = clazz.getDeclaredConstructor().newInstance();
		Method method;

		if(!inBag.isEmpty()) {
			method = clazz.getDeclaredMethod(command.getMethodName(), CDBag.class);
			outBag = (CDBag) method.invoke(obj, inBag);
		} else {
			method = clazz.getDeclaredMethod(command.getMethodName());
			outBag = (CDBag) method.invoke(obj);
		}

		return outBag;
	}

	public CDBag getParams(CDBag inBag) throws Exception {
		CDBag outBag = new CDBag();

		CommandRepository repository = new CommandRepository();
		Command command = repository.getCommand(inBag.get("command").toString());
		outBag.put("command", command.getCommandName());
		outBag.put("className", command.getClassName());
		outBag.put("methodName", command.getMethodName());
		outBag.put("commandParameters", command.getCommandParameters());

		return outBag;
	}
}
