package com.hcl.lloyds.ewallet;

import com.hcl.lloyds.ewallet.dto.PaymentRequest;
import com.hcl.lloyds.ewallet.entity.User;
import com.hcl.lloyds.ewallet.entity.Wallet;
import com.hcl.lloyds.ewallet.repository.UserRepository;
import com.hcl.lloyds.ewallet.repository.WalletRepository;
import com.hcl.lloyds.ewallet.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@SpringBootApplication
public class HclLloydsEwalletApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(HclLloydsEwalletApplication.class, args);

        UserRepository userRepo = ctx.getBean(UserRepository.class);
        WalletRepository walletRepo = ctx.getBean(WalletRepository.class);
        TransactionService txService = ctx.getBean(TransactionService.class);

        // ---- ensure two demo users exist ----
        User u1 = upsertUser(userRepo, "Alice", "9990000001");
        User u2 = upsertUser(userRepo, "Bob",   "9990000002");

        // ---- ensure one wallet per user (OneToOne unique) ----
        Wallet w1 = upsertWalletForUser(walletRepo, u1, BigDecimal.valueOf(10_000));
        Wallet w2 = upsertWalletForUser(walletRepo, u2, BigDecimal.ZERO);

        System.out.println("Before transfer: w1=" + w1.getBalance() + ", w2=" + w2.getBalance());

        // ---- run a demo transfer: w1 -> w2 (₹500) ----
        PaymentRequest req = new PaymentRequest();
        req.setFromWalletId(w1.getId());
        req.setToWalletId(w2.getId());
        req.setAmount(BigDecimal.valueOf(500));
        req.setExternalCustomerId("demo-user"); // used by MockPaymentGateway

        try {
            txService.transfer(req);
            // reload to show new balances
            Wallet a = walletRepo.findById(w1.getId()).orElseThrow();
            Wallet b = walletRepo.findById(w2.getId()).orElseThrow();
            System.out.println("Transfer success ✅");
            System.out.println("After  transfer: w1=" + a.getBalance() + ", w2=" + b.getBalance());
        } catch (Exception e) {
            System.out.println("Transfer failed ❌: " + e.getMessage());
        }
    }

    private static User upsertUser(UserRepository userRepo, String name, String phone) {
        // If your UserRepository already has findByPhone, great. If not, you can add it (see note below).
        Optional<User> existing = userRepo.findByPhone(phone);
        if (existing.isPresent()) {
            User u = existing.get();
            if (u.getName() == null || !u.getName().equals(name)) {
                u.setName(name);
            }
            return userRepo.save(u);
        }
        User u = new User();
        u.setName(name);
        u.setPhone(phone);
        u.setCreatedAt(Instant.now());
        return userRepo.save(u);
    }

    private static Wallet upsertWalletForUser(WalletRepository walletRepo, User user, BigDecimal minBalance) {
        // If your WalletRepository already has findByUser, great. If not, add it (see note below).
        Optional<Wallet> existing = walletRepo.findByUser(user);
        if (existing.isPresent()) {
            Wallet w = existing.get();
            if (w.getBalance() == null) w.setBalance(BigDecimal.ZERO);
            if (w.getBalance().compareTo(minBalance) < 0) {
                w.setBalance(minBalance);
            }
            w.setUpdatedAt(Instant.now());
            return walletRepo.save(w);
        }
        Wallet w = new Wallet();
        w.setUser(user); // required by @OneToOne(optional = false)
        w.setBalance(minBalance);
        w.setUpdatedAt(Instant.now());
        return walletRepo.save(w);
    }
}
