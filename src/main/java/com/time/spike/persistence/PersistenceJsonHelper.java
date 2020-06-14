package com.time.spike.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.spike.model.json.process.JsonComplexProcess;
import com.time.spike.model.json.process.JsonProcessDescription;
import com.time.spike.model.json.process.JsonSimpleStep;

public class PersistenceJsonHelper {

    private String dataSourceFolder = ".";
    
    public static PersistenceJsonHelper getDefaultInstance() {
        return new PersistenceJsonHelper();
    }
    
    public List<JsonProcessDescription> readJsonProcesses() {
        File folder = new File( dataSourceFolder );
        if ( folder.isDirectory() ) {
            File[] processFiles = folder.listFiles( new FilenameFilter() {
                
                @Override
                public boolean accept( File dir, String name ) {
                    return name.endsWith( ".json" ) && name.contains( "process" );
                }
                
            } );
            return Arrays.stream( processFiles )
                    .map( this::readFromFile )
                    .collect( Collectors.toList() );
        }
        return null;
    }
    
    public JsonProcessDescription readFromFile( File inputFile ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode processTree = mapper.readTree( new FileInputStream( inputFile ) );
            return readFromTree( processTree );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    private JsonProcessDescription readFromTree( JsonNode processTree ) {
        if ( processTree.has( "subprocesses" ) ) {
            JsonComplexProcess process = new JsonComplexProcess( processTree );
            
            JsonNode subprocessesTree = processTree.get( "subprocesses" );
            subprocessesTree.iterator().forEachRemaining( 
                    node -> {
                        String entryCriterion = node.get( "activationCriterion" ).asText();
                        String exitCriterion = node.get( "finalizationCriterion" ).asText();
                        process.addSubprocess( entryCriterion, exitCriterion, readFromTree( node.get( "subprocess" ) ) );
                    });
            return process;
        }
        else {
            return new JsonSimpleStep( processTree );
        }
    }

}
