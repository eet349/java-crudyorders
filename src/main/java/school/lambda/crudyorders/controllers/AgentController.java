package school.lambda.crudyorders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.lambda.crudyorders.models.Agent;
import school.lambda.crudyorders.services.AgentServices;

@RestController
@RequestMapping(value = "/agents")
public class AgentController {
//    http://localhost:2019/agents/agent/9
    @Autowired
    private AgentServices agentServices;

    @GetMapping(value = "/agent/{agentcode}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long agentcode) {
        Agent rtnAgent = agentServices.findAgentById(agentcode);

        return new ResponseEntity<>(rtnAgent, HttpStatus.OK);
    }
}
