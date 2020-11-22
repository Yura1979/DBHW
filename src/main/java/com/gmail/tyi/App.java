package com.gmail.tyi;

import java.io.IOException;
import java.util.Scanner;

import com.gmail.tyi.dao.DbHelper;

public class App {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		DbHelper db = DbHelper.getInstance();
//    	addFlat(db);
//    	db.getFlats();

		while (true) {
			System.out.println("1: add flat");
			System.out.println("2: view flats");
			System.out.println("3: view flat with condition");
			System.out.println("4: exit");
			System.out.print("-> ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				addFlat(db);
				break;
			case 2:
				db.getFlats();
				break;
			case 3:
				getFlatsWithCondition(db);
				break;
			case 4:
				System.out.println("Exiting...");
				return;
			default:
				return;
			}
		}

	}
	
	public static void getFlatsWithCondition(DbHelper db) {
		
		String condition = "";
		System.out.println("1: show flats whith square equals to: ");
		System.out.println("2: show flats whith square greater then: ");
		System.out.println("3: show flats whith square less then: ");
		System.out.println("4: show flats whith rooms less then: ");
		System.out.println("5: show flats whith rooms greater then: ");
		System.out.print("-> ");
		int choice = sc.nextInt();
		System.out.println("Enter value: ");
		int value = sc.nextInt();
		
		switch (choice) {
		case 1:
			condition = " where squere = " + value;
			break;
		case 2:
			condition = " where squere >= " + value;
			break;
		case 3:
			condition = " where squere <= " + value;
			break;
		case 4:
			condition = " where rumsCount <= " + value;
			break;
		case 5:
			condition = " where rumsCount >= " + value;
			break;
		default:
			System.out.println("Wrong condition!!!");
			return;
		}
		System.out.println(condition);
		db.getFlatsWithCondition(condition);
		
		
	}

	public static void addFlat(DbHelper db) {

		System.out.println("Enter flat region: ");
		String region = sc.nextLine();
		System.out.println("Enter flat address: ");
		String address = sc.nextLine();
		System.out.println("Enter flat squere: ");
		int squere = sc.nextInt();
		System.out.println("Enter flat number of rooms: ");
		int rumsCount = sc.nextInt();
		System.out.println("Enter flat price: ");
		int price = sc.nextInt();
		db.addFlat(region, address, squere, rumsCount, price);

	}

}
