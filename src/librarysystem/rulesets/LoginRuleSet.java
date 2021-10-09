package librarysystem.rulesets;

import librarysystem.LoginWindow;

import java.awt.Component;


/**
 * Rules:
 * 1. All fields must be nonempty
 */
public class LoginRuleSet implements RuleSet {

    private LoginWindow loginWindow;

    @Override
    public void applyRules(Component ob) throws RuleException {
        loginWindow = (LoginWindow) ob;
        nonemptyRule();
    }

    private void nonemptyRule() throws RuleException {
        if (loginWindow.getUserName().trim().isEmpty() ||
                loginWindow.getPassword().trim().isEmpty()) {
            throw new RuleException("All fields must be non-empty!");
        }
    }

}




