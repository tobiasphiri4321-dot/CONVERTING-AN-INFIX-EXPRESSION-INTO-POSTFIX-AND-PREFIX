import java.util.Stack;

public class infix {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // INFIX TO POSTFIX
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            // If '(', push
            else if (c == '(') {
                stack.push(c);
            }
            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            // Operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop remaining
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // INFIX TO PREFIX
    static String infixToPrefix(String exp) {
        // Reverse expression
        StringBuilder input = new StringBuilder(exp);
        input.reverse();

        // Replace brackets
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                input.setCharAt(i, ')');
            else if (input.charAt(i) == ')')
                input.setCharAt(i, '(');
        }

        // Convert to postfix
        String postfix = infixToPostfix(input.toString());

        // Reverse postfix → prefix
        StringBuilder prefix = new StringBuilder(postfix);
        return prefix.reverse().toString();
    }

    public static void main(String[] args) {
        String exp = "A+B*(C-D)";

        System.out.println("Infix   : " + exp);
        System.out.println("Postfix : " + infixToPostfix(exp));
        System.out.println("Prefix  : " + infixToPrefix(exp));
    }
}
