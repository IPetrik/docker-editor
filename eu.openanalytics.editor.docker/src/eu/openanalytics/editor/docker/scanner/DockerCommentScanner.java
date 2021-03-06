package eu.openanalytics.editor.docker.scanner;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;

import eu.openanalytics.editor.docker.syntax.SyntaxColors;

public class DockerCommentScanner extends RuleBasedScanner {

	public static final String COMMENT_SEQUENCE = "#";
	
	public DockerCommentScanner() {
		IToken token = new Token(new TextAttribute(SyntaxColors.getCommentColor()));

		IRule[] rules = new IRule[1];
		rules[0] = new EndOfLineRule(COMMENT_SEQUENCE, token) {
			@Override
			public IToken evaluate(ICharacterScanner scanner, boolean resume) {
				if (getColumn() > 0) return Token.UNDEFINED;
				return super.evaluate(scanner, resume);
			}
		};

		setRules(rules);
	}
}
