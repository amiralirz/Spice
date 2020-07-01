import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Circuit_Simulation {
    class node{
        ArrayList<element> connections = new ArrayList<element>();
        float voltage;
        boolean is_ground = false;
    }
    class element{
        int n1,n2;
    }
    class branch{
        ArrayList<node> connections = new ArrayList<node>();
        String name;

    }
    class circuit{
        ArrayList<node> nodes = new ArrayList<node>();
        ArrayList<element> elements = new ArrayList<element>();
    }
    /*********************elements*********************/
    {
        class resistor extends element{

        }
        class capacitor extends element{

        }
        class inductor extends element{

        }
    }

    public static void main(String[] args){
        boolean test = false;
        File f;
        if(!test){
            f = new File("sample_input.txt");
        }
        else {
            f = new File("sample_input.txt");
        }
        Scanner con;
        try {
            con = new Scanner(f);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        String input1 = "";
        //v : independent voltage source -- i : independent current source r : resistor
        switch (input1.charAt(0)){

        }
    }
}
