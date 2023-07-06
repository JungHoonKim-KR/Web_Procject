// package homework;

// import java.io.*;

// class Calc {
// int token;
// int pretoken;
// int value;
// int ch;
// int check;
// private PushbackInputStream input;
// final int NUMBER = 256;

// Calc(PushbackInputStream is) {
// input = is;
// }

// int getToken() { /* tokens are characters */
// while (true) {
// try {
// ch = input.read();
// if (ch == ' ' || ch == '\t' || ch == '\r')
// ;
// else if (Character.isDigit(ch)) {
// value = number();
// input.unread(ch);
// return NUMBER;
// } else
// return ch;
// } catch (IOException e) {
// System.err.println(e);
// }
// }
// }

// private int number() {
// /* number -> digit { digit } */
// int result = ch - '0';
// try {
// ch = input.read();
// while (Character.isDigit(ch)) {
// result = 10 * result + ch - '0';
// ch = input.read();
// }
// } catch (IOException e) {
// System.err.println(e);
// }
// return result;
// }

// void error() {
// System.out.printf("syntax error");
// System.exit(1);
// }

// void match(int c) {
// if (token == c)
// token = getToken();
// else
// error();
// }

// void command() {
// /* command -> expr '\n' */
// int result = expr(); // Need to be changed

// if (result == 1 && check >= 1)
// System.out.println("True");

// else if (result == 0 && check >= 1)
// System.out.println("False");

// else if (check == 0)
// System.out.println(result);

// else if (token == '\n') /* end the parse and print the result */
// System.out.println(result);

// else
// error();
// }

// int expr() {
// int result;

// if (token == '!') {
// match('!');

// return expr();
// } else if (token == 't') {
// match('t');
// match('r');
// match('u');
// match('e');

// return 1;
// } else if (token == 'f') {
// match('f');
// match('a');
// match('l');
// match('s');
// match('e');

// return 0;
// } else {
// result = bexp();
// int a;
// while (token == '&' || token == '|') {

// if (token == '&') {
// match('&');
// a = bexp();
// } else if (token == '|') {
// match('|');
// a = bexp();
// }
// }
// return result;

// }
// }

// int bexp() {

// int result = aexp();
// int a = 0;

// if (token == '=') {
// match('=');
// match('=');
// check += 1;
// a = aexp();

// if (result == a)
// return 1;
// else
// return 0;
// } else if (token == '!') {
// match('!');
// match('=');
// check += 1;

// a = aexp();
// if (result != a)
// return 1;
// else
// return 0;
// } else if (token == '>') {
// match('>');
// check += 1;

// if (token == '=') {
// match('=');
// a = aexp();
// if (result >= a)
// return 1;
// else
// return 0;
// } else if (token != '=') {
// a = aexp();
// if (result > a) {
// return 1;
// } else
// return 0;
// }

// } else if (token == '<') {
// match('<');
// check += 1;

// if (token == '=') {
// match('=');
// a = aexp();

// if (result <= a)
// return 1;
// else
// return 0;
// } else if (token != '=') {
// a = aexp();
// if (result > a)
// return 1;
// else
// return 0;
// }
// }
// return result;
// }

// int aexp() {
// /* expr -> term { '+' term } */
// int result = term();
// while (token == '+' || token == '-') {
// if (token == '+') {
// match('+');
// result += term();
// } else if (token == '-') {
// match('-');
// result -= term();

// }
// }
// return result;
// }

// int term() {
// /* term -> factor { '*' factor } */
// int result = factor();
// while (token == '*' || token == '/') {
// if (token == '*') {
// match('*');
// result *= factor();
// } else if (token == '/') {
// match('/');
// result /= factor();
// }
// }
// return result;
// }

// int factor() {
// /* factor -> '(' expr ')' | number */

// int result = 0;
// int m = 0;

// if (token == '-') {
// match('-');

// m = 1;
// }
// if (token == '(') {
// match('(');
// result = aexp();
// match(')');

// } else if (NUMBER == token) {
// if (m == 0) {

// result = value;
// } else
// result = (-value);

// match(NUMBER);
// }

// return result;
// }

// void parse() {
// token = getToken(); // get the first token
// command(); // call the parsing command
// }

// public static void main(String args[]) {
// Calc calc = new Calc(new PushbackInputStream(System.in));
// while (true) {
// System.out.print(">> ");
// calc.parse();
// }
// }
// }