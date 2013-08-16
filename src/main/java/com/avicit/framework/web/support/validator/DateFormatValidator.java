package com.avicit.framework.web.support.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateFormatValidator implements ConstraintValidator<DateFormat, Date> {

    private Pattern pattern;
    
    @Override
    public void initialize(DateFormat dateFormat) {
        this.pattern = Pattern.compile(dateFormat.value());
    }
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintContext) {
        if(date == null) {
            return false;
        }
        
        if(this.pattern.matcher(new SimpleDateFormat("yyyy-MM-dd").format(date)).matches()) {
            return true;
        }
        
        return false;
    }
    
}
