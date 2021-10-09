package librarysystem.rulesets;

import librarysystem.AddMemberWindow;
import librarysystem.CheckOutBookWindow;
import librarysystem.LoginWindow;

import java.awt.*;
import java.util.HashMap;

final public class RuleSetFactory {
    private RuleSetFactory(){}
    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();
    static {
        map.put(AddMemberWindow.class, new AddMemberRuleSet());
        map.put(LoginWindow.class, new LoginRuleSet());
        map.put(CheckOutBookWindow.class, new CheckoutRuleSet());
    }
    public static RuleSet getRuleSet(Component c) {
        Class<? extends Component> cl = c.getClass();
        if(!map.containsKey(cl)) {
            throw new IllegalArgumentException(
                    "No RuleSet found for this Component");
        }
        return map.get(cl);
    }
}
