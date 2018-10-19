import java.io.File;

class Main{
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Invalid amount of arguments - only one argument is allowed");
            System.exit(1);
        }
        File f = new File(args[0]);
        if(f.isFile()){
            System.out.println("File found");
            GraphReader r = new GraphReader(f);
            r.readData();
        } else {
            System.err.println("File does not exist or is a directory");
            System.exit(1);
        }
    }
}