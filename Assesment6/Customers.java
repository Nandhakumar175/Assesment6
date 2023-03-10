package com.Ebrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Customers {
    private String name;
    private String email;
    private String city;
    private String state;
    private String country;

    public Customers(String name, String email, String city, String state, String country) {  
        this.name = name;
        this.email = email;
        this.city = city;
        this.state = state;
        this.country = country;
        }
     public static void main(String[] args) {
    	 try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter the number of customers: ");
			 int numCustomers = input.nextInt();
			 

			 ArrayList<Customers> customers = new ArrayList<Customers>();
			 

			 for (int i = 0; i < numCustomers; i++) {
				  System.out.printf("Enter details for customer %d:%n", i+1);
			      System.out.print("Name: ");
			      String name = input.next();
			      System.out.print("Email: ");
			      String email = input.next();
			      System.out.print("City: ");
			      String city = input.next();
			      System.out.print("State: ");
			      String state = input.next();
			      System.out.print("Country: ");
			      String country = input.next();
			      Customers customer = new Customers(name, email, city, state, country);
			      customers.add(customer);
			      }
			 System.out.println("--------------------------------------Display Customer Details:------------------------------------------");
			 for(Customers customer : customers) {
			 System.out.println("Name:"+customer.name +"    "+"Email:"+ customer.email +"    "+"City:"+ customer.city +"    "+"State:"+ customer.state +"    "+"Country:"+ customer.country);
			 }
			 
			 
			 

			 Map<String, Map<String, Map<String, List<Customers>>>> groupedCustomers = new HashMap<>();
			  for (Customers customer : customers) {
				  String country = customer.country;
			      String state = customer.state;
			      String city = customer.city;
			      

			      groupedCustomers.computeIfAbsent(country, k -> new HashMap<>()).computeIfAbsent(state, k -> new HashMap<>()).computeIfAbsent(city,k-> new ArrayList<>()).add(customer);
			      }
			  

			  System.out.println("--------------------------------------Country-wise Total Customers:-------------------------------------");
			  
			  

			  for (Map.Entry<String, Map<String, Map<String, List<Customers>>>> countryEntry : groupedCustomers.entrySet()) { 
				  
			      String country = countryEntry.getKey();
			      int totalCustomers = 0;
			      for (Map.Entry<String, Map<String, List<Customers>>> stateEntry : countryEntry.getValue().entrySet()) {
			    	  for (List<Customers> cityList : stateEntry.getValue().values()) {
			    		  totalCustomers += cityList.size();
			    		  }
			    	  }
			      System.out.println(country + ": " + totalCustomers + " customers");
			      }
			  

			// Print country-wise and state-wise customers
			  System.out.println("--------------------------------------Country and State-wise Customers:--------------------------------");
			  for (Map.Entry<String, Map<String, Map<String, List<Customers>>>> countryEntry : groupedCustomers.entrySet()) {
				  String country = countryEntry.getKey();
				  for (Map.Entry<String, Map<String, List<Customers>>> stateEntry : countryEntry.getValue().entrySet()) {
			      String state = stateEntry.getKey();
			      int stateTotalCustomers = 0;
			      for (List<Customers> cityList : stateEntry.getValue().values()) {
			    	  stateTotalCustomers += cityList.size();
			    	  }
			      System.out.println(country + ", " + state + ":" +stateTotalCustomers + "customers");
			      }
				  }
			  System.out.println("--------------------------------Country, State and District-wise Customers:----------------------------");

			  Map<String, Map<String, Map<String, List<Customers>>>> countryStateDistrictMap = new HashMap<>();
			  for (Customers customer : customers) {
			      String country = customer.country;
			      String state = customer.state;
			      String district = customer.city;

			      if (!countryStateDistrictMap.containsKey(country)) {
			          countryStateDistrictMap.put(country, new HashMap<>());
			      }
			      if (!countryStateDistrictMap.get(country).containsKey(state)) {
			          countryStateDistrictMap.get(country).put(state, new HashMap<>());
			      }
			      if (!countryStateDistrictMap.get(country).get(state).containsKey(district)) {
			          countryStateDistrictMap.get(country).get(state).put(district, new ArrayList<>());
			      }
			      countryStateDistrictMap.get(country).get(state).get(district).add(customer);
			  }

			  for (Map.Entry<String, Map<String, Map<String, List<Customers>>>> countryEntry :
			          countryStateDistrictMap.entrySet()) {
			      String country = countryEntry.getKey();
			      int countryCustomerCount = 0;

			      System.out.println("Country: " + country);
			      for (Map.Entry<String, Map<String, List<Customers>>> stateEntry :
			              countryEntry.getValue().entrySet()) {
			          String state = stateEntry.getKey();
			          int stateCustomerCount = 0;

			          System.out.println("State: " + state);
			          for (Map.Entry<String, List<Customers>> districtEntry :
			                  stateEntry.getValue().entrySet()) {
			              String district = districtEntry.getKey();
			              List<Customers> customersInDistrict = districtEntry.getValue();
			              int districtCustomerCount = customersInDistrict.size();

			              System.out.println("District: " + district + " (" + districtCustomerCount + " customers)");
			              stateCustomerCount += districtCustomerCount;
			              countryCustomerCount += districtCustomerCount;
			          }
			          System.out.println("Total customers in " + state + ": " + stateCustomerCount);
			      }
			      System.out.println("Total customers in " + country + ": " + countryCustomerCount);
			      System.out.println();
			  }

		}
          }
     }


