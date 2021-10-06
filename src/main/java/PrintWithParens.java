import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.Visitable;

public class PrintWithParens extends CloneVisitor {

    @Override
    public Visitable visit(NameExpr n, Object arg) {
        return super.visit(n, arg);
    }

    @Override
    public Visitable visit(UnaryExpr n, Object arg) {
        if (n.hasParentNode() && !((Expression)n.getParentNode().get()).isUnaryExpr()&& !((Expression)n.getParentNode().get()).isEnclosedExpr())
            return new EnclosedExpr((Expression)super.visit(n,arg));
        return super.visit(n, arg);
    }

    @Override
    public Visitable visit(BinaryExpr n, Object arg) {
        if (n.hasParentNode() && !((Expression)n.getParentNode().get()).isEnclosedExpr())
            return new EnclosedExpr((Expression)super.visit(n,arg));
        return super.visit(n, arg);
    }

    @Override
    public Visitable visit(IntegerLiteralExpr n, Object arg) {
        return super.visit(n, arg);
    }

    static public String toString(Expression e){
        PrintWithParens p = new PrintWithParens();
        Expression newExpr = (Expression)e.accept(p,null);
        return newExpr.toString();
    }
}
