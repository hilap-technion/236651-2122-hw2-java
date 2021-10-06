import com.github.javaparser.ast.expr.Expression;
import makers.ASTMaker;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EnumerationTests {
    // Notice: the full, unpruned enumeration appears in the file, and
    // commented out lines are programs from the full enumeration that
    // need to be skipped with observational equivalence.

    @Test
    public void testEnumerate1() {
        List<Map<String,Integer>> contexts = List.of(Map.of("x",1,"y",2,"z",3));
        Enumerator enumerator = new Enumerator(List.of(0,1),List.of("x","y","z"),contexts);
        Expression e = enumerator.next();
        assertEquals("x",e.toString());
        assertArrayEquals(new int[] {1},e.getData(ASTMaker.VALUES));
        assertEquals("y",PrintWithParens.toString(enumerator.next())); //  values: [2]
        assertEquals("z",PrintWithParens.toString(enumerator.next())); //  values: [3]
        assertEquals("0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        assertEquals("-x",PrintWithParens.toString(enumerator.next())); //  values: [-1]
        assertEquals("-y",PrintWithParens.toString(enumerator.next())); //  values: [-2]
        assertEquals("-z",PrintWithParens.toString(enumerator.next())); //  values: [-3]
        //assertEquals("-0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("-1",PrintWithParens.toString(enumerator.next())); //  values: [-1]

        //assertEquals("x + x",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("x + y",PrintWithParens.toString(enumerator.next())); //  values: [3]
        assertEquals("x + z",PrintWithParens.toString(enumerator.next())); //  values: [4]
        //assertEquals("x + 0",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("x + 1",PrintWithParens.toString(enumerator.next())); //  values: [2]

        //assertEquals("y + x",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("y + y",PrintWithParens.toString(enumerator.next())); //  values: [4]
        assertEquals("y + z",PrintWithParens.toString(enumerator.next())); //  values: [5]
        //assertEquals("y + 0",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("y + 1",PrintWithParens.toString(enumerator.next())); //  values: [3]

        //assertEquals("z + x",PrintWithParens.toString(enumerator.next())); //  values: [4]
        //assertEquals("z + y",PrintWithParens.toString(enumerator.next())); //  values: [5]
        assertEquals("z + z",PrintWithParens.toString(enumerator.next())); //  values: [6]
        //assertEquals("z + 0",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("z + 1",PrintWithParens.toString(enumerator.next())); //  values: [4]

        //assertEquals("0 + x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("0 + y",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("0 + z",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("0 + 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 + 1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("1 + x",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("1 + y",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("1 + z",PrintWithParens.toString(enumerator.next())); //  values: [4]
        //assertEquals("1 + 0",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("1 + 1",PrintWithParens.toString(enumerator.next())); //  values: [2]

        //assertEquals("x - x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("x - y",PrintWithParens.toString(enumerator.next())); //  values: [-1]
        //assertEquals("x - z",PrintWithParens.toString(enumerator.next())); //  values: [-2]
        //assertEquals("x - 0",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("x - 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("y - x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("y - y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("y - z",PrintWithParens.toString(enumerator.next())); //  values: [-1]
        //assertEquals("y - 0",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("y - 1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("z - x",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("z - y",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("z - z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("z - 0",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("z - 1",PrintWithParens.toString(enumerator.next())); //  values: [2]

        //assertEquals("0 - x",PrintWithParens.toString(enumerator.next())); //  values: [-1]
        //assertEquals("0 - y",PrintWithParens.toString(enumerator.next())); //  values: [-2]
        //assertEquals("0 - z",PrintWithParens.toString(enumerator.next())); //  values: [-3]
        //assertEquals("0 - 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 - 1",PrintWithParens.toString(enumerator.next())); //  values: [-1]

        //assertEquals("1 - x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("1 - y",PrintWithParens.toString(enumerator.next())); //  values: [-1]
        //assertEquals("1 - z",PrintWithParens.toString(enumerator.next())); //  values: [-2]
        //assertEquals("1 - 0",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("1 - 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("x * x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("x * y",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("x * z",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("x * 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("x * 1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("y * x",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("y * y",PrintWithParens.toString(enumerator.next())); //  values: [4]
        //assertEquals("y * z",PrintWithParens.toString(enumerator.next())); //  values: [6]
        //assertEquals("y * 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("y * 1",PrintWithParens.toString(enumerator.next())); //  values: [2]

        //assertEquals("z * x",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("z * y",PrintWithParens.toString(enumerator.next())); //  values: [6]
        assertEquals("z * z",PrintWithParens.toString(enumerator.next())); //  values: [9]
        //assertEquals("z * 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("z * 1",PrintWithParens.toString(enumerator.next())); //  values: [3]

        //assertEquals("0 * x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 * y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 * z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 * 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 * 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("1 * x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("1 * y",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("1 * z",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("1 * 0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("1 * 1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("x / x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("x / y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("x / z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("x / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("x / 1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("y / x",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("y / y",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("y / z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("y / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("y / 1",PrintWithParens.toString(enumerator.next())); //  values: [2]

        //assertEquals("z / x",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("z / y",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("z / z",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("z / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("z / 1",PrintWithParens.toString(enumerator.next())); //  values: [3]

        //assertEquals("0 / x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 / y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 / z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("0 / 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("1 / x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("1 / y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("1 / z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("1 / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("1 / 1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("x % x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("x % y",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("x % z",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("x % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("x % 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("y % x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("y % y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("y % z",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("y % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("y % 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("z % x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("z % y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("z % z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("z % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("z % 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("0 % x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 % y",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 % z",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("0 % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("0 % 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("1 % x",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("1 % y",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("1 % z",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("1 % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("1 % 1",PrintWithParens.toString(enumerator.next())); //  values: [0]

        //assertEquals("--x",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("--y",PrintWithParens.toString(enumerator.next())); //  values: [2]
        //assertEquals("--z",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("--0",PrintWithParens.toString(enumerator.next())); //  values: [0]
        //assertEquals("--1",PrintWithParens.toString(enumerator.next())); //  values: [1]

        //assertEquals("-(x + x)",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("-(x + y)",PrintWithParens.toString(enumerator.next())); //  values: [3]
        //assertEquals("-(x + z)",PrintWithParens.toString(enumerator.next())); //  values: [4]
        //assertEquals("-(x + 0)",PrintWithParens.toString(enumerator.next())); //  values: [1]
        //assertEquals("-(x + 1)",PrintWithParens.toString(enumerator.next())); //  values: [2]
    }

    @Test
    public void testEnumerate2() {
        List<Map<String,Integer>> contexts = List.of(Map.of("x",1,"y",2),Map.of("x",8,"y",2));
        Enumerator enumerator = new Enumerator(List.of(0,1),List.of("x","y"),contexts);

        assertEquals("x",PrintWithParens.toString(enumerator.next())); //  values: [1,8]
        assertEquals("y",PrintWithParens.toString(enumerator.next())); //  values: [2,2]
        assertEquals("0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        assertEquals("1",PrintWithParens.toString(enumerator.next())); //  values: [1,1]

        assertEquals("-x",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8]
        assertEquals("-y",PrintWithParens.toString(enumerator.next())); //  values: [-2,-2]
        //assertEquals("-0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        assertEquals("-1",PrintWithParens.toString(enumerator.next())); //  values: [-1,-1]

        assertEquals("x + x",PrintWithParens.toString(enumerator.next())); //  values: [2,16]
        assertEquals("x + y",PrintWithParens.toString(enumerator.next())); //  values: [3,10]
        //assertEquals("x + 0",PrintWithParens.toString(enumerator.next())); //  values: [1,8]
        assertEquals("x + 1",PrintWithParens.toString(enumerator.next())); //  values: [2,9]

        //assertEquals("y + x",PrintWithParens.toString(enumerator.next())); //  values: [3,10]
        assertEquals("y + y",PrintWithParens.toString(enumerator.next())); //  values: [4,4]
        //assertEquals("y + 0",PrintWithParens.toString(enumerator.next())); //  values: [2,2]
        assertEquals("y + 1",PrintWithParens.toString(enumerator.next())); //  values: [3,3]

        //assertEquals("0 + x",PrintWithParens.toString(enumerator.next())); //  values: [1,8]
        //assertEquals("0 + y",PrintWithParens.toString(enumerator.next())); //  values: [2,2]
        //assertEquals("0 + 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 + 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1]

        //assertEquals("1 + x",PrintWithParens.toString(enumerator.next())); //  values: [2,9]
        //assertEquals("1 + y",PrintWithParens.toString(enumerator.next())); //  values: [3,3]
        //assertEquals("1 + 0",PrintWithParens.toString(enumerator.next())); //  values: [1,1]
        //assertEquals("1 + 1",PrintWithParens.toString(enumerator.next())); //  values: [2,2]

        //assertEquals("x - x",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        assertEquals("x - y",PrintWithParens.toString(enumerator.next())); //  values: [-1,6]
        //assertEquals("x - 0",PrintWithParens.toString(enumerator.next())); //  values: [1,8]
        assertEquals("x - 1",PrintWithParens.toString(enumerator.next())); //  values: [0,7]

        assertEquals("y - x",PrintWithParens.toString(enumerator.next())); //  values: [1,-6]
        //assertEquals("y - y",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("y - 0",PrintWithParens.toString(enumerator.next())); //  values: [2,2]
        //assertEquals("y - 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1]

        //assertEquals("0 - x",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8]
        //assertEquals("0 - y",PrintWithParens.toString(enumerator.next())); //  values: [-2,-2]
        //assertEquals("0 - 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 - 1",PrintWithParens.toString(enumerator.next())); //  values: [-1,-1]

        assertEquals("1 - x",PrintWithParens.toString(enumerator.next())); //  values: [0,-7]
        //assertEquals("1 - y",PrintWithParens.toString(enumerator.next())); //  values: [-1,-1]
        //assertEquals("1 - 0",PrintWithParens.toString(enumerator.next())); //  values: [1,1]
        //assertEquals("1 - 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        assertEquals("x * x",PrintWithParens.toString(enumerator.next())); //  values: [1,64]
        //assertEquals("x * y",PrintWithParens.toString(enumerator.next())); //  values: [2,16]
        //assertEquals("x * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("x * 1",PrintWithParens.toString(enumerator.next())); //  values: [1,8]

        //assertEquals("y * x",PrintWithParens.toString(enumerator.next())); //  values: [2,16]
        //assertEquals("y * y",PrintWithParens.toString(enumerator.next())); //  values: [4,4]
        //assertEquals("y * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("y * 1",PrintWithParens.toString(enumerator.next())); //  values: [2,2]

        //assertEquals("0 * x",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 * y",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 * 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        //assertEquals("1 * x",PrintWithParens.toString(enumerator.next())); //  values: [1,8]
        //assertEquals("1 * y",PrintWithParens.toString(enumerator.next())); //  values: [2,2]
        //assertEquals("1 * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("1 * 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1]

        //assertEquals("x / x",PrintWithParens.toString(enumerator.next())); //  values: [1,1]
        assertEquals("x / y",PrintWithParens.toString(enumerator.next())); //  values: [0,4]
        //assertEquals("x / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("x / 1",PrintWithParens.toString(enumerator.next())); //  values: [1,8]

        assertEquals("y / x",PrintWithParens.toString(enumerator.next())); //  values: [2,0]
        //assertEquals("y / y",PrintWithParens.toString(enumerator.next())); //  values: [1,1]
        //assertEquals("y / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("y / 1",PrintWithParens.toString(enumerator.next())); //  values: [2,2]

        //assertEquals("0 / x",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 / y",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("0 / 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        assertEquals("1 / x",PrintWithParens.toString(enumerator.next())); //  values: [1,0]
        //assertEquals("1 / y",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("1 / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("1 / 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1]

        //assertEquals("x % x",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("x % y",PrintWithParens.toString(enumerator.next())); //  values: [1,0]
        //assertEquals("x % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("x % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        assertEquals("y % x",PrintWithParens.toString(enumerator.next())); //  values: [0,2]
        //assertEquals("y % y",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("y % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("y % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        //assertEquals("0 % x",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 % y",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("0 % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("0 % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        assertEquals("1 % x",PrintWithParens.toString(enumerator.next())); //  values: [0,1]
        //assertEquals("1 % y",PrintWithParens.toString(enumerator.next())); //  values: [1,1]
        //assertEquals("1 % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("1 % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0]

        //assertEquals("--x",PrintWithParens.toString(enumerator.next())); //  values: [1,8]
        //assertEquals("--y",PrintWithParens.toString(enumerator.next())); //  values: [2,2]
        //assertEquals("--0",PrintWithParens.toString(enumerator.next())); //  values: [0,0]
        //assertEquals("--1",PrintWithParens.toString(enumerator.next())); //  values: [1,1]

        assertEquals("-(x + x)",PrintWithParens.toString(enumerator.next())); //  values: [-2,-16]
        assertEquals("-(x + y)",PrintWithParens.toString(enumerator.next())); //  values: [-3,-10]
        //assertEquals("-(x + 0)",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8]
        assertEquals("-(x + 1)",PrintWithParens.toString(enumerator.next())); //  values: [-2,-9]
    }
    @Test
    public void testEnumerate3() {
        List<Map<String,Integer>> contexts = List.of(Map.of("x",1), Map.of("x",8), Map.of("x",2));
        Enumerator enumerator = new Enumerator(List.of(0,1),List.of("x"),contexts);

        assertEquals("x",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]
        assertEquals("0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        assertEquals("1",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]

        assertEquals("-x",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8,-2]
        //assertEquals("-0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        assertEquals("-1",PrintWithParens.toString(enumerator.next())); //  values: [-1,-1,-1]

        assertEquals("x + x",PrintWithParens.toString(enumerator.next())); //  values: [2,16,4]
        //assertEquals("x + 0",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]
        assertEquals("x + 1",PrintWithParens.toString(enumerator.next())); //  values: [2,9,3]

        //assertEquals("0 + x",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]
        //assertEquals("0 + 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("0 + 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]

        //assertEquals("1 + x",PrintWithParens.toString(enumerator.next())); //  values: [2,9,3]
        //assertEquals("1 + 0",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]
        assertEquals("1 + 1",PrintWithParens.toString(enumerator.next())); //  values: [2,2,2]

        //assertEquals("x - x",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("x - 0",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]
        assertEquals("x - 1",PrintWithParens.toString(enumerator.next())); //  values: [0,7,1]

        //assertEquals("0 - x",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8,-2]
        //assertEquals("0 - 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("0 - 1",PrintWithParens.toString(enumerator.next())); //  values: [-1,-1,-1]

        assertEquals("1 - x",PrintWithParens.toString(enumerator.next())); //  values: [0,-7,-1]
        //assertEquals("1 - 0",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]
        //assertEquals("1 - 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]

        assertEquals("x * x",PrintWithParens.toString(enumerator.next())); //  values: [1,64,4]
        //assertEquals("x * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("x * 1",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]

        //assertEquals("0 * x",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("0 * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("0 * 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]

        //assertEquals("1 * x",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]
        //assertEquals("1 * 0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("1 * 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]

        //assertEquals("x / x",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]
        //assertEquals("x / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("x / 1",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]

        //assertEquals("0 / x",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("0 / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("0 / 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]

        assertEquals("1 / x",PrintWithParens.toString(enumerator.next())); //  values: [1,0,0]
        //assertEquals("1 / 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("1 / 1",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]

        //assertEquals("x % x",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("x % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("x % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]

        //assertEquals("0 % x",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("0 % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("0 % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]

        assertEquals("1 % x",PrintWithParens.toString(enumerator.next())); //  values: [0,1,1]
        //assertEquals("1 % 0",PrintWithParens.toString(enumerator.next())); //  values: ZeroDivisionError
        //assertEquals("1 % 1",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]

        //assertEquals("--x",PrintWithParens.toString(enumerator.next())); //  values: [1,8,2]
        //assertEquals("--0",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("--1",PrintWithParens.toString(enumerator.next())); //  values: [1,1,1]

        assertEquals("-(x + x)",PrintWithParens.toString(enumerator.next())); //  values: [-2,-16,-4]
        //assertEquals("-(x + 0)",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8,-2]
        assertEquals("-(x + 1)",PrintWithParens.toString(enumerator.next())); //  values: [-2,-9,-3]

        //assertEquals("-(0 + x)",PrintWithParens.toString(enumerator.next())); //  values: [-1,-8,-2]
        //assertEquals("-(0 + 0)",PrintWithParens.toString(enumerator.next())); //  values: [0,0,0]
        //assertEquals("-(0 + 1)",PrintWithParens.toString(enumerator.next())); //  values: [-1,-1,-1]
    }
}
