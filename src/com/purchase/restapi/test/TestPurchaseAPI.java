package com.purchase.restapi.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class TestPurchaseAPI {

	public static void testPutSavePurchasesJson() {
		String string = "";
		try {
			InputStream jsonInputStream = new FileInputStream("./././././resources\\exemplo.json");
			InputStreamReader jsonReader = new InputStreamReader(jsonInputStream);
			BufferedReader br = new BufferedReader(jsonReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
			JSONObject jsonObject = new JSONObject(string);
			System.out.println("jsonObject sent to Rest API: " + jsonObject);
			try {
				URL url = new URL("http://localhost:8081/BlipPurchaseRESTAPI/purchase/restapi/save");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("PUT");
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
				String output;
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				System.out.print("Server output: [");
				while ((output = in.readLine()) != null) {
					System.out.print(output);
				}
				System.out.println("]");
				int responseCode = connection.getResponseCode();
				String responseMessage = connection.getResponseMessage();
				System.out.print("ResponseCode: " + responseCode);
				System.out.println(" ResponseMessage: " + responseMessage);
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
				e.printStackTrace();
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testGetConsultJson() {
		try {
			URL url = new URL("http://localhost:8081/BlipPurchaseRESTAPI/purchase/restapi/consult");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.print("Server output: ");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static void testGetGlobalMetrics() {
		try {
			URL url = new URL("http://localhost:8081/BlipPurchaseRESTAPI/purchase/restapi/metrics/global");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.print("Server output: ");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testGetDAOMetrics() {
		try {
			URL url = new URL("http://localhost:8081/BlipPurchaseRESTAPI/purchase/restapi/metrics/dao");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.print("Server output: ");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testGetControlMetrics() {
		try {
			URL url = new URL("http://localhost:8081/BlipPurchaseRESTAPI/purchase/restapi/metrics/control");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.print("Server output: ");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Test 1
		System.out.println("-----------------Begining test 1!");
		testPutSavePurchasesJson();
		System.out.println("-----------------Ended test 1!\n\n");

		// Test 2
		System.out.println("-----------------Begining test 2!");
		testGetConsultJson();
		System.out.println("-----------------Ended test 2!\n\n");

		// Test 3
		System.out.println("-----------------Begining test 3!");
		testGetGlobalMetrics();
		System.out.println("-----------------Ended test 3!\n\n");

		// Test 4
		System.out.println("-----------------Begining test 4!");
		testGetDAOMetrics();
		System.out.println("-----------------Ended test 4!\n\n");

		// Test 5
		System.out.println("-----------------Begining test 5!");
		testGetControlMetrics();
		System.out.println("-----------------Ended test 5!\n\n");
	}

}
