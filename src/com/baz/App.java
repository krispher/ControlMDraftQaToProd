package com.baz;

import com.baz.controller.ControllerApp;

/**
 * Clase principal que invoca a la clase ControllerApp
 * 
 * @version 0.4, 13/06/2022
 * @author CRISTHOFER L�PEZ HERN�NDEZ
 */
public class App {
	
	public static void main(String[] args) {
		
		ControllerApp conapp = new ControllerApp();
		
		conapp.getPathXML();
		
		System.exit(0);
		
	}
}