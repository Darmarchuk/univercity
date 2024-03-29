package ado.rush.university.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class PasswordValidatorConstraint implements ConstraintValidator<PasswordValid,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(List.of(
                new LengthRule(3, 30))
//                new UppercaseCharacterRule(1),
//                new DigitCharacterRule(1),
//                new SpecialCharacterRule(1),
//                new NumericalSequenceRule(3,false),
//                new AlphabeticalSequenceRule(3,false),
//                new QwertySequenceRule(3,false),
//                new WhitespaceRule())
        );

        RuleResult result = validator.validate(new PasswordData(s));
        if(result.isValid())
            return true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(
                validator.getMessages(result).stream().collect(Collectors.joining(",")))
                .addConstraintViolation();
        return false;
    }
}
