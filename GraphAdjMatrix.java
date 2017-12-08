public class GraphAdjMatrix implements Graph {
    private int[][] matrix;
    private int size;

    public GraphAdjMatrix(int size) {
        this.size = size;
        matrix = new int[size][size];
    }

    @Override
    public void addEdge(int v1, int v2) {
    }

    @Override
    public void topologicalSort() {
    }

    @Override
    public void addEdge(int v1, int v2, int weight) {
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size){
            return;
        }
        matrix[v1][v2] = weight;
        matrix[v2][v1] = weight;
    }

    @Override
    public int getEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size){
            return 0;
        }
        return matrix[v1][v2];
    }

    @Override
    public int createSpanningTree() {
        boolean isVisited[] = new boolean[size];
        int newMat[][] = new int[size][size];
        int dis[] = new int[size];
        int pre[] = new int[size];
        for (int i = 0; i < size; i++) {
            dis[i] = Integer.MAX_VALUE;
            pre[i] = -1;
        }

        int result = 0;
        dis[0] = 0;
        for (int i = 0; i < size; i++) {
            int minIndex = -1;
            int minVal = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (!isVisited[j] && dis[j] < minVal){
                    minVal = dis[j];
                    minIndex = j;
                }
            }
            if (minVal == Integer.MAX_VALUE){
                return 0;
            }
            result += minVal;
            isVisited[minIndex] = true;
            if (pre[minIndex] >= 0){
                newMat[minIndex][pre[minIndex]] = matrix[minIndex][pre[minIndex]];
                newMat[pre[minIndex]][minIndex] = matrix[minIndex][pre[minIndex]];
            }

            for (int j = 0; j < size; j++) {
                if (!isVisited[j] && matrix[j][minIndex] > 0 && dis[j] > matrix[j][minIndex]){
                    dis[j] = matrix[j][minIndex];
                    pre[j] = minIndex;
                }
            }
        }

        matrix = newMat;
        return result;
    }
}
