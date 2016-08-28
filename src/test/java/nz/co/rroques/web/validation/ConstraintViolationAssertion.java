package nz.co.rroques.web.validation;

import nz.co.rroques.web.EventView;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ConstraintViolationAssertion
        extends AbstractAssert<ConstraintViolationAssertion, Set<ConstraintViolation<EventView>>> {

    protected ConstraintViolationAssertion(Set<ConstraintViolation<EventView>> actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static ConstraintViolationAssertion assertThat(Set<ConstraintViolation<EventView>> actual) {
        return new ConstraintViolationAssertion(actual, ConstraintViolationAssertion.class);
    }

    public ConstraintViolationAssertion hasViolation(String propertyPath, String message) {
        Assertions.assertThat(actual)
                .extracting("propertyPath.currentLeafNode.name", "message")
                .contains(Tuple.tuple(propertyPath, message));
        return this;
    }
}
