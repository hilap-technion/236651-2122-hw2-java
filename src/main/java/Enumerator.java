import com.github.javaparser.ast.expr.Expression;
import makers.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Enumerator implements Iterator<Expression> {
    private final List<Map<String, Integer>> contexts;
    List<List<ASTMaker>> makers = List.of(
            new ArrayList<>(),//arity 0
            List.of( new UMinusMaker() ), //arity1
            List.of( new AdditionMaker(), new SubtractionMaker(), new MultiplicationMaker(), new IntDivisionMaker(), new ModMaker()));
    public Enumerator(List<Integer> literals, List<String> variables, List<Map<String,Integer>> contexts) {
        makers.get(0).addAll(Stream.concat(
                    variables.stream().map(varname -> new VariableMaker(varname)),
                        literals.stream().map(value -> new IntLiteralMaker(value))).collect(Collectors.toList()));
        this.contexts = contexts;
        makerIterator = makers.get(0).iterator();
        currMaker = makerIterator.next();
    }

    Optional<Expression> nextExpr = Optional.empty();
    int currArity = 0;
    int height = 0;
    ArrayList<Expression> previousLevelPrograms = new ArrayList<>();
    ArrayList<Expression> currentLevelPrograms = new ArrayList<>();

    private static int exprHeight(Expression e) {
        if (e.isLiteralExpr() || e.isNameExpr()) return 0;
        return e.getChildNodes().stream().mapToInt(c -> exprHeight((Expression)c)).max().getAsInt() + 1;
    }

    private Stream<List<Expression>> makeChildrenStream() {
        switch (currArity) {
            case 0: return Stream.of(List.of());
            case 1: return previousLevelPrograms.stream().filter(e -> exprHeight(e) == height - 1).map(e -> List.of(e));
            case 2: return previousLevelPrograms.stream().flatMap(e1 ->
                    previousLevelPrograms.stream().filter(e2 -> exprHeight(e1) == height - 1 || exprHeight(e2) == height - 1)
                            .map(e2-> List.of(e1,e2)));
        }
        return null;
    }

    private void changeArity() {
        currArity++;
        if (currArity == 1 || currArity > 2) {
            currArity = 1;
            previousLevelPrograms.addAll(currentLevelPrograms);
            currentLevelPrograms.clear();
            height += 1;
        }
        makerIterator = makers.get(currArity).iterator();
    }

    private void advanceRoot() {
        if (!makerIterator.hasNext()) changeArity();
        currMaker = makerIterator.next();
        children = makeChildrenStream();
        currentChildren = children.iterator();
    }

    Stream<List<Expression>> children = makeChildrenStream();
    Iterator<ASTMaker> makerIterator;
    ASTMaker currMaker;
    Iterator<List<Expression>> currentChildren = children.iterator();
    public void getNextExpr() {
        while (nextExpr.isEmpty()) {
            if (!currentChildren.hasNext()) advanceRoot();
            List<Expression> children = currentChildren.next();
            Expression e = currMaker.make(children, contexts);
            currentLevelPrograms.add(e);
            nextExpr = Optional.of(e);
        }
    }

    @Override
    public boolean hasNext() {
        if (nextExpr.isPresent()) return true;
        getNextExpr();
        return nextExpr.isPresent();
    }

    @Override
    public Expression next() {
        if (!nextExpr.isPresent()) getNextExpr();
        Expression ret = nextExpr.get();
        nextExpr = Optional.empty();
        return ret;
    }
}
