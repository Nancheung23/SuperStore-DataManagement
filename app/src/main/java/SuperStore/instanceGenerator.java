package SuperStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

public class InstanceGenerator {
    private List<String[]> infoList;
    private HashMap<String, Customer> customerMap;
    private HashMap<String, Boolean> returnMap;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    public InstanceGenerator(List<String[]> infoList, HashMap<String, Customer> customerMap, HashMap<String, Boolean> returnMap) {
        // get from FileDataProcessor
        this.infoList = infoList;
        this.customerMap = customerMap;
        this.returnMap = returnMap;
    }

    public InstanceGenerator(List<String[]> infoList) {
        // get from FileDataProcessor
        this.infoList = infoList;
        this.customerMap = new HashMap<>();
        this.returnMap = new HashMap<>();
    }
    
    public List<String[]> getInfoList() {
        return infoList;
    }

    public HashMap<String, Customer> getCustomerMap() {
        return customerMap;
    }

    public HashMap<String, Boolean> getReturnMap() {
        return returnMap;
    }

    public void initialization() {
        infoList.forEach(arr -> {
            addCustomer(arr);
        });
    }

    public String addCustomer(String[] line) {
        String tempId = line[5];
        String tempName = line[6];
        String tempSegment = line[7];
        Customer customer = new Customer();
        // if no such customer, add new customer
        if (customerMap.containsKey(tempId)) {
            customer = customerMap.get(tempId);
        } else {
            customer.setCustomerId(tempId);
            customer.setCustomerName(tempName);
            customer.setSegment(tempSegment);
            customerMap.put(tempId, customer);
        }
        String orderInfo = addOrder(customer, line);
        int lengthOfMap = customerMap.size();
        if (customerMap.size() - lengthOfMap == 1) {
            return "New customer:" + tempId + ":" + orderInfo;
        } else {
            return "Customer already exist:" + customer.getCustomerId() + ":" + orderInfo;
        }
    }

    public String addOrder(Customer customer, String[] line) {
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
            try {
                order.setOrderDate(LocalDate.parse(tempOrderDate, formatter));
                order.setShipDate(LocalDate.parse(tempShipDate, formatter));
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + e.getMessage());
            }
            order.setShipMode(tempShipMode);
            order.setAddress(tempAddress);
            customer.getOrders().put(tempOrderId, order);
        }
        String productInfo = addProduct(order, line);
        int lengthOfMap = customer.getOrders().size();
        if (customer.getOrders().size() - lengthOfMap == 1) {
            return "New order:" + tempOrderId + ":" + productInfo;
        } else {
            return "Order already exist:" + order.getOrderId() + ":" +productInfo;
        }
    }

    public String addProduct(Order order, String[] line) {
        String tempProductId = line[13];
        String tempCategory = line[14];
        String tempSubCategory = line[15];
        String tempProductName = line[16];
        Double tempSales = Double.parseDouble(line[17].replace(",", "."));
        int tempQuantity = Integer.parseInt(line[18].replace(",", ""));
        Double tempDiscount = Double.parseDouble(line[19].replace(",", "."));
        int tempProfit = Integer.parseInt(line[20].replace(",", ""));
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
        int lengthOfMap = order.getProducts().size();
        if (order.getProducts().size() - lengthOfMap == 1) {
            return "New product:" + tempProductId;
        } else {
            return "Product already exist:" + product.getProductId();
        }
    }

    public void setReturnMap(List<String[]> returnList) {
        returnList.forEach(l -> {
            if (l[0].equals("Yes")) {
                this.returnMap.put(l[1], true);
            }
        });
    }

    public void setOrderReturn() {
        if (returnMap != null && customerMap != null) {
            for (String id : returnMap.keySet()) {
                customerMap.values().forEach(c -> {
                    if (c.getOrders().containsKey(id)) {
                        c.getOrders().get(id).setReturn(returnMap.get(id));
                    }
                });
            }
        }
    }
}