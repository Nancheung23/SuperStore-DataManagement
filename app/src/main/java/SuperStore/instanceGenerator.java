package SuperStore;

import java.util.ArrayList;
import java.util.List;

public class instanceGenerator {
    private List<String[]> infoList;
    private List<Customer> customerList;

    public instanceGenerator(List<String[]> infoList, List<Customer> customerList) {
        // get from FileDataProcessor
        this.infoList = infoList;
        this.customerList = new ArrayList<>();
    }

    public void initialization() {
        infoList.forEach(arr -> {
            addCustomer(arr);
        });
    }
    
    public void addCustomer(String[] line) {
        String tempID = line[5];
        String tempName = line[6];
        String tempSegment = line[7];
        for(Customer customer : customerList) {
            if (customer.getCustomerId().equals(tempID) && customer.getCustomerName().equals(tempName) && customer.getSegment().equals(tempSegment)) {
                // if has customer, add new order
                return;
            }
        }
        // if no such customer, add new customer
        customerList.add(new Customer(tempID, tempName, tempSegment, null));
    }

    public Order addOrder(String[] line) {
        return new Order();
    }
}