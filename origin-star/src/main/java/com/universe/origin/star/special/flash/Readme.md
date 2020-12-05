
#回溯法
# 01背包问题
## 一、问题描述
给定 n 件物品，物品的重量为 w[i]，物品的价值为 c[i]。现挑选物品放入背包中，假定背包能承受的最大重量为 V，问应该如何选择装入背包中的物品，使得装入背包中物品的总价值最大
## 二、解决思路
物品是不能拆分的，首先想到的是动态规划，将背包问题分为两个子问题，求解子问题的最优解。回溯法可以解决这个问题，将所有的解罗列出来，叫做解空间，然后在每个解空间上，判断每个物品是否加入背包，每个物品相当于一个节点，加入和不加入相当于两条路，具体是看走哪条路。

在向下递归查找最优解的过程中，主要看两个函数，一个是当前路能够走，另一个就是当前路走下去是不是最优解。分别叫做限界条件、减枝函数，这两个函数的好坏影响算法的效率。
## 三、代码
```
/**
 * 01 背包问题
 */
public class BackPack {

    //最大重量
    private static int MAX_WEIGHT = 10;

    public static void main(String[] args) {
        int[] weight = new int[]{2, 5, 4, 2};
        int[] value = new int[]{6, 3, 5, 4};
        //最优情况下的加车情况
        int[] bestItemStatus = new int[value.length];
        //当前路径的加车情况
        int[] itemStatus = new int[value.length];
        System.out.println(backTrack(0, 0, bestItemStatus, itemStatus, value, weight, 0, 0));
        System.out.println(Arrays.toString(bestItemStatus));

    }


    /**
     * @param i              表示当前节点层级
     * @param bestResult     最优结果
     * @param bestItemStatus 最优结果状态
     * @param itemStatus     各个节点是否加车
     * @param value          价值列表
     * @param weight         重量列表
     * @param currentValue   当前值
     * @param currentWeight  当前重量
     */
    public static int backTrack(int i, int bestResult, int[] bestItemStatus, int[] itemStatus, int[] value, int[] weight, int currentValue, int currentWeight) {
        
        /**
         * 到达这里代表可能产生最优解，因为减枝叶函数会判断没有可能达到最优解的，路径不会继续走下去
         * 当吧最后一个节点加车之后，递归过来的i是等于长度的，所以是i> 数组下标
         */
        if (i > value.length - 1) {
            //返回最优解
            for (int j = 0; j < value.length; j++) {
                bestItemStatus[j] = itemStatus[j];
            }
            bestResult = currentValue;
            return bestResult;
        }
        /**
         * 这里是限界条件如果符合加车则加车，否则走下面的回溯然后走不加车，相当于两个选择
         */
        if (currentWeight + weight[i] <= MAX_WEIGHT) {
            //当前物品走加车状态
            itemStatus[i] = 1;
            currentValue += value[i];
            currentWeight += weight[i];

            //进入下一节点的判断，计算当前值加上下一节点之后的所有的可能值的最优解 如果能走到叶子节点，会将最优结果记录下来
            bestResult = backTrack(i + 1, bestResult, bestItemStatus, itemStatus, value, weight, currentValue, currentWeight);

            // 当前节点回溯，代表当前节点不再加入车，进行下面的判断，剩余物品除去当前节点能否得到最优解
            currentValue -= value[i];
            currentWeight -= weight[i];
        }

        /*
         * 判断如果当前节点不加入车，把剩下节点加车，总价值是否大于最优价值，如果大于则可能会获取到最优解，则继续扩展右子树
         * 这里是一个终结点，判断如果不把当前物品加入车，剩余的物品满足组成最优解的条件，如果实际组成的解没有当前的解好，会被这里剪枝
         * 可以优化剪枝函数，提升算法效率
         */
        if (bound(i + 1, currentValue, value) > bestResult) {
            itemStatus[i] = 0;
            bestResult = backTrack(i + 1, bestResult, bestItemStatus, itemStatus, value, weight, currentValue, currentWeight);
        }
        return bestResult;
    }

    /**
     * 计算剩余物品价值
     *
     * @param i
     * @param currentValue
     * @param value
     * @return
     */
    public static int bound(int i, int currentValue, int[] value) {
        for (int j = i; j < value.length; j++) {
            currentValue += value[j];
        }
        return currentValue;
    }
    
}
```


#最大团问题
##四、优化空间
优化的方向主要是限界条件和剪枝函数，尽可能避免无用的路径


##一、问题描述
设一个无向图G ( V , E ) G(V,E)G(V,E)，V VV为点集，E EE为两点间的边集。设U UU为V VV的一个子集，若对于任意的结点对u uu,v vv属于U UU都有边连通，则称点集U构成的图为 完全子图 。无向图G GG的完全子图U UU是G GG的团，G GG的最大团即为G GG的最大完全子图。

##二、解决思路
可以将节点选择加入和不加入当期那的子图，加入和不加入分别是两种道路，并且节点的加入不加入没有优先级，所以这里可以先排列节点的选择顺序，因为顺序不影响最终的结果，使用临接矩阵存储节点之间的边的关系。其中限界函数查找临接矩阵中是否存在边，不存在则代表当前节点只能走不加入的道路，然后向下递归不加入的道路。剪枝函数可以设定为判定如果走不加入的道路剩余的节点是否大于当前最优值，也就最优解产生的可能性，可能性为0则不走当前的道路。具体搜索过程如下


