package com;

public class MinimumFallingPathSum {
    public static void main(String[] args) {
        MinimumFallingPathSum mfps = new MinimumFallingPathSum();
        System.out.println(mfps.minFallingPathSum(
                new int[][] {{2,1,3},{6,5,4},{7,8,9}}
        )); // 13
        System.out.println(mfps.minFallingPathSum(
                new int[][] {{-19,57},{-40,-5}}
        )); // -59
        System.out.println(mfps.minFallingPathSum(
                new int[][] {{100,-42,-46,-41}, {31,97,10,-10},
                    {-58,-51,82,89}, {51,81,69,-51}}
        )); // -36
        System.out.println(mfps.minFallingPathSum(
                new int[][] {{-19,-1,-96,48,-94,36,16,55,-42,37,-59,6,-32,96,95,-58,13,-34,94,85},
                            {17,44,36,-29,84,80,-34,50,-99,64,13,91,-27,25,-36,57,20,98,-100,-72},
                            {-92,-75,86,90,-4,90,64,56,50,-63,10,-15,90,-66,-66,32,-69,-78,1,60},
                        {21,51,-47,-43,-14,99,44,90,8,11,99,-62,57,59,69,50,-69,32,85,13},
                        {-28,90,12,-18,23,61,-55,-97,6,89,36,26,26,-1,46,-50,79,-45,89,86},
                        {-85,-10,49,-10,2,62,41,92,-67,85,86,27,89,-50,77,55,22,-82,-94,-98},
                        {-50,53,-23,55,25,-22,76,-93,-7,66,-75,42,-35,-96,-5,4,-92,13,-31,-100},
                        {-62,-78,8,-92,86,69,90,-37,81,97,53,-45,34,19,-19,-39,-88,-75,-74,-4},
                        {29,53,-91,65,-92,11,49,26,90,-31,17,-84,12,63,-60,-48,40,-49,-48,88},
                        {100,-69,80,11,-93,17,28,-94,52,64,-86,30,-9,-53,-8,-68,-33,31,-5,11},
                        {9,64,-31,63,-84,-15,-30,-10,67,2,98,73,-77,-37,-96,47,-97,78,-62,-17},
                        {-88,-38,-22,-90,54,42,-29,67,-85,-90,-29,81,52,35,13,61,-18,-94,61,-62},
                        {-23,-29,-76,-30,-65,23,31,-98,-9,11,75,-1,-84,-90,73,58,72,-48,30,-81},
                        {66,-33,91,-6,-94,82,25,-43,-93,-25,-69,10,-71,-65,85,28,-52,76,25,90},
                        {-3,78,36,-92,-52,-44,-66,-53,-55,76,-7,76,-73,13,-98,86,-99,-22,61,100},
                        {-97,65,2,-93,56,-78,22,56,35,-24,-95,-13,83,-34,-51,-73,2,7,-86,-19},
                        {32,94,-14,-13,-6,-55,-21,29,-21,16,67,100,77,-26,-96,22,-5,-53,-92,-36},
                        {60,93,-79,76,-91,43,-95,-16,74,-21,85,43,21,-68,-32,-18,18,100,-43,1},
                        {87,-31,26,53,26,51,-61,92,-65,17,-41,27,-42,-14,37,-46,46,-31,-74,23},
                        {-67,-14,-20,-85,42,36,56,9,11,-66,-59,-55,5,64,-29,77,47,44,-33,-77}

                }
        ));

    }
    public int minFallingPathSum(int[][] matrix) {
        int minSum = Integer.MAX_VALUE;
        for(int i=0; i<matrix[0].length;i++){
            /*minSum = Math.min(minSum,
                    minTraverse(matrix, 0, i, matrix[0][i]));*/
            minSum = Math.min(minSum,
                    minTraverseUpdated(matrix, 0, i, 0));
        }
        return minSum;
    }
    private int minTraverseUpdated(int[][] matrix, int curRow,
                            int curCol, int currSum){
        if(curRow < matrix.length - 1) {
            int nextRow = curRow + 1;
            int minSumFromHere = minTraverseUpdated(matrix, nextRow, curCol,
                    currSum+matrix[curRow][curCol]);
            if(curCol>0)
                minSumFromHere = Math.min(minSumFromHere,
                    minTraverseUpdated(matrix, nextRow, curCol-1,
                    currSum+matrix[curRow][curCol-1]));
            if(curCol<matrix[nextRow].length-1)
                minSumFromHere = Math.min(minSumFromHere,
                    minTraverseUpdated(matrix, nextRow, curCol+1,
                           currSum+matrix[curRow][curCol+1]));
            return minSumFromHere;
        }else{
            int minVal = matrix[curRow][curCol];
            if(curCol != 0){
                minVal = Math.min(minVal, matrix[curRow][curCol-1]);
            }
            if(curCol != matrix[curRow].length-1){
                minVal = Math.min(minVal, matrix[curRow][curCol+1]);
            }
            return currSum + minVal;
        }
    }
    private int minTraverse(int[][] matrix, int curRow,
                            int curCol, int currSum){
        if(curRow < matrix.length - 1) {
            int nextRow = curRow + 1;
            int nextCol = curCol;
            if(curCol != 0){
                if(matrix[nextRow][curCol-1] <
                        matrix[nextRow][curCol]){
                    nextCol = curCol - 1;
                }else if(matrix[nextRow][curCol-1] >
                        matrix[nextRow][curCol]){
                    nextCol = curCol;
                }
            }
            if(curCol < matrix[nextRow].length - 1){
                if(matrix[nextRow][nextCol] >
                        matrix[nextRow][curCol+1]){
                    nextCol = curCol+1;
                }
            }
            return minTraverse(matrix, nextRow, nextCol,
                    currSum+matrix[nextRow][nextCol]);
        }
        else{
            return currSum;
        }
    }
}
