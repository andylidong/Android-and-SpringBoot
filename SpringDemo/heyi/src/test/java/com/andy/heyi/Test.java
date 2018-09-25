package com.andy.heyi;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName com.andy.heyi.Test
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/31$ 9:47 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/31$ 9:47 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class Test {

    public static void main(String[] args) {
        System.out.println( compute1( 5, i -> i * 2, i -> i * i ) ); // 100
        System.out.println( compute2( 5, i -> i * 2, i -> i * i ) ); // 50
    }

    private static int compute1(int i, Function<Integer, Integer> after, Function<Integer, Integer> before) {
        return after.andThen( before ).apply( i );
    }

    private static int compute2(int i, Function<Integer, Integer> after, Function<Integer, Integer> before) {
        return before.andThen( after ).apply( i );
    }

    /**
     * 显示lambol的结果
     */
    private static void evalTest() {
        List<Integer> list = IntStream.range( 0, 10 ).boxed().collect( Collectors.toList() );
        System.out.println( "输出所有数据:" );
        eval( list, n -> true );
        System.out.println( "输出所有偶数:" );
        eval( list, n -> n % 2 == 0 );
        System.out.println( "输出所有大于3的数字:" );
        eval( list, n -> n > 3 );
    }

    /**
     * 输出结果
     *
     * @param list
     * @param predicate
     */
    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test( n )) {
                System.out.print( n + "\t" );
            }
        }
        System.out.println();
    }

    /**
     * 函数的调用
     */
    private static void function() {
        Function<String, Integer> toString = Integer::valueOf;
        int result = toString.apply( "1212" );
        System.out.println( "result = " + result + "result == 1212" + (result == 1212) );
        Function<Integer, String> toInteger = String::valueOf;
        String toResult = toInteger.apply( 232323 );
        System.out.println( "toResult = " + toResult + "toResult == 232323" + toResult.equalsIgnoreCase( "232323" ) );
        Consumer<Integer> demo = (params) -> System.out.println( "result = " + (params * params) );
        demo.accept( 12 );
    }

    /**
     * 并发
     */
    private static void concurrency() {
        // 并发API引入了ExecutorService作为一个在程序中直接使用Thread的高层次的替换方案
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit( () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println( "Hello " + threadName );
        } );
        // 任何future.get()调用都会阻塞，然后等待直到callable中止
        ExecutorService executor1 = Executors.newFixedThreadPool( 1 );
        Future<Integer> future = executor1.submit( () -> {
            try {
                TimeUnit.SECONDS.sleep( 2 );
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException( "task interrupted", e );
            }
        } );
        try {
            future.get( 1, TimeUnit.SECONDS );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符串、数值、算术和文件
     */
    private static void string() {
        // 正则表达式
        Pattern pattern = Pattern.compile( ".*@gmail\\.com" );
        long count = Stream.of( "bob@gmail.com", "alice@hotmail.com" )
                .filter( pattern.asPredicate() )
                .count();
        System.out.println( "count = " + count );
    }

    /**
     * lambda的语法
     */
    private static void java8() {
        // 排序
        List<String> names = Arrays.asList( "peter", "anna", "mike", "xenia" );
        Collections.sort( names, Comparator.naturalOrder() );
        System.out.println( names );
        // 类型转换 String -> Integer
        Converter<String, Integer> converter = Integer::valueOf;
        System.out.println( converter.convert( "12345" ) );
        // 类型转换 Integer -> String
        Converter<Integer, String> converted = (from) -> Integer.toString( from );
        System.out.println( converted.convert( 12345 ) );
        // 结果
        ResultFactory<Person> resultFactory = Person::new;
        System.out.println( resultFactory.create( "12345", 12 ).toString() );
        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf( from + num );
        System.out.println( stringConverter.convert( 2 ) );
        // 判断是否为空  Predicate  布尔值类型的函数
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println( isEmpty.test( "" ) );
        // Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。
        Supplier<Person> personSupplier = Person::new;
        System.out.println( personSupplier.get().toString() );
        System.out.println( personSupplier.get().toString() );
        // Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compse, andThen）
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen( String::valueOf );
        System.out.println( backToString.apply( "123" ) );
        System.out.println( toInteger.apply( "12" ) );
        // Consumer代表了在一个输入参数上需要进行的操作
        Consumer<Person> greeter = (p) -> System.out.println( "Hello, " + p.getName() );
        greeter.accept( new Person( "Andy", 12 ) );
        // Comparator接口在早期的Java版本中非常著名。Java 8 为这个接口添加了不同的默认方法。
        Comparator<Person> comparator = Comparator.comparing( Person::getName );
        Person p1 = new Person( "Demo", 12 );
        Person p2 = new Person( "com.andy.heyi.Test", 14 );
        System.out.println( comparator.compare( p1, p2 ) ); // > 0
        System.out.println( comparator.reversed().compare( p1, p2 ) );  // < 0
        // 可以使用IntStream.range()
        IntStream.range( 1, 4 ).forEach( System.out::println );
    }

    /**
     * 排序
     */
    private static void sort() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add( "ddd2" );
        stringCollection.add( "aaa2" );
        stringCollection.add( "bbb1" );
        stringCollection.add( "aaa1" );
        stringCollection.add( "bbb3" );
        stringCollection.add( "ccc" );
        stringCollection.add( "bbb2" );
        stringCollection.add( "ddd1" );
        stringCollection
                .stream()
                .filter( s -> s.endsWith( "1" ) )
                .filter( (s) -> s.startsWith( "a" ) )
                .forEach( System.out::println );
        // 输出数组
        String[] names = {"Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres"};
        Arrays.asList( names )
                .stream()
                .map( String::toUpperCase )
                .sorted().forEach( (value) -> System.out.print( value + "\t" ) );
        System.out.println();

    }

    /**
     * 线程  runnable.run()
     */
    private static Runnable runnable = () -> {
        try {
            String name = Thread.currentThread().getName();
            System.out.println( "Foo " + name );
            TimeUnit.SECONDS.sleep( 1 );
            System.out.println( "Bar " + name );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

@FunctionalInterface
interface ResultFactory<P extends Person> {
    P create(String name, int age);
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "com.andy.heyi.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

