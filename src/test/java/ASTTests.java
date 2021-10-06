import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import makers.*;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ASTTests {
    DataKey<int[]> valuesKey = new DataKey<>(){};
    @Test
    public void testMakeIntLiteral() {
        IntLiteralMaker maker = new IntLiteralMaker(0);
        List<Map<String,Integer>> contexts = List.of(Map.of("x",0),Map.of("x", 1));
        Expression lit = maker.make(List.of(),contexts);
        assertEquals(IntegerLiteralExpr.class,lit.getClass());
        assertEquals("0",lit.toString());
        int [] values = lit.getData(ASTMaker.VALUES);
        assertArrayEquals(new int[] {0,0},values);

        maker = new IntLiteralMaker(1);
        lit = maker.make(List.of(),contexts);
        assertEquals(IntegerLiteralExpr.class,lit.getClass());
        assertEquals("1",lit.toString());
        values = lit.getData(ASTMaker.VALUES);
        assertArrayEquals(new int[] {1,1},values);
    }

    @Test
    public void testVariable() {
        VariableMaker maker = new VariableMaker("x");
        List<Map<String,Integer>> contexts = List.of(Map.of("x",1),Map.of("x", 2));
        Expression x = maker.make(List.of(),contexts);
        assertEquals("x",x.toString());
        int [] values = x.getData(ASTMaker.VALUES);
        assertArrayEquals(new int[] {1,2},values);
    }

    @Test
    public void testListsOfMakers() {
        ASTMaker [] arity0 = {
            new VariableMaker("x"),
            new VariableMaker("y"),
            new IntLiteralMaker(0),
            new IntLiteralMaker(1),
            new IntLiteralMaker(-1)
        };
        ASTMaker [] arity2 = {
            new AdditionMaker(),
            new SubtractionMaker()
        };
        List<Map<String,Integer>> contexts = List.of(
                Map.of("x",0,"y",2),
                Map.of("x", 1,"y",2));

        List<Expression> level0 = Arrays.stream(arity0).map(maker -> maker.make(List.of(),contexts)).collect(Collectors.toList());
        assertEquals(5,level0.size());
        assertEquals("x",level0.get(0).toString());
        assertEquals("y",level0.get(1).toString());
        assertEquals("0",level0.get(2).toString());
        assertEquals("1",level0.get(3).toString());
        assertEquals("-1",level0.get(4).toString());


        List<Expression> level1 = Arrays.stream(arity2).flatMap(maker ->
                //childrenLists
                level0.stream().flatMap(e1 -> level0.stream().map(e2 -> List.of(e1,e2))).
                    map(children -> maker.make(children,contexts))).collect(Collectors.toList());

        assertArrayEquals(new String[]{
                        "x + x",
                        "x + y",
                        "x + 0",
                        "x + 1",
                        "x + -1",
                        "y + x",
                        "y + y",
                        "y + 0",
                        "y + 1",
                        "y + -1",
                        "0 + x",
                        "0 + y",
                        "0 + 0",
                        "0 + 1",
                        "0 + -1",
                        "1 + x",
                        "1 + y",
                        "1 + 0",
                        "1 + 1",
                        "1 + -1",
                        "-1 + x",
                        "-1 + y",
                        "-1 + 0",
                        "-1 + 1",
                        "-1 + -1",
                //now subtraction
                        "x - x",
                        "x - y",
                        "x - 0",
                        "x - 1",
                        "x - -1",
                        "y - x",
                        "y - y",
                        "y - 0",
                        "y - 1",
                        "y - -1",
                        "0 - x",
                        "0 - y",
                        "0 - 0",
                        "0 - 1",
                        "0 - -1",
                        "1 - x",
                        "1 - y",
                        "1 - 0",
                        "1 - 1",
                        "1 - -1",
                        "-1 - x",
                        "-1 - y",
                        "-1 - 0",
                        "-1 - 1",
                        "-1 - -1",
                },
                level1.stream().map(e -> e.toString()).toArray());
    }

    @Test
    public void testToStringness() {
        JavaParser parser = new JavaParser();
        ParseResult<Expression> res = parser.parseExpression("-(x + y)");
        assertTrue(res.isSuccessful());
        Expression e = res.getResult().get();
        ((UnaryExpr)e).setExpression((Expression)e.getChildNodes().get(0).getChildNodes().get(0).clone());
        assertEquals("-x + y", e.toString());
        assertEquals("-(x + y)", PrintWithParens.toString(e));
    }
}
