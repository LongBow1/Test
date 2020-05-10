import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(getFibonacc(scanner.nextInt()));
        }
*/

        System.out.println(        combinationSumV2(new int[]{2,3,5},4));

        //System.out.println(combinationSum(new int[]{2,3,6,7},7));
    }


    public static List<List<Integer>> combinationSumV2(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0 || target <= 0){
            return null;
        }
        List<List<Integer>> resultList = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        compute(candidates, 0, target, list, resultList);
        return resultList;
    }

    private static void compute(int[] candidates, int start, int target, List<Integer> list, List<List<Integer>> resultList){
        if(target < 0){
            return;
        }

        if(target == 0){
            resultList.add(new ArrayList<>(list));
        }else{
            for(int i=start; i< candidates.length; i++){
                list.add(candidates[i]);
                compute(candidates, i, target-candidates[i], list,resultList);
                list.remove(list.size() - 1);
            }
        }
    }

    public static int getFibonacc(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        return getFibonacc(n-1) + getFibonacc(n-2);
    }

    /**
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> candidateList = new ArrayList(Arrays.asList(candidates));
        Collections.sort(candidateList);
        /*if(target < candidateList.get(0)){
            return null;
        }*/
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, candidates.length, target, 0, new ArrayDeque<>(), result);

        /*for (int i = 0; i < candidateList.size(); i++) {
            target = target - candidateList.get(i);
            List<List<Integer>> tmpRes = new ArrayList<>();
            while (target >0 ){
                target = compute(candidateList, target,tmpRes);
            }
        }*/
        return result;
    }

    private static void dfs(int[] candidates,
                            int len,
                            int residue,
                            int begin,
                            Deque<Integer> path,
                            List<List<Integer>> res) {
        if (residue == 0) {
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {

            // 在数组有序的前提下，剪枝
            if (residue - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, len, residue - candidates[i], i, path, res);
            path.removeLast();

        }
    }


    /*private int compute(List<Integer> candidateList, int target, List<List<Integer>> tmpRes) {
        for (int i = 0; i < candidateList.size(); i++) {
            if(target % candidateList.get(i) == 0){
                //result.add(new ArrayList<>(Arrays.asList(Arrays.copyOf(int[target/item],item))))
                int len = target / candidateList.get(i);
                List<Integer> tmp = new ArrayList<>(len);
                for (int j = 0; j < len; j++) {
                    tmp.add(candidateList.get(i));
                }
                tmpRes.add(tmp);
            }
            //target = target - candidateList.get(i);
            List<Integer> tmpDetailRes = new ArrayList<>();
            while ((target = target - candidateList.get(i)) >0 ){
                tmpDetailRes.add(candidateList.get(i));
            }
            if(target != 0){

            }
        }
    }*/
}
