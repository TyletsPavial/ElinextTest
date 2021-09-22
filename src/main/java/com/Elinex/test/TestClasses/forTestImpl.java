package com.Elinex.test.TestClasses;

import com.Elinex.test.Annotations.Reject;

public class forTestImpl implements forTest{
    int numb;
    public forTestImpl(){
        numb = 1;
    }
    @Reject
    public forTestImpl(SecondForTest stc){
        numb = 2;
    }
    public int getNumb(){
        return numb;
    }

    @Override
    public void print() {
        System.out.println(numb);
    }

    public void setNumb(int numb){
        this.numb = numb;
    }
}
