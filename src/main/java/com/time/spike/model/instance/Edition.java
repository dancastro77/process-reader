package com.time.spike.model.instance;

import java.util.Date;
import java.util.Map;

import com.time.spike.model.document.Field;
import com.time.spike.model.process.User;

public class Edition {
    private Date editionDate;
    private User editor;
    private Map<Field, Object> editedFields;
}
