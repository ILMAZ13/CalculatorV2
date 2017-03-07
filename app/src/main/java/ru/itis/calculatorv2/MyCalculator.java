package ru.itis.calculatorv2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyCalculator {
    public enum State{
        div, mul, add, sub, num, emp, skob
    }
    public double getResult(String temp) throws Exception {
        int o = 0;
        int c = 0;
        for (int i = 0; i < temp.length(); i++) {
            char cTemp = ' ';
            char pTemp = ' ';
            if(i > 0){
                cTemp = temp.charAt(i-1);
            }
            if(i < temp.length()-1){
                pTemp = temp.charAt(i+1);
            }
            switch (temp.charAt(i)) {
                case '(' : o++;
                    if(cTemp >= '0' && cTemp <= '9'){
                        temp = temp.substring(0, i) + '*' + temp.substring(i, temp.length());
                        i++;
                    }
                    break;
                case ')' : c++;
                    if(pTemp >= '0' && pTemp <= '9'){
                        temp = temp.substring(0, i+1) + '*' + temp.substring(i+1, temp.length());
                    }
                    break;
            }
        }
        if(o !=c ){
            throw new Exception();
        }
        Queue<Ozp> ozp = getOZP(temp);
        return getResOfOzp(ozp);
    }

    private Queue<Ozp> getOZP(String str){
        Queue<Ozp> res = new LinkedList<>();
        char[] chars = str.toCharArray();
        String nBuf = "";
        Ozp temp;
        Stack<Ozp> texas = new Stack<>();
        texas.add(new Ozp(State.emp, 0));
        final char sub = (char) 8722;
        for(char k : chars){
            switch (k){
                case '*' :
                    if(nBuf.length() > 0){
                        res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
                        nBuf = "";
                    }
                    temp = texas.peek();
                    while (temp.state == State.mul || temp.state == State.div){
                        res.add(texas.pop());
                        temp = texas.peek();
                    }
                    texas.add(new Ozp(State.mul, 0));
                    break;
                case '/' :
                    if(nBuf.length() > 0){
                        res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
                        nBuf = "";
                    }
                    temp = texas.peek();
                    while (temp.state == State.mul || temp.state == State.div){
                        res.add(texas.pop());
                        temp = texas.peek();
                    }
                    texas.add(new Ozp(State.div, 0));
                    break;
                case '+' :
                    if(nBuf.length() > 0){
                        res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
                        nBuf = "";
                    }
                    temp = texas.peek();
                    while (temp.state != State.skob && temp.state != State.emp){
                        res.add(texas.pop());
                        temp = texas.peek();
                    }
                    texas.add(new Ozp(State.add, 0));
                    break;
                case '-' :
                    if(nBuf.length() > 0){
                        res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
                        nBuf = "";
                    }
                    temp = texas.peek();
                    while (temp.state != State.skob && temp.state != State.emp){
                        res.add(texas.pop());
                        temp = texas.peek();
                    }
                    texas.add(new Ozp(State.sub, 0));
                    break;
                case '(' :
                    if(nBuf.length() > 0){
                        res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
                        nBuf = "";
                    }
                    texas.add(new Ozp(State.skob, 0));
                    break;
                case ')' :
                    if(nBuf.length() > 0){
                        res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
                        nBuf = "";
                    }
                    temp = texas.peek();
                    while (temp.state != State.skob) {
                        res.add(texas.pop());
                        temp = texas.peek();
                    }
                    texas.pop();
                    break;
                default:
                    nBuf+=k;
            }
        }
        if(nBuf.length()>0){
            res.add(new Ozp(State.num, Double.parseDouble(nBuf)));
        }
        temp = texas.pop();
        while (temp.state != State.emp){
            res.add(temp);
            temp = texas.pop();
        }
        return res;
    }

    private double getResOfOzp(Queue<Ozp> reg){
        Stack<Double> st = new Stack<>();
        double stTemp;
        Ozp temp;
        while (!reg.isEmpty()){
            temp = reg.poll();
            switch (temp.state){
                case num:
                    st.add(temp.num);
                    break;
                case add:
                    stTemp = st.pop();
                    st.add(st.pop() + stTemp);
                    break;
                case sub:
                    stTemp = st.pop();
                    st.add(st.pop() - stTemp);
                    break;
                case mul:
                    stTemp = st.pop();
                    st.add(st.pop() * stTemp);
                    break;
                case div:
                    stTemp = st.pop();
                    st.add(st.pop() / stTemp);
                    break;
            }
        }
        double resu = st.pop();
        if(!st.empty()){
            throw new Error();
        }
        return resu;
    }

    private class Ozp{
        public State state;
        public double num;

        public Ozp(State state, double num) {
            this.state = state;
            this.num = num;
        }

    }
}
