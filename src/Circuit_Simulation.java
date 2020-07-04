import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 - getting file input
 - reading file input
 simulating & output


 */

public class Circuit_Simulation {
    static class node{
        ArrayList<element> connections = new ArrayList<element>();
        double voltage;
        boolean is_ground = false;
        String name;
    }
    static class element{
        String name;
        int n1,n2,b;
        double voltage;
    }
    static class branch{
        ArrayList<node> connections = new ArrayList<node>();
        String name;

    }
    /*********************elements*********************/
        static class resistor extends element{
            double r;
            void vu(int Current){

            }
            void cu(int Voltage){

            }
        }

        static class capacitor extends element{
            double c;
            int q1,q2;
            void vu(int Current,int dt){

            }
            void cu(int Voltage){

            }
        }

        static class inductor extends element{
            double l;
            int f1,f2;
            void vu(int Current, int dt){

            }
        }

        static class voltage_Source extends element{
            double Voltage;
        }

        static class current_Source extends element{
            double Current;
        }

        static class diode extends element{
            int direction;
            boolean alone_Element_in_Branch;
            String model;
            double[] parameters;
        }

        static class voltage_controlled_voltage_source extends element{
            int r1,r2;
            double gain;
        }

        static class current_controlled_voltage_source extends element{
            int r1,r2;
            double gain;
        }

        static class voltage_controlled_current_source extends element{
            int r1,r2;
            double gain;
        }

        static class current_controlled_current_source extends element{
            int r1,r2;
            double gain;
        }

    static class circuit{

        /********************Variables*********************/

