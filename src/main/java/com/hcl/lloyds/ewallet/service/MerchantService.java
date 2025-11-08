package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.entity.Merchant;
import com.hcl.lloyds.ewallet.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService implements MerchantServiceIntf{

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant saveMerchant(Merchant merchant) {
         Merchant merchantObj = merchantRepository.save(merchant);
         return merchantObj;
    }
}
