package de.test.uni_stuttgart;

class Main{
    public static void main(String[] args) {
        //germany.fmi
        //stgtregbz.fmi
        //bw.fmi
        
        if (args.length != 1) {
            System.err.print("Invalid amount of arguments - ");
            System.err.println((args.length > 1) ? "only one argument is allowed" : "one argument is required");
            System.exit(1);
        }
        GraphReader r = new GraphReader(new File(args[0]));
        r.readData();
    }
}