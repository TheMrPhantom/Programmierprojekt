package de.test.uni_stuttgart;

class Main{
    public static void main(String[] args) {
        GraphReader r = new GraphReader();
        r.readData("germany.fmi");
        r.readDataFast("germany.fmi");
        //germany.fmi
        //stgtregbz.fmi
        //bw.fmi
    }
}