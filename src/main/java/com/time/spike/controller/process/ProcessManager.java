package com.time.spike.controller.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.time.spike.model.json.process.JsonComplexProcess;
import com.time.spike.model.json.process.JsonComplexProcess.JsonSubprocess;
import com.time.spike.model.json.process.JsonProcessDescription;
import com.time.spike.model.json.process.JsonSimpleStep;
import com.time.spike.model.process.ComplexProcess;
import com.time.spike.model.process.Criterion;
import com.time.spike.model.process.Executor;
import com.time.spike.model.process.ProcessDescription;
import com.time.spike.model.process.SimpleStep;
import com.time.spike.model.process.Subprocess;
import com.time.spike.persistence.PersistenceJsonHelper;

public class ProcessManager {

    private List<ProcessDescription> processes = new ArrayList<>();
    private static ProcessManager instance = null;
    
    private ProcessManager() {
        init();
    }
    
    public static ProcessManager getInstance() {
        if ( instance == null ) {
            instance = new ProcessManager();
        }
        return instance;
    }
    
    public Optional<ProcessDescription> findByName( String name ) {
        return processes.stream()
                .filter( process -> name.equals( process.getName() ) )
                .findAny();
    }
    
    private void init() {
        List<JsonProcessDescription> jsonProcesses = 
                PersistenceJsonHelper.getDefaultInstance().readJsonProcesses();
        
        processes = jsonProcesses.stream()
                .map( this::toCompleteProcess )
                .collect( Collectors.toList() );
    }
    
    private ProcessDescription toCompleteProcess( JsonProcessDescription jsonDesc ) {
        ProcessDescription desc = null;
        
        if ( jsonDesc instanceof JsonSimpleStep ) {
            desc = createSimpleStep( ( JsonSimpleStep ) jsonDesc );
        }
        else {
            desc = createComplexProcess( ( JsonComplexProcess ) jsonDesc );
        }
        return desc;
    }

    ComplexProcess createComplexProcess( JsonComplexProcess jsonComplex ) {
        return new ComplexProcess.Builder()
                .setName( jsonComplex.getName() )
                .setOwner( UserManager.getInstance().findById( jsonComplex.getOwner() ) )
                .setPossibleExecutors( UserManager.getInstance().findByRoleNames( jsonComplex.getPossibleExecutors() ) )
                .setSequential( jsonComplex.isSequential() )
                .setSubprocesses( generateSubprocesses( jsonComplex.getSubprocesses() ) )
                .build();
    }

    SimpleStep createSimpleStep( JsonSimpleStep jsonDesc ) {
        return new SimpleStep.Builder()
                .setName( jsonDesc.getName() )
                .setOwner( UserManager.getInstance().findById( jsonDesc.getOwner() ) )
                .setPossibleExecutors( UserManager.getInstance().findByRoleNames( jsonDesc.getPossibleExecutors() ) )
                .setManual( jsonDesc.isManual() )
                .setInputSchema( jsonDesc.getInputSchema() )
                .setStepExecutor( createExecutorByName( jsonDesc.getStepExecutor() ) )
                .build();
    }

    private List<Subprocess> generateSubprocesses( List<JsonSubprocess> subprocesses ) {
        return subprocesses.stream()
                .map( jsonSub -> new Subprocess( 
                        createCriterionByName( jsonSub.getActivationCriterion() ), 
                        createCriterionByName( jsonSub.getFinalizationCriterion() ), 
                        toCompleteProcess( jsonSub.getSubprocess() ) ) )
                .collect( Collectors.toList() );
    }

    private Criterion createCriterionByName( String criterion ) {
        return createByName( criterion, Criterion.class );
    }

    private Executor createExecutorByName( String stepExecutor ) {
        return createByName( stepExecutor, Executor.class );
    }
    
    private <T> T createByName( String name, Class<T> classToCastTo ) {
        try {
            Class<?> executorClass = Class.forName( name );
            if ( classToCastTo.isAssignableFrom( executorClass ) ) {
                return ( T ) executorClass.newInstance();
            }
        }
        catch ( ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
            e.printStackTrace();
        }
        return null;
    }
    
}
