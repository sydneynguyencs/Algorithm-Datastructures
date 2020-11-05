package Praktikum_01_Code;

public class XMLServer implements CommandExecutor {

    private ListStack stack;
    private ListStack stackCheck;
    private String word = "";


    public XMLServer() {
        stack = new ListStack();
        stackCheck = new ListStack();
    }

    @Override
    public String execute(String command) throws Exception {
        if(checkWellformed(command)) {
            return "XML well-formed. \n";
        } else {
            return "XML not well-formed. \n";
        }
    }

    public boolean checkWellformed(String arg) {
        char[] array = arg.toCharArray();
        //push tokens such as <...> and </...> on stack, skip tokens ending on <.../> and <?...>
        int i = 0;
        while(i < array.length) {
            String token = "";
            if(array[i] == '<') {
                do{
                    token+=array[i];
                    i++;
                }while(array[i]!='>');
                token+=">";
                if(!token.endsWith("/>") && !token.startsWith("<?") ) {
                    System.out.println("token to stack: "+token);
                    stack.push(token);
                }
            }
            i++;
        }

        //pop stack until stack is empty
        while(!stack.isEmpty()) {
            String token = stack.pop();
            word = getWord(token);

            if(token.startsWith("</")) {
                stackCheck.push(token);
            } else {//in case of <qanda seq="1">...</qanda>
                if(stackCheck.isEmpty()) {
                    return false;
                }
                if(stackCheck.peek().equals(getClosingTag(word))) {//closing token exists
                    stackCheck.pop();
                } else {
                    stackCheck.push(getClosingTag(word));
                }
            }
        }

        boolean result = stackCheck.isEmpty();
        stackCheck.removeAll();
        return result;
    }

    //word = token without <, >, first expression until space
    private String getWord(String expr) {
        String[] tmp = expr.split(" ");
        if (expr.startsWith("</")) {
            word = tmp[0].substring(2);
            if(tmp.length>1) {//closing token should have only one word
                exitWrongSyntax();
            }
        } else {
            word = tmp[0].substring(1);
        }
        if(word.endsWith(">")) {
            word = word.substring(0, word.length()-1);
        }
        return word;
    }

    private void exitWrongSyntax() {
        System.out.println("closing tag should have only one expression");
    }

    private String getClosingTag(String word) {
        return "</"+word+">";
    }

}
