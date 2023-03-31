
import java.util.Scanner;

public class floyd {

    public static void minDistance(int adj[][], int vertices) {

        int i, j, count = 0;
        // Here k is acting as an intermediate node
        while (count < vertices) {
            for (i = 0; i < vertices; i++) {

                for (j = 0; j < vertices; j++) {

                    if (adj[i][count] + adj[count][j] < adj[i][j])
                        adj[i][j] = adj[i][count] + adj[count][j];
                }
            }
            count++;
        }
        display(adj, vertices);
    }

    public static void display(int adj[][], int vertices) {
        System.out.print("The following matrix shows the shortest distances between every pair of vertices \n");
        for (int i = 0; i < vertices; i++) {
            System.out.print("\t" + (i + 1));
        }
        System.out.print("\n");
        for (int i = 0; i < vertices; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < vertices; j++) {

                System.out.print(adj[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.println(
                "\nNote: If the distance between any two Vertices is 9999 then there is no path between them.");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of vertices in the graph: ");
        int vertices = sc.nextInt();

        int adj[][] = new int[vertices][vertices], i, j;
        for (i = 0; i < vertices; i++) {

            for (j = 0; j < vertices; j++) {
                System.out.print("Enter Adj[" + i + "][" + j + "]:");
                adj[i][j] = sc.nextInt();
            }
        }
        minDistance(adj, vertices);

    }

}