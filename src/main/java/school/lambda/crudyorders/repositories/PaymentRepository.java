package school.lambda.crudyorders.repositories;

import org.springframework.data.repository.CrudRepository;
import school.lambda.crudyorders.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
