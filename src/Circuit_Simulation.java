import java.util.ArrayList;

public class Circuit_Simulation {
    class element{
        int n1,n2;
    }
    class node{
        ArrayList<element> connections = new ArrayList<element>();
        float voltage;
        boolean is_ground = false;
    }
    class circuit{
        ArrayList<node> nodes = new ArrayList<node>();
        ArrayList<element> elements = new ArrayList<element>();
    }
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}
