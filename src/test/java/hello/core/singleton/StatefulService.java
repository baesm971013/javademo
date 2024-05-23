package hello.core.singleton;

public class StatefulService {

    //stateless 무상태로 설계해라
    public int order(String name, int price){
        System.out.println(name + " " + price);
        return price;
    }

}
