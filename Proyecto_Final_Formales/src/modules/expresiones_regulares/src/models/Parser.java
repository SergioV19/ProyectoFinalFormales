package modules.expresiones_regulares.src.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Parser {

    private Map<Character, Integer> precedencia;
    public String aux;

    public Parser(String expression){
        Map<Character, Integer> map = new HashMap<>();

        map.put('(', 1);
        map.put('|', 2);
        map.put('+', 3);
        map.put('*', 4);
        precedencia = Collections.unmodifiableMap(map);

        setAux(convertExpression(expression));
    }

    public String convertExpression(String expression){
        Stack<Character> stack = new Stack<>();
        String postfix = "";

        for(char c : expression.toCharArray()){
            switch(c){
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while (!stack.peek().equals('('))
                    {
                        postfix += stack.pop();
                    }
                    stack.pop();
                    break;
                default:
                    while (stack.size() > 0)
                    {
                        Character peekedChar = stack.peek();
                        Integer peekedCharPrecedence = getPrecedence(peekedChar);
                        Integer currentCharPrecedence = getPrecedence(c);
                        if (peekedCharPrecedence >= currentCharPrecedence)
                        {
                            postfix += stack.pop();
                        }
                        else
                        {
                            break;
                        }
                    }
                    stack.push(c);
                    break;
            }
        }
        while (stack.size() > 0)
        {
            postfix += stack.pop();
        }
        return postfix;
    }

    int getPrecedence(char c){
        Integer precedencia_char = precedencia.get(c);
        if(precedencia_char == null)
        {
            precedencia_char = 6;
        }
        return precedencia_char;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }
}
