package com.baz.interfaces;

import java.util.ArrayList;

/**
 * Interface que declara los m�todos a utilizar
 *  de quienes lo implementen
 *  
 * @version 0.4, 13/06/2022
 * @author CRISTHOFER L�PEZ HERN�NDEZ
 */
public interface InterfaceApp {
	
	public void getPathXML();
	public void readFileXML(String fullPath, String path, String name);
	public void updgradeDate(ArrayList<String> data, String path, String name);
	public void createNewXMLPROD(ArrayList<String> data, String path, String name);

}