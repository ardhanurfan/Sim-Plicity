package com.simplicity.Objek;

public class ThreeElementArray <A, B, C> {
    // membuat generic dengan 3 tipe data yang dapat diterapkan pada inventory, bahan makanan, dan masakan
    private A first;
    private B second;
    private C third;

    public ThreeElementArray(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public C getThird() {
        return third;
    }

    public void setThird(C third) {
        this.third = third;
    }
}
