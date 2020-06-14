package com.time.spike.model.process;

import java.util.List;

public class SimpleStep extends ProcessDescription {

    private boolean manual;
    private String inputSchema;
    private Executor stepExecutor;
    
    private SimpleStep( Builder builder ) {
        super( builder.name, builder.owner, builder.possibleExecutors );
        this.inputSchema = builder.inputSchema;
        this.manual = builder.manual;
        this.stepExecutor = builder.stepExecutor;
    }

    public boolean isManual() {
        return manual;
    }
    
    public String getInputSchema() {
        return inputSchema;
    }
    
    public Executor getStepExecutor() {
        return stepExecutor;
    }

    public static class Builder {
        private String name;
        private User owner;
        private List<Role> possibleExecutors;
        private boolean manual;
        private String inputSchema;
        private Executor stepExecutor;
        
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
        
        public Builder setManual( boolean manual ) {
            this.manual = manual;
            return this;
        }
        
        public Builder setInputSchema( String inputSchema ) {
            this.inputSchema = inputSchema;
            return this;
        }
        
        public Builder setStepExecutor( Executor stepExecutor ) {
            this.stepExecutor = stepExecutor;
            return this;
        }
        
        public SimpleStep build() {
            return new SimpleStep( this );
        }
    }
    
}
