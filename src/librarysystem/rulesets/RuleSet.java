package librarysystem.rulesets;

import librarysystem.rulesets.RuleException;

import java.awt.Component;

public interface RuleSet {
	public void applyRules(Component ob) throws RuleException;
}
