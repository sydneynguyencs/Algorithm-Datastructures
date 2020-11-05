package Praktikum_02_Code;

import Praktikum_01_Code.CommandExecutor;
import Praktikum_01_Code.ListStack;

import java.util.HashMap;
import java.util.Map;

public class BracketServer implements CommandExecutor {
    private ListStack stack;
    private ListStack stackCheck;

    public BracketServer() {
        stack = new ListStack();
        stackCheck = new ListStack();
    }

    public boolean checkBrackets(String s) {
        char[] array = s.toCharArray();
        char[] symbols = {'<', '>', '(', ')', '[', ']', '{', '}'};
        Map<String, String> symbolMap = new HashMap<>();
        symbolMap.put("<*", "*>");
        symbolMap.put("<", ">");
        symbolMap.put("(", ")");
        symbolMap.put("{", "}");
        symbolMap.put("[", "]");

        //fill brackets from array into stack
        for(int i = 0; i < array.length; i++) {
            if(array[i] == '*' && stack.peek().equals("<")) {
                stack.pop();
                stack.push("<*");
            }
            boolean doubleBracket = array[i] == '>' && array[i - 1] == '*';
            if(doubleBracket) {
                stack.push("*>");
            }
            for (char symbol : symbols) {
                if (array[i] == symbol && !(doubleBracket)) {
                    stack.push(Character.toString(array[i]));
                }
            }
        }

        while(!stack.isEmpty()) {
             String currentBracket = stack.pop();
            for(String key : symbolMap.values()) {
                if(currentBracket.equals(key)) {
                    stackCheck.push(key);
                }
            }
            for(String value : symbolMap.keySet()) {
                if(currentBracket.equals(value)) {
                    if (stackCheck.isEmpty()) {
                        return false;
                    }
                    if (stackCheck.peek().equals(symbolMap.get(value))) {
                        stackCheck.pop();
                    } else {
                        stackCheck.push(value);
                    }
                }
            }
        }
        boolean result = stackCheck.isEmpty();
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
