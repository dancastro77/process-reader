package com.time.spike.model.document;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DocumentSchemaVersion {
       
    private Builder builder = null;
    
    public DocumentSchemaVersion( Builder builder ) {
        this.builder = builder;
    }

    public DocumentSchema getSchema() {
        return builder.schema;
    }
    
    public int getVersionNumber() {
        return builder.versionNumber;
    }
    
    public Date getFromDate() {
        return builder.fromDate;
    }
    
    public Date getToDate() {
        return builder.toDate;
    }
    
    public List<Field> getFields() {
        return builder.fields;
    }
    
    public List<DocumentSchemaVersion> getSubdocuments() {
        return builder.subdocuments;
    }
    
    public Function<Map<Field, Object>, Boolean> getaPosterioriValidation() {
        return builder.aPosterioriValidation;
    }
    
    static class Builder {

        private DocumentSchema schema;
        private int versionNumber;
        private Date fromDate;
        private Date toDate;
        private List<Field> fields;
        private List<DocumentSchemaVersion> subdocuments;
        private Function<Map<Field, Object>, Boolean> aPosterioriValidation;

        public Builder setSchema( DocumentSchema schema ) {
            this.schema = schema;
            return this;
        }
        
        public Builder setVersionNumber( int versionNumber ) {
            this.versionNumber = versionNumber;
            return this;
        }
        
        public Builder setFromDate( Date fromDate ) {
            this.fromDate = fromDate;
            return this;
        }
        
        public Builder setToDate( Date toDate ) {
            this.toDate = toDate;
            return this;
        }
        
        public Builder setFields( List<Field> fields ) {
            this.fields = fields;
            return this;
        }
        
        public Builder addField( Field field ) {
            this.fields.add( field );
            return this;
        }
        
        public Builder setSubdocuments( List<DocumentSchemaVersion> subdocuments ) {
            this.subdocuments = subdocuments;
            return this;
        }
        
        public Builder addSubdocument( DocumentSchemaVersion subdocument ) {
            this.subdocuments.add( subdocument );
            return this;
        }
        
        public Builder setaPosterioriValidation( Function<Map<Field, Object>, Boolean> aPosterioriValidation ) {
            this.aPosterioriValidation = aPosterioriValidation;
            return this;
        }
        
        public DocumentSchemaVersion build() {
            return new DocumentSchemaVersion( this );
        }
    
    }
}
