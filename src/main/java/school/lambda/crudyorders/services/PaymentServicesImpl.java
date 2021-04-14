package school.lambda.crudyorders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.lambda.crudyorders.models.Payment;
import school.lambda.crudyorders.repositories.PaymentRepository;

@Transactional
@Service(value = "paymentServices")
public class PaymentServicesImpl implements PaymentServices{
    @Autowired
    private PaymentRepository payrepo;
    @Transactional
    @Override
    public Payment save(Payment payment) {
        return payrepo.save(payment);
    }
}
