package makers;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UMinusMaker implements ASTMaker{
    @Override
    public Expression make(List<Expression> children, List<Map<String, Integer>> contexts) {
        UnaryExpr e = new UnaryExpr(children.get(0).clone(), UnaryExpr.Operator.MINUS);
        int [] arg = children.get(0).getData(ASTMaker.VALUES);
        e.setData(ASTMaker.VALUES, Arrays.stream(arg).map(x -> -x).toArray());
        return e;
    }
}
