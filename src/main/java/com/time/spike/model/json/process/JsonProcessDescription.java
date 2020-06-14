package com.time.spike.model.json.process;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class JsonProcessDescription 
{
    private String name;
    private String owner;
    private List<String> possibleExecutors;
    private String schema;
    
    public String getName() {
        return name;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public List<String> getPossibleExecutors() {
        return possibleExecutors;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setOwner( String owner ) {
        this.owner = owner;
    }

    public void setPossibleExecutors( List<String> possibleExecutors ) {
        this.possibleExecutors = possibleExecutors;
    }
    
    protected List<String> extractStringListFrom( JsonNode listNode ) {
        List<String> elements = new ArrayList<>();
        
        listNode.iterator().forEachRemaining( node -> elements.add( node.asText() ) );
        return elements;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema( String schema ) {
        this.schema = schema;
    }

    
}
