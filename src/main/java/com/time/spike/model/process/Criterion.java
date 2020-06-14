package com.time.spike.model.process;

import com.time.spike.model.instance.Document;

@FunctionalInterface
public interface Criterion {
    
    boolean isMetBy( Document document );

}
