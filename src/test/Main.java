package test;

/**
 * 测试static
 * private 的static 属性和方法 在其他类中都访问不了
 * @author huoyun
 * @date 2019/5/28-23:13
 */
public class Main {
    public static void main(String[] args){
//        System.out.println(Man.age);

//        Man.
    }
}

class Man{
    private static int age = 18;

    private static void echo(String str){
        System.out.println(str);
    }
}
