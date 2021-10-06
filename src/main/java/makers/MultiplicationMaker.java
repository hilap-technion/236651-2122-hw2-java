package makers;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MultiplicationMaker implements ASTMaker {
    @Override
    public Expression make(List<Expression> children, List<Map<String, Integer>> contexts) {
        BinaryExpr e = new BinaryExpr(children.get(0).clone(),children.get(1).clone(), BinaryExpr.Operator.MULTIPLY);
        int [] lhs = children.get(0).getData(ASTMaker.VALUES);
        int [] rhs = children.get(1).getData(ASTMaker.VALUES);
        e.setData(ASTMaker.VALUES, IntStream.range(0,lhs.length).map(i -> lhs[i] * rhs[i]).toArray());
        return e;
    }
}
