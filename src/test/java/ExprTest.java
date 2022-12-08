import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExprTest {
    private static List<String> sign=new ArrayList<>();

    static {
        sign.add("+");
        sign.add("-");
        sign.add("*");
        sign.add("/");
    }

    public static void main(String[] args) {
        String[] arrs={"2", "3", "4", "+", "*"};
        System.out.println(evalRPN(arrs));  //14

    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> mathStack=new Stack<>();
        for (int i = 0; i <tokens.length ; i++) {
            String s=tokens[i];
            if(sign.contains(s)){
                Integer sum=0;
                Integer num2=mathStack.pop();
                Integer num1=mathStack.pop();
                if("+".equals(s)){
                    sum=num1+num2;
                }else if("-".equals(s)){
                    sum=num1-num2;
                }else if("*".equals(s)){
                    sum=num1*num2;
                }else if("/".equals(s)){
                    sum=num1/num2;
                }
//                System.out.println(num1+s+num2+"="+sum);
                mathStack.push(sum);
            }else {
                mathStack.push(Integer.parseInt(s));
            }
        }

        return mathStack.pop();
    }

}
