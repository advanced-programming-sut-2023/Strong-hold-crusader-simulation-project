package org.example.view.commands;

public enum SignupMenuResponds {
    emptyField,
    noNumberPassword,
    noLowerCasePassword,
    noUpperCasePassword,
    noSpecialCharacterPassword,
    inValidLengthPassword,
    unknownError,
    invalidEmail,
    emailAlreadyExist,
    invalidUsername,
    passwordDifferentWithConfirm,
    usernameAlreadyExist,
    youAreInSignupMenu,
    pickYourSecurityQuestion,
    pickQuestionFormat,
    createUserSuccess;
    public String getResponse(){
        switch (this){
            case emptyField : {
                return "the fields with a sign can't be empty!!";
            }
            case noNumberPassword : {
                return "password must include at least one number!!";
            }
            case noLowerCasePassword : {
                return "password must include at least one lower case letter!!";
            }
            case noUpperCasePassword : {
                return "password must include at least one upper case letter!!";
            }
            case noSpecialCharacterPassword : {
                return "password must include at least one special character!!";
            }
            case inValidLengthPassword : {
                return "password must include at least 6 characters!!";
            }
            case unknownError : {
                return "password must include numbers, lowerCase letters, upperCase letters, and special characters!!";
            }
            case invalidEmail : {
                return "invalid email address!!";
            }
            case emailAlreadyExist : {
                return "your email is already exist in database!!";
            }
            case invalidUsername : {
                return "invalid username username; username must include just numbers, letters, and underScore!!";
            }
            case passwordDifferentWithConfirm : {
                return "password doesn't match with password confirm!!";
            }
            case usernameAlreadyExist : {
                return "this username already exist in database!!";
            }
            case youAreInSignupMenu : {
                return "you are in signup menu now.";
            }
            case pickYourSecurityQuestion : {
                return "pick your question please :";
            }
            case pickQuestionFormat : {
                return "please pick question with this format : pick -q <question number> -a <answer> -c <confirm>";
            }
            case createUserSuccess : {
                return "create user -> result : success";
            }
        }
        return null;
    }
}
