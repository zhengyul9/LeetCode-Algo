Floyd-Warshall algorithm is ez to implement and running fast in practical. 

// order of k, i and j CANNOT be changed if the relaxtion is [i][j] > [i][k] + [k][j]
    for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                        disTo[i][j] = disTo[i][k] + disTo[k][j];
                }
            }   
     }

Time Complexity:
O(N^3) where do edge relaxtion for all k, i and j. 

Space complexity:
O(N^2) created a N*N array with maximum value of Infinity