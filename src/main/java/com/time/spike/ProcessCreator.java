package com.time.spike;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.spike.model.json.process.JsonComplexProcess;
import com.time.spike.model.json.process.JsonProcessDescription;
import com.time.spike.model.json.process.JsonSimpleStep;
import com.time.spike.persistence.PersistenceJsonHelper;

/**
 * @author danilo
 *
 */
public class ProcessCreator {

    String role = "creator";
    String user = "dcastro";
    List<String> executors = Arrays.asList( "creator", "executor" );

    public JsonProcessDescription buildProcess() {
        
        JsonComplexProcess process = new JsonComplexProcess();
        process.setName( "TEAM Process" );
        process.setOwner( user );
        process.setPossibleExecutors( executors );
        
        JsonSimpleStep subprocess1 = createSimple( "Post offer", true, "schema1", "executorClass1" );
        JsonComplexProcess subprocess2 = new JsonComplexProcess();
        subprocess2.setName( "Receive applications" );
        subprocess2.setOwner( user );
        subprocess2.setPossibleExecutors( executors );
        JsonSimpleStep receivingStep = createSimple( "Receive application", false, "schema2", "executorClass2" );
        JsonSimpleStep closingStep = createSimple( "Close", true, "schema3", "executorClass3" );
                
        subprocess2.addSubprocess( "com.time.thing.ActCrit2", "com.time.thing.FinCrit2", receivingStep );
        subprocess2.addSubprocess( "com.time.thing.ActCrit3", "com.time.thing.FinCrit3", closingStep );
        process.addSubprocess( "com.time.thing.ActivationCriterion1", "com.time.thing.FinalizationCriterion1", subprocess1 );
        process.addSubprocess( "com.time.thing.ActivationCriterion2", "com.time.thing.FinalizationCriterion2", subprocess2 );
        return process;
    }
    
    private JsonSimpleStep createSimple( String name, boolean manual, String schema, String executorClass ) {
        JsonSimpleStep step = new JsonSimpleStep();
        
        step.setName( name );
        step.setOwner( user );
        step.setPossibleExecutors( executors );
        step.setInputSchema( schema );
        step.setManual( manual );
        step.setStepExecutor( executorClass );
        
        return step;
    }

    public static void main( String... args ) {
        //ProcessCreator creator = new ProcessCreator();
        //JsonProcessDescription process = creator.buildProcess();
        //ObjectMapper mapper = new ObjectMapper();
        JsonProcessDescription process = PersistenceJsonHelper.getDefaultInstance().readFromFile( new File( "process.json" ) );
        System.out.println( process );
        
        /*
        try {
            JsonComplexProcess process = mapper.reader( JsonComplexProcess.class ).readValue( new File( "process.json" ) );
            mapper.writerWithDefaultPrettyPrinter().writeValue( new File( "process.json" ), process );
            System.out.println( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( process ) );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }*/
    }
}
