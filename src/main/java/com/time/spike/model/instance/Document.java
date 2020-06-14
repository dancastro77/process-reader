package com.time.spike.model.instance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.time.spike.model.document.DocumentSchemaVersion;
import com.time.spike.model.document.Field;

public class Document {
    
    private Builder builder;
    
    public Document( Builder builder ) {
        this.builder = builder;
    }

    public DocumentSchemaVersion getSchema() {
        return builder.schema;
    }

    public Map<Field, Object> getDocumentValues() {
        return builder.documentValues;
    }
    
    public void putDocumentValue( Field field, Object value ) {
        builder.documentValues.put( field, value );
    }

    public Date getDate() {
        return builder.date;
    }

    public void setDate( Date date ) {
        builder.date = date;
    }

    public List<Document> getSubdocuments() {
        return builder.subdocuments;
    }

    public void addSubdocument( Document subdocument ) {
        builder.subdocuments.add( subdocument );
    }

    static class Builder {
        private DocumentSchemaVersion schema;
        private Map<Field, Object> documentValues;
        private Date date;
        private List<Document> subdocuments;
        
        public Builder setSchema( DocumentSchemaVersion schema ) {
            this.schema = schema;
            return this;
        }
        
        public Builder setDocumentValues( Map<Field, Object> documentValues ) {
            this.documentValues = documentValues;
            return this;
        }
        
        public Builder setDate( Date date ) {
            this.date = date;
            return this;
        }
        
        public Builder setSubdocuments( List<Document> subdocuments ) {
            this.subdocuments = subdocuments;
            return this;
        }
    
        public Document build() {
            return new Document( this );
        }
        
    }
}
