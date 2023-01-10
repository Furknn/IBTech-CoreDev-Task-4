package com.coredev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "command_parameter")
public class CommandParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "command_id")
    private Command command;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_type")
    private String parameterType;

    @Column(name = "parameter_description")
    private String parameterDescription;

    public CommandParameter() {
    }

    public CommandParameter(Long id, Command command, String parameterName, String parameterType, String parameterDescription) {
        this.id = id;
        this.command = command;
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterDescription = parameterDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }
}
