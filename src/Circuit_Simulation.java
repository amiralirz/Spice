import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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
        String name;
        int n1,n2,b,voltage;
    }
    class branch{
        ArrayList<node> connections = new ArrayList<node>();
        String name;

    }
    /*********************elements*********************/
    {
        class resistor extends element{
            int r;
            void vu(int Current){

            }
            void cu(int Voltage){

            }
        }

        class capacitor extends element{
            int c;
            int q1,q2;
            void vu(int Current,int dt){

            }
            void cu(int Voltage){

            }
        }

        class inductor extends element{
            int l;
            int f1,f2;
            void vu(int Current, int dt){

            }
        }

        class voltage_Source extends element{

        }

        class current_Source extends element{

        }

        class diode extends element{
            int direction;
            boolean alone_Element_in_Branch;
            String model;
            int[] parameters;
        }
    }

    static class circuit{
        boolean test = false,has_file = false;
        ArrayList<node> nodes = new ArrayList<node>();
        ArrayList<element> elements = new ArrayList<element>();
        File file;
        void get_file_input(){
            if(!test){
                file = new File("D:\\Projects\\Java\\oop\\Spice");
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

    }

    public static void main(String[] args){
        circuit c1 = new circuit();
        while (!c1.has_file)
        c1.get_file_input();
    }
}
