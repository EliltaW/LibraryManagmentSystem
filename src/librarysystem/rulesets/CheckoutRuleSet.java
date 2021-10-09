package librarysystem.rulesets;

import librarysystem.CheckOutBookWindow;

import java.awt.*;

/**
 * Rules:
 * 1. All fields must be nonempty
 */
public class CheckoutRuleSet implements RuleSet {
    private CheckOutBookWindow checkOutBookWindow;

    @Override
    public void applyRules(Component ob) throws RuleException {
        checkOutBookWindow = (CheckOutBookWindow) ob;
        nonemptyRule();
    }

    private void nonemptyRule() throws RuleException {
        if (checkOutBookWindow.getMemberIdTextFeild().trim().isEmpty() ||
                checkOutBookWindow.getiIbntTextField().trim().isEmpty()) {
            throw new RuleException("All fields must be non-empty!");
        }
    }
}
