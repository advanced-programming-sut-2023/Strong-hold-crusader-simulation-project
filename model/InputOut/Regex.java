package model.InputOut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    createUser,
    password,
    passwordErrorNumber,
    passwordErrorLowerCase,
    passwordErrorUpperCaseLetter,
    passwordErrorSpecialCharacter,
    passwordInvalidLength,
    doubleQuote,
    email,
    username,
    pickRecoveryQuestion;
    public Matcher getMatcher(String  command){
        Pattern pattern;
        switch (this){
            case createUser:
                if (Pattern.matches(createUserRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(createUserRegex);
                return pattern.matcher(command);
            case password:
                if (Pattern.matches(passwordRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(passwordRegex);
                return pattern.matcher(command);
            case passwordErrorLowerCase:
                if (Pattern.matches(passwordErrorLowerCaseRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(passwordErrorLowerCaseRegex);
                return pattern.matcher(command);
            case passwordErrorUpperCaseLetter:
                if (Pattern.matches(passwordErrorUpperCaseRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(passwordErrorUpperCaseRegex);
                return pattern.matcher(command);
            case passwordErrorNumber:
                if (Pattern.matches(passwordErrorNumberRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(passwordErrorNumberRegex);
                return pattern.matcher(command);
            case passwordErrorSpecialCharacter:
                if (Pattern.matches(passwordErrorSpecialCharacterRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(passwordErrorSpecialCharacterRegex);
                return pattern.matcher(command);
            case passwordInvalidLength:
                if(Pattern.matches(passwordRegexInvalidLength , command) == false){
                    return null;
                }
                pattern = Pattern.compile(passwordRegexInvalidLength);
                return pattern.matcher(command);
            case doubleQuote:
                if (Pattern.matches(doubleQuotRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(doubleQuotRegex);
                return pattern.matcher(command);
            case email:
                if (Pattern.matches(emailRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(emailRegex);
                return pattern.matcher(command);
            case username:
                if (Pattern.matches(UsernameRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(UsernameRegex);
                return pattern.matcher(command);
            case pickRecoveryQuestion:
                if (Pattern.matches(pickRecoveryQuestionRegex , command) == false){
                    return null;
                }
                pattern = Pattern.compile(pickRecoveryQuestionRegex);
                return pattern.matcher(command);
            }
        return null;
    }

    private String createUserRegex =
            ("^(?=(.* +-u (?<username>(\"[^\"]*\")|([\\S]*))){1,})"
                    + "(?=(.* +-p (?<password>(\"[^\"]*\")|([\\S]*)) "
                    +"(?<confirm>(\"[^\"]+\")|([\\S]*))){1,})"
                    +"(?=(.* +-email (?<email>(\"[^\"]*\")|([\\S]*))){1,})"
                    +"(?=(.* +-n (?<nickname>(\"[^\"]*\")|([\\S]*))){0,})"
                    +"(?=(.* +-s (?<slogan>(\"[^\"]*\")|([\\S]*))){0,})user create.+$");
    private String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{6,20}$";
    private String passwordErrorNumberRegex = "(?=.*(?<number>[0-9])).+";
    private String passwordErrorLowerCaseRegex = "(?=.*(?<lowerCase>[a-z])).+";
    private String passwordErrorUpperCaseRegex = "(?=.*(?<upperCase>[A-Z])).+";
    private String passwordErrorSpecialCharacterRegex = "(?=.*(?<specialCharacter>[\\!\\@\\#\\$\\%\\^\\&\\*])).+";
    private String passwordRegexInvalidLength = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{4,20}$";
    private String doubleQuotRegex = "\"(?<data>.*)\"";
    private String emailRegex = "^[a-zA-Z\\_]+\\@[a-zA-Z\\_]+\\.[a-zA-Z\\_\\.]+$";
    private String UsernameRegex = "[a-zA-Z0-9\\_]+";
    private String pickRecoveryQuestionRegex = ("(?=(.* +-q (?<question>([0-9]+))){1,})"
                                                 +"(?=(.* +-a (?<answer>(\"[^\"]*\")|([\\S]*))){1,})"
                                                     +"(?=(.* +-c (?<confirm>(\"[^\"]*\")|([\\S]*))){1,})pick.+$");
}
