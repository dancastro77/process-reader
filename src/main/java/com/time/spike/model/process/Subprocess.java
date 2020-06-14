package com.time.spike.model.process;

public class Subprocess {

    private Criterion activationCriterion, finalizationCriterion;
    private ProcessDescription process;
    
    public Subprocess( Criterion activationCriterion, Criterion finalizationCriterion, ProcessDescription process ) {
        super();
        this.activationCriterion = activationCriterion;
        this.finalizationCriterion = finalizationCriterion;
        this.process = process;
    }

    public Criterion getActivationCriterion() {
        return activationCriterion;
    }

    public Criterion getFinalizationCriterion() {
        return finalizationCriterion;
    }

    public ProcessDescription getProcess() {
        return process;
    }
    
}
