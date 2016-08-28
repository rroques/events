package nz.co.rroques.web.validation;


import nz.co.rroques.web.EventView;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class EventViewValidationTest {

    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameCannotBeNull() {
        EventView view = new EventView();
        Set<ConstraintViolation<EventView>> violations = validator.validate(view);
        ConstraintViolationAssertion
                .assertThat(violations)
                .hasViolation("name", "may not be null");
    }


}
