package infixtopostfix;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main
{
    //функция для возврата приоритета данного оператора
    public static int precedence(char c)
    {
        if (c == '*' || c == '/') {
            return 3;
        }
        else if (c == '+' || c == '-') {
            return 4;
        }
        else if (c == '&') {
            return 8;
        }
        else if (c == '^') {
            return 9;
        }
        else if (c == '|') {
            return 10;
        }
        return Integer.MAX_VALUE;            // для открывающей скобки '('
    }

    //функция для проверки, является ли данный токен операндом
    public static boolean isOperand(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }

    public static String infixToPostfix(String infix)
    {
        //stack для хранения операторов
        Deque<Character> s = new ArrayDeque<>();
        //string для хранения постфиксного выражения
        StringBuilder postfix = new StringBuilder();

        for (char c: infix.toCharArray())
        {
            // Если текущий токен является открывающей скобкой '(', помещаем его в stack
            if (c == '(') {
                s.push(c);
            }
            // Если текущий токен является закрывающей скобкой ')', извлекаем токены из stack до соответствующего открытия
            else if (c == ')')
            {
                // Добавляем каждый оператор в конце
                while (s.peek() != null && s.peek() != '(') {
                    postfix.append(s.pop());
                }
                s.pop();
            }

            // Если текущий токен является операндом, добавляем его в конец постфиксного выражения
            else if (isOperand(c)) {
                postfix.append(c);
            }

            // Если текущий токен является оператором удаляем из stack операторы с более высоким или равным приоритетом
            else {
                //добавляем операторы в конец постфиксного выражения
                while (!s.isEmpty() && precedence(c) >= precedence(s.peek())) {
                    postfix.append(s.pop());
                }
                s.push(c);
            }
        }

        // добавляем все оставшиеся операторы в stack в конце постфиксного выражения
        while (!s.isEmpty()) {
            postfix.append(s.pop());
        }
        return postfix.toString();
    }

    public static boolean contains(char[] chars, char ch) {
        for (char element : chars) {
            if (element == ch)
                return true;
        }

        return false;
    }

    //функция для проверки формата выражения
    public static boolean isValidInput(String userInput){
        Deque<Character> s = new ArrayDeque<>();
        char[] oper = {'+', '-', '*', '/','|', '&', '^'};
        int sLength = userInput.length();
        if (contains(oper, userInput.charAt(0)) || contains(oper, userInput.charAt(sLength-1))) {
            //Если выражение начинаетя или заканчивается с оператоа то формат неправильный
            return false;
        }

        for (int i = 0; i < userInput.length(); i++){

            char curChar = userInput.charAt(i);
            //если текущий curChar = '(' добавляем в стек
            if (curChar == '(') s.push(curChar);
            else if (curChar == ')'){
                //если текущий curChar = '(' удалаем его из стека
                //Если стек пустой формат выражения неправильный
                if (s.isEmpty()) {
                    return false;
                }
                else s.pop();
            }

            //Если 2 или больше оператора будут находится друг за другом добавляем их в стек
            //следовательно формат выражения будет неправильным
            if (i != sLength-1 && contains(oper, curChar) && contains(oper, userInput.charAt(i+1))){
                return false;
            }
        }
        //Если стек пустой, то формат выражения правильный
        return (s.isEmpty());
    }

    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args)
    {
        Scanner rl = new Scanner(System.in);
        logger.info("Введите выражение в инфиксной форме: ");
        String infix;
        infix = rl.nextLine();

        while (!isValidInput(infix)){
            logger.warning("неправильный формат");
            logger.info("Введите выражение в инфиксной форме: ");
            infix = rl.nextLine();
        }
        String postfix = infixToPostfix(infix.replaceAll("\\s",""));
        logger.info("выражение в постфиксной форме: ");
        logger.info(postfix);
    }
}