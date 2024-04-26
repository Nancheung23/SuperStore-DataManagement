package SuperStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for generating instances based on provided information.
 * It processes lists of string arrays representing data records, generating customers,
 * orders, and products from this data, and tracking returns.
 */
public class InstanceGenerator {
    private List<String[]> infoList;
    private HashMap<String, Customer> customerMap;
    private HashMap<String, Boolean> returnMap;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    /**
     * Constructs an InstanceGenerator with predefined lists and maps.
     * 
     * @param infoList A list of string arrays containing data records.
     * @param customerMap A map of customer ID to Customer objects.
     * @param returnMap A map of order ID to return status.
     */
    public InstanceGenerator(List<String[]> infoList, HashMap<String, Customer> customerMap, HashMap<String, Boolean> returnMap) {
        // get from FileDataProcessor
        this.infoList = infoList;
        this.customerMap = customerMap;
        this.returnMap = returnMap;
    }

    /**
     * Constructs an InstanceGenerator with only a list of data records.
     * Initializes empty maps for customers and returns.
     * 
     * @param infoList A list of string arrays containing data records.
     */
    public InstanceGenerator(List<String[]> infoList) {
        // get from FileDataProcessor
        this.infoList = infoList;
        this.customerMap = new HashMap<>();
        this.returnMap = new HashMap<>();
    }

    /**
     * Returns the list of information records.
     * 
     * @return The list of string arrays representing data records.
     */    
    public List<String[]> getInfoList() {
        return infoList;
    }

    /**
     * Returns the map of customers.
     * 
     * @return A map of customer ID to Customer objects.
     */
    public HashMap<String, Customer> getCustomerMap() {
        return customerMap;
    }

    /**
     * Returns the map of return statuses.
     * 
     * @return A map of order ID to return status (Boolean).
     */    
    public HashMap<String, Boolean> getReturnMap() {
        return returnMap;
    }

    /**
     * Initializes the instance by processing each record in the information list.
     * Adds customers and their orders to the customer map.
     */    
    public void initialization() {
        infoList.forEach(this::addCustomer);
    }

    /**
     * Processes a single data record, adding or updating a customer and their order.
     * 
     * @param line A string array representing a single data record.
     * @return A string indicating the result of the operation.
     */
    public String addCustomer(String[] line) {
        String tempId = line[5];
        String tempName = line[6];
        String tempSegment = line[7];
        Customer customer = customerMap.getOrDefault(tempId, new Customer());
        // if no such customer, add new customer
        if (customerMap.containsKey(tempId)) {
            customer = customerMap.get(tempId);
        } else {
            customer.setCustomerId(tempId);
            customer.setCustomerName(tempName);
            customer.setSegment(tempSegment);
            customerMap.putIfAbsent(tempId, customer);
        }
        String orderInfo = addOrder(customer, line);
        int lengthOfMap = customerMap.size();
        if (customerMap.size() - lengthOfMap == 1) {
            return "New customer:" + tempId + ":" + orderInfo;
        } else {
            return "Customer already exist:" + customer.getCustomerId() + ":" + orderInfo;
        }
    }

    /**
     * Adds or updates an order for a given customer based on the provided data record.
     * 
     * @param customer The customer object to add the order to.
     * @param line A string array representing a single data record.
     * @return A string indicating the result of the operation.
     */
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
            setOrderReturn();
        }
        String productInfo = addProduct(order, line);
        int lengthOfMap = customer.getOrders().size();
        if (customer.getOrders().size() - lengthOfMap == 1) {
            return "New order:" + tempOrderId + ":" + productInfo;
        } else {
            return "Order already exist:" + order.getOrderId() + ":" +productInfo;
        }
    }

    /**
     * Adds or updates a product within an order based on the provided data record.
     * 
     * @param order The order object to add the product to.
     * @param line A string array representing a single data record.
     * @return A string indicating the result of the operation.
     */    
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
            order.getProducts().put(tempProductId, product);
        }
        int lengthOfMap = order.getProducts().size();
        if (order.getProducts().size() - lengthOfMap == 1) {
            return "New product:" + tempProductId;
        } else {
            return "Product already exist:" + product.getProductId();
        }
    }

    /**
     * Sets the return map based on a list of return records.
     * 
     * @param returnList A list of string arrays representing return records.
     */
    public void setReturnMap(List<String[]> returnList) {
        returnList.forEach(l -> {
            if (l[0].equals("Yes")) {
                this.returnMap.put(l[1], true);
            }
        });
    }

    /**
     * Updates the return status of orders based on the return map.
     */    
    public void setOrderReturn() {
        if (returnMap != null && customerMap != null) {
            for (String id : returnMap.keySet()) {
                customerMap.values().forEach(c -> {
                    if (c.getOrders().containsKey(id)) {
                        c.getOrders().get(id).setIsReturn(returnMap.get(id));
                    }
                });
            }
        }
    }
}