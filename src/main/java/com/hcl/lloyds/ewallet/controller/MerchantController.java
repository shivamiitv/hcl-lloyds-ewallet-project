package com.hcl.lloyds.ewallet.controller;

import com.hcl.lloyds.ewallet.entity.Merchant;
import com.hcl.lloyds.ewallet.service.MerchantServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ewallet/v1/merchant")
public class MerchantController {

    @Autowired
    MerchantServiceIntf merchantService;

    @PostMapping(value = "/save")
    public Merchant saveMerchant(@RequestBody Merchant merchant){
        return merchantService.saveMerchant(merchant);
    }
}
