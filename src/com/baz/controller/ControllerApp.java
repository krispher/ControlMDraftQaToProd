package com.baz.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.baz.interfaces.InterfaceApp;

/**
 * Clase para cargar información de archivos XML Drafts de Control M
 * y convertirlos a datos Productivos
 * 
 * @version 0.4, 13/06/2022
 * @author CRISTHOFER LÓPEZ HERNÁNDEZ
 */
public class ControllerApp implements InterfaceApp {

	/**
	 * Método que solicita al Usuario el archivo a Modificar
	 */
	@Override
	public void getPathXML() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		final JFileChooser fc = new JFileChooser();

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Draft XML", "xml", "XML"));
		fc.setAcceptAllFileFilterUsed(true);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			File file = fc.getSelectedFile();

			// System.out.println("Ruta del Draft .XML: -> " + file.getPath());

			readFileXML(file.getPath(), file.getParent(), file.getName().substring(0, file.getName().length() - 4));

		} else {
			JOptionPane.showMessageDialog(null, "Favor de seleccionar un Draf XML");
			System.exit(0);
		}

	}

	/**
	 * Método que lee el archivo y se guarda el contenido en un ArrayList<String>
	 */
	@Override
	public void readFileXML(String fullPath, String path, String name) {

		// System.out.println("Cargando informacion del Draft XML");

		try {

			final BufferedReader bffReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(fullPath), "utf-8"));

			String lineTxt;
			ArrayList<String> line = new ArrayList<>();

			while ((lineTxt = bffReader.readLine()) != null) {
				lineTxt.split("\\n", -1);
				line.add(lineTxt);
			}

			updgradeDate(line, path, name);

			bffReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que reemplaza los datos de QA a PROD
	 */
	@Override
	public void updgradeDate(ArrayList<String> data, String path, String name) {

		for (int i = 0; i < data.size(); i++) {

			if (data.get(i).contains("DATACENTER=\"KIOBANAZTQACTLM\"")) {// SERVIDOR AZTLAN
				data.set(i, data.get(i).replace("DATACENTER=\"KIOBANAZTQACTLM\"", "DATACENTER=\"Aztlan\""));
			}
			if (data.get(i).contains("kiobanaztqactlm")) {// HOSTGROUP LOCAL
				data.set(i, data.get(i).replace("kiobanaztqactlm", "finbanprosqlclu"));
			}
			if (data.get(i).contains("kiobazintnxapb2")) {// HOSTGROUP MP
				data.set(i, data.get(i).replace("kiobazintnxapb2", "finbanmxbatcvip"));
			}
			if (data.get(i).contains("ubsappqa01")) {// HOSTGROUP UBS
				data.set(i, data.get(i).replace("ubsappqa01", "ubsappl01"));
			}
			if (data.get(i).contains("pymtappqa01")) {// HOSTGROUP PAYMENTS
				data.set(i, data.get(i).replace("pymtappqa01", "phappl01"));
			}
			if (data.get(i).contains("VALUE=\"KIOBANAZTQACTLM\\usrctm\"")) {// USER SFPT LOCAL
				data.set(i, data.get(i).replace("VALUE=\"KIOBANAZTQACTLM\\usrctm\"", "VALUE=\"UsrCTBAZ\""));
			}
			if (data.get(i).contains("VALUE=\"10.90.12.25\"")) {// IP UBS
				data.set(i, data.get(i).replace("VALUE=\"10.90.12.25\"", "VALUE=\"10.3.17.20\""));
			}
			if (data.get(i).contains("10.90.12.28")) {// IP PAYMENTS
				data.set(i, data.get(i).replace("10.90.12.28", "10.3.12.27"));
			}
			if (data.get(i).contains("10.50.109.124")) {// IP FACTURACION
				data.set(i, data.get(i).replace("10.50.109.124", "10.242.0.76"));
			}
			// NOMBRE DE PERFILES DE CONEXIÓN
			if (data.get(i).contains("UBSCoreQA")) {
				data.set(i, data.get(i).replace("UBSCoreQA", "UBSCoreN1"));
			}
			if (data.get(i).contains("HubPagosQA")) {
				data.set(i, data.get(i).replace("HubPagosQA", "Envio"));
			}
			if (data.get(i).contains("RepoBigMS")) {
				data.set(i, data.get(i).replace("RepoBigMS", "RepoBigMS"));
			}
			if (data.get(i).contains("LocalServerCtm")) {
				data.set(i, data.get(i).replace("LocalServerCtm", "localctmaztlan"));
			}
			if (data.get(i).contains("MediosPagoQA")) {
				data.set(i, data.get(i).replace("MediosPagoQA", "MediosPagoPROD"));
			}

			// RUTAS MED PAGO
			if (data.get(i).contains("C:\\SFTP\\USR-MPF-BEA\\FINREPORTS\\")) {
				data.set(i,
						data.get(i).replace("C:\\SFTP\\USR-MPF-BEA\\FINREPORTS\\", "C:\\FINREPORTS\\USR-MPF-BEA\\"));
			}
			if (data.get(i).contains("C:\\SFTP\\USR-MPF-BEA\\FINREPORTS\\")) {
				data.set(i,
						data.get(i).replace("C:\\SFTP\\USR-MPF-BEA\\FINREPORTS\\", "C:\\FINREPORTS\\USR-MPF-BEA\\"));
			}
			if (data.get(i).contains("/REPORTERIA/")) {
				data.set(i, data.get(i).replace("/REPORTERIA/", "/D:/REPORTERIA/"));
			}

		}

		// for (String txt : data) { System.err.println(txt); }

		createNewXMLPROD(data, path, name);

	}

	/**
	 * Método que crea y escribe en el nuevo archivo con datos PROD
	 */
	@SuppressWarnings("resource")
	@Override
	public void createNewXMLPROD(ArrayList<String> data, String path, String name) {

		// System.out.println("Creando Draft productivo ...");

		try {

			new OutputStreamWriter(new FileOutputStream(path + "\\" + name + "_PROD.xml", true), "utf-8");
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(path + "\\" + name + "_PROD.xml", true), "utf-8"));
			
			for (String txt : data) {

				out.write(txt + "\n");

			}

			out.close();

			JOptionPane.showMessageDialog(null, "Draft Productivo XML Creado con Éxito ...");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}