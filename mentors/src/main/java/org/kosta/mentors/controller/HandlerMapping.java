package org.kosta.mentors.controller;

public class HandlerMapping {
	private static HandlerMapping instance= new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return instance;
	}
	public Controller create(String controllerName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		StringBuilder classInfo = new StringBuilder(this.getClass().getPackage().getName());
		classInfo.append(".").append(controllerName);
		return (Controller) Class.forName(classInfo.toString()).newInstance();
	}
}
