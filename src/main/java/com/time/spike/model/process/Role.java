package com.time.spike.model.process;

import java.util.List;

public class Role {
    private String name;
    private List<String> policies;
    
    public Role( String name, List<String> policies ) {
        super();
        this.name = name;
        this.policies = policies;
    }

    public String getName() {
        return name;
    }

    public List<String> getPolicies() {
        return policies;
    }
    
}
