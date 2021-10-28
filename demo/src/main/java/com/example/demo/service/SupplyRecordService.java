package com.example.demo.service;

import com.example.demo.model.OrderListResponse;
import com.example.demo.model.OrderRecord;
import com.example.demo.model.OrderResponse;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplyRecordService {
    public static List<OrderRecord> records = new ArrayList<OrderRecord>();

    public void setOrderRecords() {
        records.add(new OrderRecord("order1", "product1", LocalDate.of(2021, 03, 16), 10.0));
        records.add(new OrderRecord("order2", "product2", LocalDate.of(2021, 03, 19), 5.0));
        records.add(new OrderRecord("order3", "product1", LocalDate.of(2021, 03, 16), 30.0));
        records.add(new OrderRecord("order4", "product4", LocalDate.of(2021, 03, 20), 20.0));
        records.add(new OrderRecord("order5", "product5", LocalDate.of(2021, 03, 16), 20.0));
    }

    public OrderResponse getMiniumQuantityProduct() {
        double max = 0;
        String finalProductId = "";
        double sum=0;
        for (int i = 0; i < records.size(); i++) {
          final String tempProductId = records.get(i).getProductId();
          max=  records.stream().filter(j->j.getProductId().equals(tempProductId)).mapToDouble(j->j.getQuantity()).sum();
            if(max>sum){
                sum=max;
                finalProductId = tempProductId;
            }
        }
        final String productId = finalProductId;
      List<OrderListResponse> orderResponseList=  records.stream().filter(i->i.getProductId().equals(productId)).map(i-> new OrderListResponse(i.getOrderNo(),i.getDate(),i.getQuantity())).collect(Collectors.toList());
        OrderResponse orderResponse=new OrderResponse(finalProductId,orderResponseList);
        return orderResponse;
    }

}
