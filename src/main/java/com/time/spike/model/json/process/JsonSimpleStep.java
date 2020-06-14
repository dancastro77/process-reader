package com.time.spike.model.json.process;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonSimpleStep extends JsonProcessDescription {
    
    private boolean manual;
    private String inputSchema;
    private String stepExecutor;
    
    public JsonSimpleStep() {}
    
    public JsonSimpleStep( JsonNode node ) {
        String processName, owner;
        List<String> executors;

        processName = node.get( "name" ).asText();
        owner = node.get( "owner" ).asText();
        executors = extractStringListFrom( node.get( "possibleExecutors" ) );

        this.setName( processName );
        this.setOwner( owner );
        this.setPossibleExecutors( executors );
        this.setManual( node.get( "manual" ).asBoolean() );
        this.setInputSchema( node.get( "inputSchema" ).asText() );
        this.setStepExecutor( node.get( "stepExecutor" ).asText() );

    }
    
    public boolean isManual() {
        return manual;
    }
    
    public void setManual( boolean manual ) {
        this.manual = manual;
    }
    
    public String getInputSchema() {
        return inputSchema;
    }
    
    public void setInputSchema( String inputSchema ) {
        this.inputSchema = inputSchema;
    }
    
    public String getStepExecutor() {
        return stepExecutor;
    }
    
    public void setStepExecutor( String stepExecutor ) {
        this.stepExecutor = stepExecutor;
    }

    @Override
    public String toString() {
        return "JsonSimpleStep [inputSchema=" + inputSchema + ", manual=" + manual + ", stepExecutor=" + stepExecutor
                + ", getName()=" + getName() + ", getOwner()=" + getOwner() + ", getPossibleExecutors()="
                + getPossibleExecutors() + "]";
    }

}
