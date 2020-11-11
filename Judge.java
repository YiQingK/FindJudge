import java.util.LinkedList; 

public class Judge
{
    public class Graph
    {
        public LinkedList<Integer>[] adj;
        int vertices;

        public Graph(int vertices)
        {
            this.vertices = vertices;
            adj = new LinkedList[vertices+1];
            for(int i =0; i <= vertices; i++)
            {
                adj[i] = new LinkedList<Integer>();
            }
        }
        public void addEdge(int a, int b)
        {
            adj[a].add(b);
        }

        public LinkedList<Integer> [] getGraph()
        {
            return adj;
        }
    }

    public int findJudge( int N, int[][] trust) throws Exception
    {
        //Check validity of n
        if(N < 1 || N > 1000)
        {
            throw new Exception("Please use an n-value between 1 and 1000.");
        }

        // Base case, only 1 person
        if ( N == 1)
        {
            return 1;
        }

        Graph graph = new Graph(N);

        LinkedList<Integer> [] adjacencylist = graph.getGraph();

        // Add trust arrows
        for (int i =0; i<trust.length; i++)
        {
            graph.addEdge(trust[i][0],trust[i][1]);
        }

        // Loop through linkedlist for possible judge (No outgoing, size = 0)
        for( int i =1; i<adjacencylist.length; i++)
        {

            if (adjacencylist[i].size() == 0) //possible canditate for judge
            {
                int possible = i;
                boolean judge = true;
            

                // Verify if judge (Everyone else contains possible)
                for (int j = 1; j<adjacencylist.length; j++)
                {
                    if(j == i)
                    {
                        continue;
                    }

                    LinkedList<Integer> trusts = adjacencylist[j];
                    if (trusts.contains(possible) == false)
                    {
                        judge = false;
                        break;
                    }
                
                }
                if(judge == true)
                {
                    return i;
                }
            }
        }
    return -1;
    }

    public static void main(String[] args)
    {
        try
        {
            //Test 1, should return 3
            int n1 = 4;
            int[][] trust1 = {{1,3},{1,4},{2,3},{2,4},{4,3}};
            Judge fj = new Judge();
            System.out.println(fj.findJudge(n1,trust1));

            //Test 2, should return -1
            int n2 = 3;
            int[][] trust2 = {{1,2},{2,3}};
            System.out.println(fj.findJudge(n2,trust2));
        }

        catch(Exception e)
        {
            //print exception message
            System.out.println(e.getMessage());
        }

    }
}