package Praktikum_01_Code;

public class BracketServer implements CommandExecutor {
    private ListStack stack;
    private ListStack stackCheck;

    public BracketServer() {
        stack = new ListStack();
        stackCheck = new ListStack();
    }

    public boolean checkBrackets(String s) {
        //push String s into char array
        char[] array = s.toCharArray();
        //fill brackets from array into stack
        for(char c : array) {
            if(c == '(' || c == '[' || c == '{' || c == ')' || c == ']' || c == '}') {
                stack.push(Character.toString(c));
            }
        }

        while(!stack.isEmpty()) {
            //System.out.println("Stack peek: " + stack.peek());
            switch (stack.pop()) {
                case ")":
                    stackCheck.push(")");
                    break;
                case "]":
                    stackCheck.push("]");
                    break;
                case "}":
                    stackCheck.push("}");
                    break;
                case "(":
                    if(stackCheck.peek().equals(")")) {
                        stackCheck.pop();
                    } else {
                        stackCheck.push("(");
                    }
                    break;
                case "[":
                    if(stackCheck.peek().equals("]")) {
                        stackCheck.pop();
                    } else {
                        stackCheck.push("[");
                    }
                    break;
                case "{":
                    if(stackCheck.peek().equals("}")) {
                        stackCheck.pop();
                    } else {
                        stackCheck.push("{");
                    }
                    break;
            }
        }

        //System.out.println("Stack: " + stack.getSize() + " | StackCheck: " + stackCheck.getSize());
        boolean result = stackCheck.isEmpty();
        //empty stackCheck
        stackCheck.removeAll();
        return result;
    }

    @Override
    public String execute(String command) throws Exception {
        if(checkBrackets(command)) {
            return "Brackets valid. \n";
        } else {
            return "Brackets invalid. \n";
        }
    }
}
