package com.time.spike.model.json.process;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonComplexProcess extends JsonProcessDescription {

    private List<JsonSubprocess> subprocesses = new ArrayList<>();
    private boolean sequential = true;
    
    public JsonComplexProcess() {}
    
    public JsonComplexProcess( JsonNode node ) {
        String processName, owner, schema;
        List<String> executors;
        
        processName = node.get( "name" ).asText();
        owner = node.get( "owner" ).asText();
        schema = node.get( "schema" ).asText();
        executors = extractStringListFrom( node.get( "possibleExecutors" ) );

        this.setName( processName );
        this.setOwner( owner );
        this.setPossibleExecutors( executors );
    }
    
    public void addSubprocess( String activationCriterionClassName, 
                               String finalizationCriterionClassname, 
                               JsonProcessDescription process ) {
        subprocesses.add( new JsonSubprocess( activationCriterionClassName, finalizationCriterionClassname, process ) );
    }
    
    public List<JsonSubprocess> getSubprocesses() {
        return subprocesses;
    }

    public void setSubprocesses( List<JsonSubprocess> subprocesses ) {
        this.subprocesses = subprocesses;
    }
    
    public boolean isSequential() {
        return sequential;
    }

    public void setSequential( boolean sequential ) {
        this.sequential = sequential;
    }

    public static class JsonSubprocess {
        
        private String activationCriterion;
        private String finalizationCriterion;
        private JsonProcessDescription subprocess;
        
        public JsonSubprocess( String activationCriterionClassName, 
                           String finalizationCriterionClassname, 
                           JsonProcessDescription process ) {
            this.activationCriterion = activationCriterionClassName;
            this.finalizationCriterion = finalizationCriterionClassname;
            this.subprocess = process;
        }
        
        public JsonSubprocess() {}

        public String getActivationCriterion() {
            return activationCriterion;
        }
        
        public void setActivationCriterion( String activationCriterion ) {
            this.activationCriterion = activationCriterion;
        }
        
        public JsonProcessDescription getSubprocess() {
            return subprocess;
        }
        
        public void setSubprocess( JsonProcessDescription subprocess ) {
            this.subprocess = subprocess;
        }

        public String getFinalizationCriterion() {
            return finalizationCriterion;
        }

        public void setFinalizationCriterion( String finalizationCriterion ) {
            this.finalizationCriterion = finalizationCriterion;
        }

        @Override
        public String toString() {
            return "JsonSubprocess [activationCriterion=" + activationCriterion + ", finalizationCriterion="
                    + finalizationCriterion + ", subprocess=" + subprocess + "]";
        }
        
        
    }

    @Override
    public String toString() {
        return "JsonComplexProcess [subprocesses=" + subprocesses + ", sequential=" + sequential + ", getName()="
                + getName() + ", getOwner()=" + getOwner() + ", getPossibleExecutors()=" + getPossibleExecutors() + "]";
    }
    
    
    
}
