class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings,(a,b)->a[0]-b[0]);
        int m=meetings.length;
        PriorityQueue<int[]>freeroom=new PriorityQueue<>((a,b)->a[0]-b[0]);
         PriorityQueue<int[]>occupiedroom=new PriorityQueue<>((a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
         int[] freq=new int[n];
         for(int i=0;i<n;i++){
            freeroom.offer(new int[]{0,i});
         }
         for(int i=0;i<m;i++){
            int start=meetings[i][0];
            int end=meetings[i][1];
            while(!occupiedroom.isEmpty() && occupiedroom.peek()[0]<=start){
                int[] curr=occupiedroom.poll();
                freeroom.offer(new int[]{0,curr[1]});
            }
            if(!freeroom.isEmpty()){
                int[] free=freeroom.peek();
                occupiedroom.offer(new int[]{end,free[1]});
                freq[free[1]]++;
                freeroom.poll();
            }
            else{
               int diff=end-start;
               int[] current=occupiedroom.peek();
               occupiedroom.poll();
               occupiedroom.offer(new int[]{current[0]+diff,current[1]});
               freq[current[1]]++;
            }
         }
          int ans = 0;
        for (int i = 1; i < n; i++) {
            if (freq[i] > freq[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}
