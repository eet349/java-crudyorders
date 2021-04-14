package school.lambda.crudyorders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.lambda.crudyorders.models.Agent;
import school.lambda.crudyorders.repositories.AgentRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices{

    @Autowired
    private AgentRepository agentrepo;

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentrepo.save(agent);
    }

    @Override
    public Agent findAgentById(long agentcode) {
        return agentrepo.findById(agentcode).orElseThrow(() -> new EntityNotFoundException("Agent code " + agentcode + " Not Found!"));
    }

}
