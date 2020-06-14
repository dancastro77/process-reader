package com.time.spike.model.process;

import java.util.ArrayList;
import java.util.List;

public class ComplexProcess extends ProcessDescription {

    private List<Subprocess> subprocesses = null;
    private boolean sequential;
            
    ComplexProcess( Builder builder ) {
        super( builder.name, builder.owner, builder.possibleExecutors );
        this.sequential = builder.sequential;
        this.subprocesses = builder.subprocesses;
    }

    public List<Subprocess> getSubprocesses() {
        return subprocesses;
    }

    public boolean isSequential() {
        return sequential;
    }

    public static class Builder {
        private List<Subprocess> subprocesses = new ArrayList<>();
        private boolean sequential = true;
        private String name;
        private User owner;
        private List<Role> possibleExecutors;
        
        public Builder setSubprocesses( List<Subprocess> subprocesses ) {
            this.subprocesses = subprocesses;
            return this;
        }
        
        public Builder setSequential( boolean sequential ) {
            this.sequential = sequential;
            return this;
        }
        
        public Builder setName( String name ) {
            this.name = name;
            return this;
        }
        
        public Builder setOwner( User owner ) {
            this.owner = owner;
            return this;
        }
        
        public Builder setPossibleExecutors( List<Role> possibleExecutors ) {
            this.possibleExecutors = possibleExecutors;
            return this;
        }

        public ComplexProcess build() {
            return new ComplexProcess( this );
        }
    }

}
