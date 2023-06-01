
import java.awt.geom.Line2D;
import java.util.ArrayList;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MathParser {

    String Func;

    int WIDTH;
    int HEIGHT;

    int x;
    int y;

    ArrayList<Double> resList = new ArrayList<Double>();

    MathParser(String Func, int WIDTH, int HEIGHT, int x, int y) {
        this.Func = Func;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.x = x;
        this.y = y;
    }

    public double execute(int nums, int x, int y) {
        String result = Func;
        double resValue = 0.0;

        result = result.replaceAll("\\s", "");
        result = result.replaceAll("^(\\d+)([+-/])", "$1*" + String.valueOf(y) + "$2");
        result = result.replaceAll("(\\w)([+-])(\\d+)(([+-])|$)", "$1*" + String.valueOf(y) + "/s$2$3*" + String.valueOf(y) + "$4");
        
        //^ - не доделана
        result = result.replaceAll("(?![(])x\\^(\\d)(?![)])", "((x*" + String.valueOf(y) + "/s)^($1))/" + String.valueOf(y) + "/(s)^($1-1)");
        System.out.println(result);
        result = result.replaceAll("([+-/])(\\d+)([/])[A-Z]([/])([a-z])", "$1$2$3$5");

        
        result = result.replaceAll("sin\\((\\w+)\\^(\\d+)\\)", "sin(($1/s)^$2)*" + String.valueOf(y));
        result = result.replaceAll("sin\\((\\w+)\\)", "sin($1/s)" + String.valueOf(y));
        

        result = result.replaceAll("cos\\((\\w+)\\^(\\d+)\\)", "cos(($1/s)^$2)*" + String.valueOf(y));
        result = result.replaceAll("cos\\((\\w+)\\)", "cos($1/s)*" + String.valueOf(y));

        result = result.replaceAll("tg\\((\\w+)\\^(\\d+)\\)", "tan(($1/s)^$2)*" + String.valueOf(y));
        result = result.replaceAll("tg\\((\\w+)\\)", "((sin($1/s))/(cos($1/s)))*" + String.valueOf(y));

        Expression e = new ExpressionBuilder(result)
                .variables("x", "s")
                .build()
                .setVariable("x", nums)
                .setVariable("s", x);

        double resultT = e.evaluate();

        return resultT;
    }

    public ArrayList fillList() {

        for (int i = -WIDTH / 2; i < WIDTH / 2; i++) {
            resList.add(this.execute(i, x, y));
        }
        return resList;
    }

}
