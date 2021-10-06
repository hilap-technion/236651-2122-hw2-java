package makers;

import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;

import java.util.Map;
import java.util.List;

public interface ASTMaker {
    Expression make(List<Expression> children, List<Map<String,Integer>> contexts);

    public static final DataKey<int[]> VALUES = new DataKey<int[]>() { };
}
