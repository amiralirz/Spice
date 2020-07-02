import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Circuit_Simulation {
    static class node{
        ArrayList<element> connections = new ArrayList<element>();
        float voltage;
        boolean is_ground = false;
        String name;
    }
    static class element{
        String name;
        int n1,n2,b,voltage;
    }
    static class branch{
        ArrayList<node> connections = new ArrayList<node>();
        String name;

    }
    /*********************elements*********************/
        static class resistor extends element{
            int r;
            void vu(int Current){

            }
            void cu(int Voltage){

            }
        }

        static class capacitor extends element{
            int c;
            int q1,q2;
            void vu(int Current,int dt){

            }
            void cu(int Voltage){

            }
        }

        static class inductor extends element{
            int l;
            int f1,f2;
            void vu(int Current, int dt){

            }
        }

        static class voltage_Source extends element{

        }

        static class current_Source extends element{

        }

        static class diode extends element{
            int direction;
            boolean alone_Element_in_Branch;
            String model;
            int[] parameters;
        }

    static class circuit{
        /********************Variables*********************/

        boolean test = false,has_file = false;
        ArrayList<node> nodes = new ArrayList<node>();
        ArrayList<element> elements = new ArrayList<element>();
        String title = "";
        File file;

        /*********************methods**********************/

        int get_node(String n){
            for (int i = 0;i<nodes.size();i++){
                if (nodes.get(i).name.equals(n))
                    return i;
            }
            return -1;
        }

        int get_element(String n){
            for (int i = 0;i<elements.size();i++){
                if (elements.get(i).name.equals(n))
                    return i;
            }
            return -1;
        }

        void get_file_input(){
            if(!test){
                file = new File("src");
                JFrame f1 = new JFrame("Input file selection");
                FileSystemView fsv = FileSystemView.getFileSystemView();
                JFileChooser jfc = new JFileChooser(file,fsv);
                int response = jfc.showOpenDialog(f1);
                if(response == JFileChooser.APPROVE_OPTION){
                    file = jfc.getSelectedFile();
                    has_file = true;
                }
            }
            else {
                file = new File("sample_input.txt");
                has_file = true;
            }
        }

        void read_file(){
            try {
                Scanner fr = new Scanner(file);
                title = fr.nextLine();
                String in = fr.next();
                switch (in.charAt(0)){
                    case 'v':{
                        voltage_Source v = new voltage_Source();
                        v.name = in.substring(1,in.length());
                        String node_name = fr.next();
                        int f = get_node(node_name);
                        if(f==-1){
                            node n = new node();
                            n.name = node_name;
                            nodes.add(n);
                        }
                    }break;
                    case 'i':{
                        current_Source i = new current_Source();
                    }break;
                    case 'r':{
                        resistor r = new resistor();
                    }break;
                    case 'c':{
                        capacitor c = new capacitor();
                    }break;
                    case 'l':{
                        inductor l = new inductor();
                    }break;
                    case 'd':{
                        diode d = new diode();
                    }break;

                }
            }
            catch (FileNotFoundException e){
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args){
        circuit c1 = new circuit();
        while (!c1.has_file)
        c1.get_file_input();
    }
}