##三、代码
```
public class MaximumGroup {
    public static void main(String[] args) {
        //邻接矩阵存储图 0代表相连否则不相连
        int[][] adjacencyMatrix = new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0}};
        //保存当前的节点加入状态
        int[] currentStatus = new int[adjacencyMatrix.length];
        //保存最好结果的节点加入状态
        int[] bestStatus = new int[adjacencyMatrix.length];
        //代表最好结果的人数
        int bestValue = maxCompletelyGroup(adjacencyMatrix, 0, currentStatus, bestStatus, 0, 0);
        System.out.println(bestValue);
        System.out.println(Arrays.toString(bestStatus));


    }

    public static Integer maxCompletelyGroup(int[][] adjacencyMatrix, int i, int[] currentStatus, int[] bestStatus, int bestVale, int currentValue) {

        /**
         * 递归的终点，最优解一定是到达叶子节点的
         */
        if (i > adjacencyMatrix.length - 1) {
            //记录最优解
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                bestStatus[j] = currentStatus[j];
            }
            bestVale = currentValue;
            return bestVale;
        }


        /**
         * 节点判断阶段，如果满足加入条件则加入，递归下个节点，拿到最优值后进行回溯，选择不加入这个节点
         */
        if (isAccordWithJoin(adjacencyMatrix, i, currentStatus)) {
            //代表可以加入
            currentStatus[i] = 1;
            //当前值加一
            currentValue += 1;

            //向下递归节点
            bestVale = maxCompletelyGroup(adjacencyMatrix, i + 1, currentStatus, bestStatus, bestVale, currentValue);

            //将当前节点回溯走下面的判断
            currentValue -= 1;
        }

        /**
         * 减枝函数 进行粗略判断下面的能否加入
         * 假设剩下的节点都能加入，则判断和当前最优值的大小，否则则没必要向下扩展
         */
        if ((currentValue + adjacencyMatrix.length - i - 1) > bestVale) {
            //表示当前节点走不加入的道路，递归计算不加入情况下剩余节点和已加入节点的值
            currentStatus[i] = 0;
            bestVale = maxCompletelyGroup(adjacencyMatrix, i + 1, currentStatus, bestStatus, bestVale, currentValue);
        }


        return bestVale;
    }

    /**
     * 限界条件
     * 判断当前节点是否能够加入
     * 循环截止到当前顶点，如果有不相连的则不能加入
     *
     * @param adjancencyMatrix
     * @param i
     * @param currentStatus
     * @return
     */
    public static boolean isAccordWithJoin(int[][] adjancencyMatrix, int i, int[] currentStatus) {
        for (int j = 0; j < i; j++) {
            //判断当前节点如果在子图中则判断是否和i有边，没有false
            if (currentStatus[j] == 1) {
                if (adjancencyMatrix[j][i] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
```
##四、优化空间

#地图着色问题
##一、问题描述
地图上某些区域挨着，挨着的区域不能是相同的颜色，如果使用m中材料对地图着色，共有多少种着色方式。
##二、解决思路
地图可以看做是图，地图上的位置相当于图上的点，如果挨着代表点与点之间有边，否则没有，使用回溯发，确定限界条件个剪枝函数，不过当前问题是求解所有的方案，所以没有必要需要剪枝函数。当问题到达子节点则代表是一个可行解。到达之后进行回溯，求解其他的方案。

##三、代码
```
public class MapColoring {

    public static void main(String[] args) {
        //邻接矩阵存储图 0代表相连否则不相连
        int[][] adjacencyMatrix = new int[][]{
                {0, 1, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0, 1, 1},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 1, 1, 0},
        };
        //记录所有的结果
        int[][] allResult = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        int[] currentValue = new int[adjacencyMatrix.length];
        coloring(adjacencyMatrix, allResult, 0, currentValue, 3, 0);
        for (int i = 0; i < allResult.length; i++) {
            System.out.println(Arrays.toString(allResult[i]));
        }


    }

    /**
     * 地图着色
     *
     * @param adjacencyMatrix 邻接矩阵
     * @param allResult       所有的结果集
     * @param i               当前点
     */
    public static int coloring(int[][] adjacencyMatrix, int[][] allResult, int i, int[] currentValue, int color, int resultIndex) {

        /**
         * 判断当前节点的着色
         * 循环已经着色的点 ,如果相连则判断能否着色当前颜色
         * 循环颜色
         */

        if (i > adjacencyMatrix.length - 1) {
            //将当前结果保存到全部结果中
            for (int j = 0; j < currentValue.length; j++) {
                allResult[resultIndex][j] = currentValue[j];
            }
            resultIndex += 1;
            return resultIndex;
        }

        for (int j = 1; j <= color; j++) {
            // 记录当前节点的颜色尝试
            if (isOk(adjacencyMatrix, j, currentValue, i)) {
                // 当前节点染色j
                currentValue[i] = j;
                //向下递归 如果这条路最终能走到叶子节点，则会加入到allResult
                resultIndex = coloring(adjacencyMatrix, allResult, i + 1, currentValue, color, resultIndex);
                //由于这里是for循环尝试其他的道路，所以不用手动回溯，下次currentValue[i] = j;  会将本次循环值覆盖，直到达到叶子节点保存结果
            }
        }
        return resultIndex;
    }


    /**
     * 判断当前节点是否可以进行找色
     * 循环已经作色的点 当相邻再判断颜色是否相等
     *
     * @param adjacencyMatrix 邻接矩阵
     * @param colorIndex      判断的颜色
     * @param currentValue    当前值列表
     * @param i               当前节点
     * @return
     */
    public static boolean isOk(int[][] adjacencyMatrix, int colorIndex, int[] currentValue, int i) {
        for (int j = 0; j < i; j++) {
            if (adjacencyMatrix[j][i] == 1) {
                if (currentValue[j] == colorIndex) {
                    return false;
                }
            }
        }
        return true;
    }
```
##四、优化空间
