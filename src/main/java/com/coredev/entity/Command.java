package com.coredev.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "command_name")
    private String commandName;

    @Column(name = "command_description")
    private String commandDescription;

    @Column(name = "class_name")
    private String className;

    @Column(name = "method_name")
    private String methodName;

	@OneToMany(mappedBy = "command",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<CommandParameter> commandParameters;

	public Command() {
	}

	public Command(Long id, String commandName, String commandDescription, String className, String methodName) {
		this.id = id;
		this.commandName = commandName;
		this.commandDescription = commandDescription;
		this.className = className;
		this.methodName = methodName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getCommandDescription() {
		return commandDescription;
	}

	public void setCommandDescription(String commandDescription) {
		this.commandDescription = commandDescription;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<CommandParameter> getCommandParameters() {
		return commandParameters;
	}

	public void setCommandParameters(List<CommandParameter> commandParameters) {
		this.commandParameters = commandParameters;
	}
}
