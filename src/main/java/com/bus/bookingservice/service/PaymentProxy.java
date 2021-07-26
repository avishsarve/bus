 package com.bus.bookingservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bus.bookingservice.model.FundTransfer;
import com.bus.bookingservice.model.ReversePayment;
import com.bus.bookingservice.model.TransferRequest;


@FeignClient(value = "payment-service")
 public interface PaymentProxy {

     @RequestMapping(method = RequestMethod.POST, value = "/customer/transfer")
      FundTransfer doPayment(@RequestBody TransferRequest payment);

     @RequestMapping(method=RequestMethod.POST, value = "/customer/reverse")
	void reversePayment(@RequestBody ReversePayment reversepay);
   
}