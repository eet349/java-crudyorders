package school.lambda.crudyorders.repositories;

import org.springframework.data.repository.CrudRepository;
import school.lambda.crudyorders.models.Agent;

public interface AgentRepository extends CrudRepository<Agent, Long> {
}
