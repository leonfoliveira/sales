package br.edu.unifei.common.validator;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;

public class ConstraintValidatorContextMock implements ConstraintValidatorContext {
    @Override
    public void disableDefaultConstraintViolation() {

    }

    @Override
    public String getDefaultConstraintMessageTemplate() {
        return null;
    }

    @Override
    public ClockProvider getClockProvider() {
        return null;
    }

    @Override
    public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String s) {
        return new ConstraintViolationBuilder() {
            @Override
            public NodeBuilderDefinedContext addNode(String s) {
                return null;
            }

            @Override
            public NodeBuilderCustomizableContext addPropertyNode(String s) {
                return null;
            }

            @Override
            public LeafNodeBuilderCustomizableContext addBeanNode() {
                return null;
            }

            @Override
            public ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String s, Class<?> aClass, Integer integer) {
                return null;
            }

            @Override
            public NodeBuilderDefinedContext addParameterNode(int i) {
                return null;
            }

            @Override
            public ConstraintValidatorContext addConstraintViolation() {
                return null;
            }
        };
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
