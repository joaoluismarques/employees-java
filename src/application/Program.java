package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.next();
		System.out.print("Enter salary: ");
		double baseSalary = sc.nextDouble();
		System.out.print("Letra inicial: ");
		char l = sc.next().charAt(0);
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Employee> emp = new ArrayList<>();
			String line = br.readLine();
			
			while(line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);
				emp.add(new Employee(name, salary, email));
				line = br.readLine();
			}
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> higherSalaries = emp.stream()
					.filter(x -> x.getSalary() > baseSalary)
					.map(x -> x.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());			
			
			double sumName = emp.stream()
					.filter(x -> x.getName().charAt(0) == l)
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			System.out.println("Email of people whose salary is more than 2000.00:");
			higherSalaries.forEach(System.out::println);
					
			System.out.printf("Sum of salary of people whose name starts with: %.2f%n", sumName);		
					
					
			
	
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		sc.close();

	}

}
