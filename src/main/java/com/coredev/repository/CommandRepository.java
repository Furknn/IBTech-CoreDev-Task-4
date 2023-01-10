package com.coredev.repository;
import com.coredev.entity.Command;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CommandRepository extends BaseRepository<Command>{
	public CommandRepository() {
		super(Command.class);
	}

	// find a command by its name, eager fetch its parameters
	private static final String SELECT="select e from %s e where e.commandName=:commandName";
	public Command getCommand(String commandName) {
		EntityManager entityManager=newManager();
		TypedQuery<Command> typedQuery=entityManager.createQuery(String.format(SELECT, clazz.getSimpleName()), clazz);
		typedQuery.setParameter("commandName", commandName);
		Command command=typedQuery.getSingleResult();
		entityManager.close();
		return command;
	}


}
