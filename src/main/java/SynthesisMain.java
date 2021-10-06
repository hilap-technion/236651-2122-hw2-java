import com.github.javaparser.ast.expr.Expression;
import makers.ASTMaker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SynthesisMain {
    public static void synthesize(List<Map<String,Integer>> contexts, int [] outputs) {
        List<String> vars = contexts.get(0).keySet().stream().collect(Collectors.toList());
        Enumerator enumerator = new Enumerator(List.of(0,1,2),vars,contexts);
        while(enumerator.hasNext()) {
            Expression p = enumerator.next();
            if (Arrays.equals(outputs,p.getData(ASTMaker.VALUES))){
                System.out.println(PrintWithParens.toString(p));
                return;
            }
        }
    }
    public static void main(String [] args) {
        //can read these from a file...
        List<Map<String,Integer>> inputs = List.of(Map.of("x",1,"y",2),
                Map.of("x",11,"y",20));
        int [] outputs = new int[] {3,31}; // looking for x + y
        //int [] outputs = new int []{3,221}; // looking for x * y + 1
        synthesize(inputs,outputs);

    }
}