        boolean test = false;
        boolean has_file = false;
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
                file = new File("sample_input");
                has_file = true;
                System.out.println("Testing : ");
            }
        }

        static boolean is_number(char s){
            if(s<='9' && s>='0'){
                return true;
            }
            return false;
        }

        static double get_number(String s){
            int i = 0;
            for (i = 0;i<s.length();i++){
                if(!is_number(s.charAt(i)))
                    break;
            }
            double output = Double.parseDouble(s.substring(0,i));
            if (i!=s.length()){
                switch (s.charAt(i)){
                    case 'm':
                        output /= 1000;
                        break;
                    case 'u':
                        output /= 1000000;
                        break;
                    case 'k':
                        output *= 1000;
                        break;
                    case 'M':
                        output *= 1000000;
                        break;
                }
            }
            return output;
        }

        void read_file(){
            try {
                Scanner fr = new Scanner(file);
                title = fr.nextLine();
                while (fr.hasNext()) {
                    String in = fr.next();
                    switch (in.charAt(0)) {
                        case 'v': {
                            voltage_Source v = new voltage_Source();
                            v.name = in.substring(1, in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                v.n1 = nodes.size() - 1;
                            } else {
                                v.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                v.n2 = nodes.size() - 1;
                            } else {
                                v.n2 = f;
                            }
                            v.Voltage = get_number(fr.nextLine().trim());
                            elements.add(v);
                        }
                        break;
                        case 'i': {
                            current_Source i = new current_Source();
                            i.name = in.substring(1, in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                i.n1 = nodes.size() - 1;
                            } else {
                                i.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                i.n2 = nodes.size() - 1;
                            } else {
                                i.n2 = f;
                            }
                            i.Current = get_number(fr.nextLine().trim());
                            elements.add(i);

                        }
                        break;
                        case 'r': {
                            resistor r = new resistor();
                            r.name = in.substring(1, in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                r.n1 = nodes.size() - 1;
                            } else {
                                r.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                r.n2 = nodes.size() - 1;
                            } else {
                                r.n2 = f;
                            }
                            r.r = get_number(fr.nextLine().trim());
                            elements.add(r);

                        }
                        break;
                        case 'c': {
                            capacitor c = new capacitor();
                            c.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                c.n1 = nodes.size() - 1;
                            } else {
                                c.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                c.n2 = nodes.size() - 1;
                            } else {
                                c.n2 = f;
                            }
                            c.c = get_number(fr.nextLine().trim());
                            elements.add(c);

                        }
                        break;
                        case 'l': {
                            inductor l = new inductor();
                            l.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                l.n1 = nodes.size() - 1;
                            } else {
                                l.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                l.n2 = nodes.size() - 1;
                            } else {
                                l.n2 = f;
                            }
                            l.l = get_number(fr.nextLine().trim());
                            elements.add(l);

                        }
                        break;
                        case 'd': {
                            diode d = new diode();
                            d.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                d.n1 = nodes.size() - 1;
                            } else {
                                d.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                d.n2 = nodes.size() - 1;
                            } else {
                                d.n2 = f;
                            }
                            d.model = fr.next();
                            String[] tokens = fr.nextLine().trim().split("\\s");
                            d.parameters = new double[tokens.length];
                            System.out.print("parameters : ");
                            for (int i = 0;i<tokens.length;i++){
                                d.parameters[i] = get_number(tokens[i]);
                                System.out.print(" - " + d.parameters[i]);
                            }
                            System.out.println();
                            elements.add(d);
                        }
                        break;
                        case 'E': {
                            voltage_controlled_voltage_source cs = new voltage_controlled_voltage_source();
                            cs.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n1 = nodes.size() - 1;
                            } else {
                                cs.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n2 = nodes.size() - 1;
                            } else {
                                cs.n2 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r1 = nodes.size() - 1;
                            } else {
                                cs.r1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r2 = nodes.size() - 1;
                            } else {
                                cs.r2 = f;
                            }
                            cs.gain = get_number(fr.nextLine().trim());
                            elements.add(cs);
                        }
                        break;
                        case 'H': {
                            current_controlled_voltage_source cs = new current_controlled_voltage_source();
                            cs.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n1 = nodes.size() - 1;
                            } else {
                                cs.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n2 = nodes.size() - 1;
                            } else {
                                cs.n2 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r1 = nodes.size() - 1;
                            } else {
                                cs.r1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r2 = nodes.size() - 1;
                            } else {
                                cs.r2 = f;
                            }
                            cs.gain = get_number(fr.nextLine().trim());
                            elements.add(cs);
                        }
                        break;
                        case 'G': {
                            voltage_controlled_current_source cs = new voltage_controlled_current_source();
                            cs.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n1 = nodes.size() - 1;
                            } else {
                                cs.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n2 = nodes.size() - 1;
                            } else {
                                cs.n2 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r1 = nodes.size() - 1;
                            } else {
                                cs.r1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r2 = nodes.size() - 1;
                            } else {
                                cs.r2 = f;
                            }
                            cs.gain = get_number(fr.nextLine().trim());
                            elements.add(cs);
                        }
                        break;
                        case 'F': {
                            current_controlled_current_source cs = new current_controlled_current_source();
                            cs.name = in.substring(1,in.length());
                            String node_name = fr.next();
                            int f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n1 = nodes.size() - 1;
                            } else {
                                cs.n1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.n2 = nodes.size() - 1;
                            } else {
                                cs.n2 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r1 = nodes.size() - 1;
                            } else {
                                cs.r1 = f;
                            }
                            node_name = fr.next();
                            f = get_node(node_name);
                            if (f == -1) {
                                node n = new node();
                                n.name = node_name;
                                nodes.add(n);
                                cs.r2 = nodes.size() - 1;
                            } else {
                                cs.r2 = f;
                            }
                            cs.gain = get_number(fr.nextLine().trim());
                            elements.add(cs);
                        }
                        break;
                        default:{
                            fr.nextLine();
                        }break;
                    }
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
        c1.read_file();

        System.out.println("Elements : ");
        for(int i = 0;i<c1.elements.size();i++){
            System.out.println(" " + c1.elements.get(i).name);
        }
        System.out.println("Nodes : ");
        for(int i = 0;i<c1.nodes.size();i++){
            System.out.println(" " + c1.nodes.get(i).name);
        }

    }
}
