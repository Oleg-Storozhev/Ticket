package hillel.org.Hibernate.Enums;

public enum DirectionType {
    FROM(1), TO(2), UNKNOWN(3);

    private final int code;

    DirectionType(int code){
        this.code = code;
    }
    public int setCode(){
        return code;
    }
}
