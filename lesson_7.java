import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

public class lesson_7 {

    public static void main(String[] args) {
        try {
            start_test("Test_class");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void start_test(String class_name) throws ClassNotFoundException {
        Class test_class=Class.forName(class_name);
        test_class.getAnnotations();
        Method[] metods=test_class.getDeclaredMethods();
        Method[] sortArray=new Method[metods.length];
        for (Method method:metods) {
            Annotation[] annotation = method.getDeclaredAnnotations();
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (method.getAnnotations().length>1){
                    throw new RuntimeException();
                }
                sortArray[0] = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (method.getAnnotations().length>1){
                    throw new RuntimeException();
                }
                sortArray[metods.length-1] = method;
            } else if (method.getAnnotation(Test.class) != null) {
                sortArray[method.getAnnotation(Test.class).value()]=method;
            }
          }
         Test_class test=new Test_class();

        for (Method m:sortArray ) {
            try {
                m.invoke(test);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
      //  test_class.getAnnotations();
    }
}
