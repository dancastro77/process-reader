package com.time.spike.model.process;

import java.util.List;

public abstract class ProcessDescription {
    
    private String name;
    private User owner;
    private List<Role> possibleExecutors;
    
    ProcessDescription( String name, User owner, List<Role> possibleExecutors ) {
        super();
        this.name = name;
        this.owner = owner;
        this.possibleExecutors = possibleExecutors;
    }

    public String getName() {
        return name;
    }
    
    public User getOwner() {
        return owner;
    }
    
    public List<Role> getPossibleExecutors() {
        return possibleExecutors;
    }
    
}
