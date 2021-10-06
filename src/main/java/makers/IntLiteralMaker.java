package makers;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;

import java.util.List;
import java.util.Map;

public class IntLiteralMaker implements ASTMaker {
    final int value;
    public IntLiteralMaker(int value) {
        this.value = value;
    }

    @Override
    public Expression make(List<Expression> children, List<Map<String, Integer>> contexts) {
        IntegerLiteralExpr e = new IntegerLiteralExpr(value);
        e.setData(ASTMaker.VALUES, contexts.stream().mapToInt(ctx -> value).toArray());
        return e;
    }
}
