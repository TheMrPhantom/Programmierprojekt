import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class GraphReader{

    private int[] indices;
    private double[] coordinates;
    private int[] edges;
    File file;

    public GraphReader(File f){
        this.file = f;
    }
    public void readData(){
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(file));
            System.out.println("Started reading");
            long time = System.nanoTime();
            
            for(int i = 0; i < 5; i++){
                br.readLine();
            }
            int numOfNodes = Integer.parseInt(br.readLine());
            int numOfEdges = Integer.parseInt(br.readLine());
            indices = new int[numOfNodes];
            coordinates = new double[2 * numOfNodes];
            edges = new int[3 * numOfEdges];

            String[] sArr;
            for(int i = 0; i < numOfNodes; i++){
                sArr = br.readLine().split(" ");
                indices[i] = i;
                //latitude
                coordinates[i * 2 + 0] = Double.parseDouble(sArr[2]);
                //longitude
                coordinates[i * 2 + 1] = Double.parseDouble(sArr[3]);
            }

            for(int i = 0; i < numOfEdges; i++){
                sArr = br.readLine().split(" ");
                //Source ID
                edges[i * 3 + 0] = Integer.parseInt(sArr[0]);
                //Target ID
                edges[i * 3 + 1] = Integer.parseInt(sArr[1]);
                //Costs 
                edges[i * 3 + 2] = Integer.parseInt(sArr[2]);
            }

            System.out.println("Finished reading. Time elapsed: " + (double) (System.nanoTime() - time) / 1000000000f);
            
        } catch (Exception e){
            System.out.println("Input failed");
        }

    }

    public int[] getIndices() {
        return this.indices;
    }

    public double[] getCoordinates(){
        return this.coordinates;
    }

    public int[] getEdges(){
        return this.edges;
    }
}