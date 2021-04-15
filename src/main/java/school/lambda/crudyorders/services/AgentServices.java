package school.lambda.crudyorders.services;

import school.lambda.crudyorders.models.Agent;

public interface AgentServices  {
    Agent save(Agent agent);
    Agent findAgentById(long agentcode);
}
