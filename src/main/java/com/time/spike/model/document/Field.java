package com.time.spike.model.document;

import java.util.function.Function;

public class Field {
    
    private String name;
    private String type;
    private boolean repeated;
    private Object defaultValue;
    private Function<Object, Boolean> aPrioriValidation;
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public boolean isRepeated() {
        return repeated;
    }
    
    public Object getDefaultValue() {
        return defaultValue;
    }
    
    public Function<Object, Boolean> getaPrioriValidation() {
        return aPrioriValidation;
    }
    
    
}
