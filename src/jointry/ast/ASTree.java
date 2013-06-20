package jointry.ast;
import java.util.Iterator;

import jointry.parser.env.Environment;

public abstract class ASTree implements Iterable<ASTree> {
	public static final int TRUE = 1;
	public static final int FALSE = 0;

	public abstract ASTree child(int i);
	public abstract int numChildren();
	public abstract Iterator<ASTree> children();
	public abstract String location();
	public Iterator<ASTree> iterator() { return children(); }
	public abstract Object eval(Environment env);
}
