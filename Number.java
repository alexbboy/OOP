package com.company;

import java.util.Objects;

public final class Number {
    private String numb;
    private String type;


    Number(String numb,String type){
        this.numb=new String();
        this.numb=numb;
        this.type=new String();
        this.type=type;
        if((type.equals("Home")==false)&&(type.equals("Mobile")==false)&&(type.equals("Work")==false))
            throw new RuntimeException("The only possible types: Home, Mobile, Work");
    }

    public String getNumb() {
        return numb;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Number)) return false;
        Number number = (Number) o;
        return Objects.equals(numb, number.numb) &&
                Objects.equals(type, number.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numb, type);
    }
}
