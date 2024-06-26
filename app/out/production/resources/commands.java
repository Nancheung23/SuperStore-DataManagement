import java.io.IOException;

import SuperStore.Customer;
import SuperStore.CustomerMapUtils;
import SuperStore.FileDataProcessor;
import SuperStore.InstanceGenerator;
import SuperStore.Order;
import SuperStore.Product;

public class commands {
        public static void main(String[] args) throws IOException {
                FileDataProcessor fdp = new FileDataProcessor(
                                "C:\\Users\\zhang\\IdeaProjects\\2024-final-Nancheung23\\dataset\\SuperStoreOrders.csv");
                // for (String title : fdp.getTitles()) {
                // System.out.println(title);
                // }
                FileDataProcessor rfdp = new FileDataProcessor(
                                "C:\\Users\\zhang\\IdeaProjects\\2024-final-Nancheung23\\dataset\\SuperStoreReturns.csv");
                InstanceGenerator ig = new InstanceGenerator(fdp.processFile());
                ig.initialization();
                Customer example = ig.getCustomerMap().get("MA-17560");
                // test -- orders
                System.out.println(example.getCustomerId());
                System.out.println(example.getCustomerName());
                System.out.println(example.getOrders().get("CA-2015-143105").getShipDate());
                // test -- returns
                System.out.println(rfdp.processFile().get(0)[1]);
                ig.setReturnMap(rfdp.processFile());
                // Customer count
                System.out.println(CustomerMapUtils.calculateCustomersNumber(ig.getCustomerMap()));
                // Order count
                System.out.println(CustomerMapUtils.calculateOrdersNumber(ig.getCustomerMap()));
                // Product count
                System.out.println(CustomerMapUtils.calculateProductsNumber(ig.getCustomerMap()));
                // Customer get
                System.out.println(CustomerMapUtils.getCustomerById(ig.getCustomerMap(), "MA-17560"));
                // Order get
                Order resultOrder = CustomerMapUtils.getOrderById(ig.getCustomerMap(), "CA-2017-153822");
                System.out.println(resultOrder.toString());
                // Product get
                Product resultProduct = CustomerMapUtils.getProductById(ig.getCustomerMap(), "OFF-AP-10001492");
                System.out.println(resultProduct);
                // Total sales
                System.out.println(CustomerMapUtils.getTotalSalesForOrder(ig.getCustomerMap(), "CA-2017-153822"));
                // Average sales
                System.out.println(CustomerMapUtils.getAverageSalesForOrder(ig.getCustomerMap(), "CA-2017-153822"));
                System.out.println(CustomerMapUtils.getAverageSalesForAllOrders(ig.getCustomerMap()));
                // Best Customer
                System.out.println(CustomerMapUtils.getBestCustomer(ig.getCustomerMap()));
                // getAmountCustomerPerFilter : State
                CustomerMapUtils.getAmountCustomerPerFilter(ig.getCustomerMap(), "State")
                                .forEach((k, v) -> System.out.println(k + ":" + v));
                // getAmountOfSegment
                CustomerMapUtils.getAmountOfSegment(ig.getCustomerMap())
                                .forEach((k, v) -> System.out.println((k + ":" + v)));
                // getTotalSalesPerFilter : Region
                CustomerMapUtils.getTotalSalesPerFilter(ig.getCustomerMap(), "Region")
                                .forEach((k, v) -> System.out.println((k + ":" + v)));
                // getTotalSalesPerFilter : Year
                CustomerMapUtils.getTotalSalesPerFilter(ig.getCustomerMap(), "Year")
                                .forEach((k, v) -> System.out.println((k + ":" + v)));
                // getTotalSalesPerFilter : Month
                CustomerMapUtils.getTotalSalesPerFilter(ig.getCustomerMap(), "Month")
                                .forEach((k, v) -> System.out.println((k + ":" + v)));
                // getTotalSalesPerAttribute : any
                CustomerMapUtils.getTotalSalesPerAttribute(ig.getCustomerMap(), o -> o.getShipDate().substring(0, 7))
                                .forEach((k, v) -> System.out.println((k + ":" + v)));

        }
}
