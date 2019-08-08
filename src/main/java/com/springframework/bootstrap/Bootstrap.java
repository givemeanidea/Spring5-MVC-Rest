package com.springframework.bootstrap;

import com.springframework.domain.Category;
import com.springframework.domain.Customer;
import com.springframework.repositories.CategoryRepository;
import com.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();

        System.out.println("Data Loaded = " + categoryRepository.count());
        System.out.println("Customers Loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Daniel");
        customer1.setLastName("Byrne");

        Customer customer2 = new Customer();
        customer2.setFirstName("Billy");
        customer2.setLastName("Joe");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }
}
