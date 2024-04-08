package SuperStore;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class InstanceGenerator {
    private List<String[]> infoList;
    private HashMap<String, Customer> customerMap;

    public InstanceGenerator(List<String[]> infoList, HashMap<String, Customer> customerMap) {
        // get from FileDataProcessor
        this.infoList = infoList;
    }

    public void initialization() {
        infoList.forEach(arr -> {
            addCustomer(arr);
        });
    }

    public boolean addCustomer(String[] line) {
        String tempId = line[5];
        String tempName = line[6];
        String tempSegment = line[7];
        Customer customer = new Customer();
        // if no such customer, add new customer
        if (customerMap.containsKey(tempId)) {
            customer = customerMap.get(tempId);
        } else {
            customerMap.put(tempId, customer);
            customer.setCustomerId(tempId);
            customer.setCustomerName(tempName);
            customer.setSegment(tempSegment);
        }
        if (addOrder(customer, line)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addOrder(Customer customer, String[] line) {
        String tempOrderId = line[1];
        String tempOrderDate = line[2];
        String tempShipDate = line[3];
        String tempShipMode = line[4];
        String tempCountry = line[8];
        String tempCity = line[9];
        String tempState = line[10];
        String tempRegion = line[12];
        int tempPostalCode = Integer.parseInt(line[11]);
        Address tempAddress = new Address(tempCountry, tempPostalCode, tempRegion, tempState, tempCity);
        Order order = new Order();
        if (customer.getOrders().containsKey(tempOrderId)) {
            order = customer.getOrders().get(tempOrderId);
        } else {
            order.setOrderId(tempOrderId);
            order.setOrderDate(LocalDate.parse(tempOrderDate));
            order.setShipDate(LocalDate.parse(tempShipDate));
            order.setShipMode(tempShipMode);
            order.setAddress(tempAddress);
            customer.getOrders().put(tempOrderId, order);
        }
        if (addProduct(order, line)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addProduct(Order order, String[] line) {
        int lengthOfMap = order.getProducts().size();
        String tempProductId = line[13];
        String tempCategory = line[14];
        String tempSubCategory = line[15];
        String tempProductName = line[16];
        Double tempSales = Double.parseDouble(line[17]);
        int tempQuantity = Integer.parseInt(line[18]);
        Double tempDiscount = Double.parseDouble(line[19]);
        int tempProfit = Integer.parseInt(line[20]);
        CategoryInfo tempCategoryInfo = new CategoryInfo(tempCategory, tempSubCategory);
        Product product = new Product();
        if (order.getProducts().containsKey(tempProductId)) {
            product = order.getProducts().get(tempProductId);
        } else {
            product.setProductId(tempProductId);
            product.setCategory(tempCategoryInfo);
            product.setProductName(tempProductName);
            product.setSales(tempSales);
            product.setQuantity(tempQuantity);
            product.setDiscount(tempDiscount);
            product.setProfit(tempProfit);
        }
        if(order.getProducts().size() >= lengthOfMap) {
            return true;
        }
    }
}