import java.util.Stack;
public class InaSufij2 {
    public static String InaSufij(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder posfij = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char token = infix.charAt(i);
            switch (token) {
                case '(':
                    stack.push(token);
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        posfij.append(stack.pop());
                    }
                    stack.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                        posfij.append(stack.pop());
                    }
                    stack.push(token);
                    break;
                default:
                    posfij.append(token);
            }
        }
        while (!stack.isEmpty()) {
            posfij.append(stack.pop());
        }
        return posfij.toString();
    }
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
    public static void main(String[] args) {
        String infij = "(a + b) * c";
        String posfij = InaSufij(infij);
        System.out.println(posfij);
    }
}