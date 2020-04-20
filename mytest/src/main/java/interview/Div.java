package interview;

public class Div {
    public static void main(String[] args) {

    }

    int div2()  //注意负数的处理
    {
        int x=100;
        int y =-3;
        boolean negative=false;
        System.out.println(String.format("%d/%d",x,y));
        if(y ==0)
            return ~(1<<31);
        if(x*y <0)
        {
            x = Math.abs(x);
            y = Math.abs(y);
            negative = true;
        }
        int x_temp =x;
        int multi;
        int result_ans =0;

        while(y<=x_temp)
        {
            multi=1;
            while(y*multi<=(x_temp>>1))  //x_temp ，跳出来右移动1位，这样跳出来刚好，是倍数
            {
                multi<<=1;  //以2的倍数上增加
            }
            result_ans+=multi;
            x_temp = x_temp - y*multi;  //2倍之后，剩下部分，继续判断，更新x_temp ，这样就可以补充奇数倍
        }
        if(negative)
            result_ans = -result_ans;
        System.out.println(String.format("=%d\n",result_ans));
        return result_ans;
    }
}
