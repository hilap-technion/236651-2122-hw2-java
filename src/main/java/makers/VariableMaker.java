package makers;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.List;
import java.util.Map;

public class VariableMaker implements ASTMaker{
    private final String varname;
    public VariableMaker(String varname){
        this.varname = varname;
    }

    @Override
    public Expression make(List<Expression> children, List<Map<String, Integer>> contexts) {
        NameExpr e = new NameExpr(varname);
        e.setData(ASTMaker.VALUES,contexts.stream().mapToInt(ctx -> ctx.get(varname)).toArray());
        return e;
    }
}
